package tools.mahmoudmabrok.tawasol.feature.camera

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_camera_part.*
import tools.mahmoudmabrok.tawasol.R
import tools.mahmoudmabrok.tawasol.utils.log

@SuppressLint("NewApi")
class CameraPart : AppCompatActivity() {
    private val camerReqCode: Int = 1001
    private val MY_CAMERA_PERMISSION_CODE: Int = 1002

    private val mClassifier = DissesClassifier(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_part)

        mClassifier.initialize()
                .addOnSuccessListener {
                    callCameraApp()
                }
                .addOnFailureListener { e ->
                    "Error to setting up digit classifer, ${e.localizedMessage}".log()
                    Toast.makeText(this, "Error, please try again later", Toast.LENGTH_SHORT).show()
                    finish()
                }
    }


    private fun callCameraApp() {
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.CAMERA), MY_CAMERA_PERMISSION_CODE)
        } else {
            // go to pick image
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, camerReqCode)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == camerReqCode) {
            if (null != data) {
                try {
                    val thumbnail = data.extras!!.get("data") as Bitmap
                    cameraView.setImageBitmap(thumbnail)
                    // start model
                    if (mClassifier.isInitialized) {
                        mClassifier.classifyAsync(thumbnail)
                                .addOnSuccessListener { resultText ->
                                    tvResultCamera.text = resultText
                                    " success".log()
                                }
                                .addOnFailureListener { e ->
                                    tvResultCamera.text = e.localizedMessage
                                    " fail".log()
                                }
                        "After When".log()

                    } else {
                        "NOt able to load".log()
                    }

                } catch (e: Exception) {
                    Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroy() {
        mClassifier.close()
        super.onDestroy()
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
