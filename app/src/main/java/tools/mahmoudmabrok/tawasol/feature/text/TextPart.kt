package tools.mahmoudmabrok.tawasol.feature.text

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_test_part.*
import tools.mahmoudmabrok.tawasol.R
import tools.mahmoudmabrok.tawasol.feature.camera.CameraPart
import tools.mahmoudmabrok.tawasol.utils.*


class TextPart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_part)

        /*  setSupportActionBar(textToolbar)
          supportActionBar?.setHomeButtonEnabled(true)
        */

        imBackFromText.setOnClickListener {
            it.animateItemWithAction { finish() }
        }

        imOpenCamera.setOnClickListener {
            it.animateItemWithAction {
                this.goTo(CameraPart::class.java)
                finish()
            }
        }

        imAction.setOnClickListener {
            this.dismissKeyboard()
            val name = edText.text.toString().clear()
            tvResultText.text = name
            try {
                process(name)
            } catch (e: Exception) {
                tvResultText.text = "Not Found"
            }
        }

    }

    override fun onResume() {
        super.onResume()
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
    }

    private fun process(text: String) {
        if (!text.isBlank()) {
            when {
                // input found on drawable so place it directly
                this.isExists(text) ->{
                    processNameIntoImage(text)
                }
                // not found so split it into separates char and display it.
                else -> {
                    handleEachCharSep(text)
                }
            }
        }
    }

    private fun handleEachCharSep(text: String) {
        val hanlder = Handler()
        var s = 1
        for (name in text.toCharArray()) {
            hanlder.postDelayed({
                processNameIntoImage(name.toString())
            }, ((s * 700).toLong()))
            s++
        }
    }

    private fun processNameIntoImage(name: String) {
        imResult.setImageDrawable(this.getImage(name))
        tvResultText.text = name
        imResult.animItem()
    }
}