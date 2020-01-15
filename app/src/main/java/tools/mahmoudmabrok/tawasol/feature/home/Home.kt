package tools.mahmoudmabrok.tawasol.feature.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*
import tools.mahmoudmabrok.tawasol.R
import tools.mahmoudmabrok.tawasol.feature.camera.CameraPart
import tools.mahmoudmabrok.tawasol.feature.text.TextPart
import tools.mahmoudmabrok.tawasol.utils.goTo
import tools.mahmoudmabrok.tawasol.utils.log

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        openCamer.setOnClickListener {
            this.goTo(CameraPart::class.java)
        }

        openText.setOnClickListener {
            this.goTo(TextPart::class.java)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        "onDestroy".log()

    }
}