package tools.mahmoudmabrok.tawasol.feature.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.googlecode.tesseract.android.TessBaseAPI
import kotlinx.android.synthetic.main.activity_home.*
import tools.mahmoudmabrok.tawasol.R
import tools.mahmoudmabrok.tawasol.feature.camera.CameraPart
import tools.mahmoudmabrok.tawasol.feature.text.TextPart
import tools.mahmoudmabrok.tawasol.utils.animateItemWithAction
import tools.mahmoudmabrok.tawasol.utils.goTo

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        openCamer.setOnClickListener {
            it.animateItemWithAction {
                this.goTo(CameraPart::class.java)
            }
        }

        openText.setOnClickListener {
            it.animateItemWithAction {
                this.goTo(TextPart::class.java)
            }
        }


    }

    override fun onResume() {
        super.onResume()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

        val is5Even = 5.isEven()
    }

}

fun Int.isEven() = this % 2 == 0



