package tools.mahmoudmabrok.tawasol.feature.splash

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import kotlinx.android.synthetic.main.activity_splash.*
import tools.mahmoudmabrok.tawasol.R
import tools.mahmoudmabrok.tawasol.feature.home.Home

import android.util.Pair as UtilPair

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        object : CountDownTimer(1500, 1500) {
            override fun onFinish() {
                startTranistion()
            }

            override fun onTick(p0: Long) {
            }

        }.start()
    }

    private fun startTranistion() {
        val intent = Intent(this, Home::class.java)
        val option = ActivityOptions.makeSceneTransitionAnimation(
                this,
                UtilPair.create(imSplash, "im"),
                UtilPair.create(tvMainSplash, "main"),
                UtilPair.create(tvSubSplash, "sub")
        )
        // intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NO_HISTORY
        startActivity(intent, option.toBundle())
    }
}





