package tools.mahmoudmabrok.tawasol.feature.camera

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks.call
import org.tensorflow.lite.Interpreter
import tools.mahmoudmabrok.tawasol.utils.log
import java.io.FileInputStream
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.channels.FileChannel
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class EnglishWordsClassifier(private val context: Context) {
    private var interpreter: Interpreter? = null
    var isInitialized = false
        private set


    private lateinit var chars: List<String>

    /** Executor to run inference task in the background */

    private val executorService: ExecutorService = Executors.newCachedThreadPool()

    private var inputImageWidth: Int = 0 // will be inferred from TF Lite model
    private var inputImageHeight: Int = 0 // will be inferred from TF Lite model
    private var modelInputSize: Int = 0 // will be inferred from TF Lite model

    fun initialize(): Task<Void> {

        initchars()

        return call(
                executorService,
                Callable<Void> {
                    initializeInterpreter()
                    null
                }
        )
    }

    private fun initchars() {
        val a = 'a'
        chars = IntRange(0, 26).map {
            (a + it).toString()
        }
        // filter out 'j', 'z'
        chars.filter { !it.equals('j') and !it.equals('z') }
    }

    fun getChByIndex(pos: Int): String {
        return chars[pos]
    }

    @Throws(IOException::class)
    private fun initializeInterpreter() {
        // Load the TF Lite model
        val assetManager = context.assets
        val model = loadModelFile(assetManager)

        // Initialize TF Lite Interpreter with NNAPI enabled
        val options = Interpreter.Options()
        options.setUseNNAPI(true)
        val interpreter = Interpreter(model, options)

        // Read input shape from model file
        val inputShape = interpreter.getInputTensor(0).shape()
        inputImageWidth = inputShape[1]
        inputImageHeight = inputShape[2]
        modelInputSize = FLOAT_TYPE_SIZE * inputImageWidth * inputImageHeight * PIXEL_SIZE
        "size $modelInputSize".log()

        // Finish interpreter initialization
        this.interpreter = interpreter
        isInitialized = true
        "Initialized TFLite interpreter.".log()
    }

    @Throws(IOException::class)
    private fun loadModelFile(assetManager: AssetManager): ByteBuffer {
        val fileDescriptor = assetManager.openFd(MODEL_FILE)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    private fun classify(bitmap: Bitmap): String {
        if (!isInitialized) {
            throw IllegalStateException("TF Lite Interpreter is not initialized yet.")
        }

        var startTime: Long
        var elapsedTime: Long

        "w:$inputImageWidth h:$inputImageHeight".log()
        // Preprocessing: resize the input
        startTime = System.nanoTime()
        val resizedImage = Bitmap.createScaledBitmap(bitmap, inputImageWidth, inputImageHeight, true)
        val byteBuffer = convertBitmapToByteBuffer(resizedImage)
        elapsedTime = (System.nanoTime() - startTime) / 1000000
        "Preprocessing time = $elapsedTime ms".log()
        startTime = System.nanoTime()
        // maje array of array(i.e 2D)
        val result = Array(1) { FloatArray(OUTPUT_CLASSES_COUNT) }
        // run inference by provide buffer for image and container for output.
        interpreter?.run(byteBuffer, result)
        elapsedTime = (System.nanoTime() - startTime) / 1000000
        "Inference time = " + elapsedTime + "ms".log()

        return getOutputString(result[0])
    }

    fun classifyAsync(bitmap: Bitmap): Task<String> {
        return call(executorService, Callable<String> { classify(bitmap) })
    }

    fun close() {
        call(
                executorService,
                Callable<String> {
                    interpreter?.close()
                    Log.d(TAG, "Closed TFLite interpreter.")
                    null
                }
        )
    }

    /**
     * convertBitmapToByteBuffer
     */

    private fun convertBitmapToByteBuffer(bitmap: Bitmap): ByteBuffer {
        "convertBitmapToByteBuffer".log()
        val byteBuffer = ByteBuffer.allocateDirect(modelInputSize)
        "a ${byteBuffer}"
        byteBuffer.order(ByteOrder.nativeOrder())

        val pixels = IntArray(inputImageWidth * inputImageHeight)
        bitmap.getPixels(pixels, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)

        for (pixelValue in pixels) {
            val r = (pixelValue shr 16 and 0xFF)
            val g = (pixelValue shr 8 and 0xFF)
            val b = (pixelValue and 0xFF)

            // Convert RGB to grayscale and normalize pixel value to [0..1]
            val normalizedPixelValue = (r + g + b) / 3.0f / 255.0f
            byteBuffer.putFloat(normalizedPixelValue)
        }

        "finish convert".log()

        return byteBuffer
    }

    private fun getOutputString(output: FloatArray): String {
        val maxIndex = output.indices.maxBy { output[it] } ?: -1
        //return "Prediction Result: %d\nConfidence: %2f".format(maxIndex, output[maxIndex])
        return maxIndex.toString()
    }

    companion object {
        const val NAME = "English Letter"

        private const val TAG = "TestApp"

        private const val FLOAT_TYPE_SIZE = 4

        private const val MODEL_FILE = "words.tflite"

        // private const val MODEL_FILE = "mnist.tflite"

        /**
         * color channels 3 means 3 colors so each pixel hold three values.
         */

        private const val PIXEL_SIZE = 1

        private const val OUTPUT_CLASSES_COUNT = 24
    }
}

