package tools.mahmoudmabrok.tawasol.feature.camera

import android.Manifest
import android.app.ProgressDialog
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.Bundle
import android.util.Size
import android.view.Surface
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_camera_part.*
import tools.mahmoudmabrok.tawasol.R
import tools.mahmoudmabrok.tawasol.feature.text.TextPart
import tools.mahmoudmabrok.tawasol.utils.goTo
import tools.mahmoudmabrok.tawasol.utils.log
import tools.mahmoudmabrok.tawasol.utils.show
import java.io.File
import java.util.concurrent.Executors

class CameraPart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_part)
    }
}


/*

class CameraPart : AppCompatActivity(), ImageCapture.OnImageSavedListener {


    private lateinit var pBar: ProgressDialog

    */
/**
     * permission code to be identified.
 *//*
    private val REQUEST_CODE_PERMISSIONS = 10

    */
/**
     * This is an array of all the permission specified in the manifest.
 *//*
    private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)

    */
/**
     * classifer object  uses context to get access to assets folder to load model.
 *//*
    private val classifer = Classifier(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_part)

        imBackFromCamera.setOnClickListener {
            finish()
        }

        imOpenText.setOnClickListener {
            this.goTo(TextPart::class.java)
            finish()
        }


        // Request camera permissions
        if (allPermissionsGranted()) {
            // use post to be sure that view is inflated.
            cameraView.post { startCamera() }
        } else {
            ActivityCompat.requestPermissions(
                    this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }

        // Every time the provided texture view changes, recompute layout
        cameraView.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            updateTransform()
        }

        classifer
                .initialize()
                .addOnFailureListener { e -> "Error to setting up digit classifer, ${e.localizedMessage}".log() }


    }

    override fun onResume() {
        super.onResume()
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
    }

    override fun onDestroy() {
        classifer.close()
        super.onDestroy()

    }

    private val executor = Executors.newSingleThreadExecutor()

    private fun startCamera() {
        // First need to make config setting.

        // Create configuration object for the viewfinder use case
        val previewConfig = PreviewConfig.Builder().apply {
            // set Size to 640*480 
            setTargetResolution(Size(cameraView.width, cameraView.height))
            setTargetRotation(Surface.ROTATION_0)
        }.build()


        // Build the viewfinder use case
        val preview = Preview(previewConfig)

        // Every time the viewfinder is updated, recompute layout
        preview.setOnPreviewOutputUpdateListener {

            // To update the SurfaceTexture, we have to remove it and re-add it
            val parent = cameraView.parent as ViewGroup
            parent.removeView(cameraView)
            parent.addView(cameraView, 0)
            cameraView.surfaceTexture = it.surfaceTexture
            //  cameraView.updateTransformForCameraFinderView()
            updateTransform()

        }

        // use case for capture

        // Create configuration object for the image capture use case
        val imageCaptureConfig = ImageCaptureConfig.Builder()
                .apply {
                    // We don't set a resolution for image capture; instead, we
                    // select a capture mode which will infer the appropriate
                    // resolution based on aspect ration and requested mode
                    setCaptureMode(ImageCapture.CaptureMode.MIN_LATENCY)
                }.build()

        // create  the image capture use case
        val imageCapture = ImageCapture(imageCaptureConfig)

        // add listener for btn clicks

        takeImage.setOnClickListener {
            val file = File(externalMediaDirs.first(),
                    "${System.currentTimeMillis()}.jpg")

            imageCapture.takePicture(file, executor, this
            )
        }


        // Bind use cases to lifecycle
        // If Android Studio complains about "this" being not a LifecycleOwner
        // try rebuilding the project or updating the appcompat dependency to
        // version 1.1.0 or higher.
        CameraX.bindToLifecycle(this, preview, imageCapture)
    }

    private fun updateTransform() {
        val matrix = Matrix()

        // Compute the center of the view finder
        val centerX = cameraView.width / 2f
        val centerY = cameraView.height / 2f

        // Correct preview output to account for display rotation
        val rotationDegrees = when (cameraView.display.rotation) {
            Surface.ROTATION_0 -> 0
            Surface.ROTATION_90 -> 90
            Surface.ROTATION_180 -> 180
            Surface.ROTATION_270 -> 270
            else -> return
        }
        matrix.postRotate(-rotationDegrees.toFloat(), centerX, centerY)

        // Finally, apply transformations to our TextureView
        cameraView.setTransform(matrix)
    }

    */
/**
     * Process result from permission request dialog box, has the request
     * been granted? If yes, start Camera. Otherwise display a toast
 *//*
    override fun onRequestPermissionsResult(
            requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                cameraView.post { startCamera() }
            } else {
                Toast.makeText(this,
                        "Permissions not granted by the user.",
                        Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    */
/**
     * Check if all permission specified in the manifest have been granted
 *//*
    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
                baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    */
/**
     * called when capture image and it saved to disk
 *//*
    override fun onImageSaved(file: File) {
        val msg = "Photo capture succeeded: ${file.absolutePath}"
        cameraView.post {
            //  Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            pBar = ProgressDialog(this)
            pBar.setCancelable(false)
            pBar.show()

        }

        val bitmap = BitmapFactory.decodeFile(file.path)
        //todo  add indicator for loading
        if ((bitmap != null) && (classifer.isInitialized)) {
            classifer
                    .classifyAsync(bitmap)
                    .addOnSuccessListener { resultText ->
                        pBar.dismiss()
                        this.show("result:$resultText")
                    }
                    .addOnFailureListener { e ->
                        pBar.dismiss()
                        this.show("Error :${e.localizedMessage}")
                    }
        } else {
            "NOt able to load".log()
        }
    }

    override fun onError(
            imageCaptureError: ImageCapture.ImageCaptureError,
            message: String,
            exc: Throwable?
    ) {
        val msg = "Photo capture failed: $message"
        msg.log()
        cameraView.post {
            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        }
    }



}*/