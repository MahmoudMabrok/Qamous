package tools.mahmoudmabrok.tawasol.feature.camera

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_camera_part.*
import kotlinx.android.synthetic.main.activity_test_part.*
import kotlinx.android.synthetic.main.camer_layout.*
import tools.mahmoudmabrok.tawasol.R
import tools.mahmoudmabrok.tawasol.feature.text.TextPart
import tools.mahmoudmabrok.tawasol.utils.goTo

class CameraPart : AppCompatActivity() {
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

    }

    override fun onResume() {
        super.onResume()
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}