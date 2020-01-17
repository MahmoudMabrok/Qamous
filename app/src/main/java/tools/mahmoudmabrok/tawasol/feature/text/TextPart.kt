package tools.mahmoudmabrok.tawasol.feature.text

import android.R.attr.name
import android.animation.AnimatorSet
import android.content.res.Resources
import android.os.Bundle
import android.os.Handler
import android.view.ViewPropertyAnimator
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_test_part.*
import tools.mahmoudmabrok.tawasol.R
import tools.mahmoudmabrok.tawasol.feature.camera.CameraPart
import tools.mahmoudmabrok.tawasol.utils.*
import java.util.concurrent.TimeUnit


class TextPart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_part)

        imBackFromText.setOnClickListener {
            finish()
        }

        imOpenCamera.setOnClickListener {
            this.goTo(CameraPart::class.java)
        }

        imAction.setOnClickListener {
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
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
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
        /*val an = AnimationUtils.loadAnimation(this,android.R.anim.fade_in)
        an.duration = 600
        imResult.animation = an
        */
        imResult.setImageDrawable(this.getImage(name))
        tvResultText.text = name
    }
}