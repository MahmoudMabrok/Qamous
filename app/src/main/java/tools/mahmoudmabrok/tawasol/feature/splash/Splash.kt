package tools.mahmoudmabrok.tawasol.feature.splash

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*
import org.koin.android.ext.android.inject
import tools.mahmoudmabrok.tawasol.R
import tools.mahmoudmabrok.tawasol.data.Repository
import tools.mahmoudmabrok.tawasol.data.local.WordItem
import tools.mahmoudmabrok.tawasol.feature.home.Home
import tools.mahmoudmabrok.tawasol.utils.log
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

        try {
            // Thread {  }
            svaeDataToDb()
        } catch (e: Exception) {
            "splash ${e.localizedMessage}".log()
        }

    }

    fun svaeDataToDb() {
        val repository = Repository(this)
        repository.addWord(WordItem(R.drawable.a, "a", ""))
        repository.addWord(WordItem(R.drawable.b, "b", ""))
        repository.addWord(WordItem(R.drawable.c, "c", ""))
        repository.addWord(WordItem(R.drawable.d, "d", ""))
        repository.addWord(WordItem(R.drawable.alph, "أ", ""))

        repository.addWord(WordItem(R.drawable.alplam, "ال", ""))
        repository.addWord(WordItem(R.drawable.ba, "ب", ""))

        repository.addWord(WordItem(R.drawable.a0, "0", ""))
        repository.addWord(WordItem(R.drawable.a1, "1", ""))


    }

    private fun startTranistion() {
        val intent = Intent(this, Home::class.java)
        val option = ActivityOptions.makeSceneTransitionAnimation(
                this,
                UtilPair.create(imSplash, "im"),
                UtilPair.create(tvMainSplash, "main"),
                UtilPair.create(tvSubSplash, "sub")
        )
        startActivity(intent, option.toBundle())
    }
}





