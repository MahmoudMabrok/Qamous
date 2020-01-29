package tools.mahmoudmabrok.tawasol.feature.splash

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*
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
        repository.addWord(WordItem(R.drawable.e, "e", ""))
        repository.addWord(WordItem(R.drawable.f, "f", ""))
        repository.addWord(WordItem(R.drawable.g, "g", ""))
        repository.addWord(WordItem(R.drawable.h, "h", ""))
        repository.addWord(WordItem(R.drawable.i, "i", ""))
        repository.addWord(WordItem(R.drawable.j, "j", ""))
        repository.addWord(WordItem(R.drawable.k, "k", ""))
        repository.addWord(WordItem(R.drawable.l, "l", ""))
        repository.addWord(WordItem(R.drawable.m, "m", ""))
        repository.addWord(WordItem(R.drawable.n, "n", ""))
        repository.addWord(WordItem(R.drawable.o, "o", ""))
        repository.addWord(WordItem(R.drawable.p, "p", ""))
        repository.addWord(WordItem(R.drawable.q, "q", ""))
        repository.addWord(WordItem(R.drawable.r, "r", ""))
        repository.addWord(WordItem(R.drawable.s, "s", ""))
        repository.addWord(WordItem(R.drawable.t, "t", ""))
        repository.addWord(WordItem(R.drawable.u, "u", ""))
        repository.addWord(WordItem(R.drawable.v, "v", ""))
        repository.addWord(WordItem(R.drawable.w, "w", ""))
        repository.addWord(WordItem(R.drawable.x, "x", ""))
        repository.addWord(WordItem(R.drawable.y, "y", ""))
        repository.addWord(WordItem(R.drawable.z, "z", ""))

        repository.addWord(WordItem(R.drawable.a0, "0", ""))
        repository.addWord(WordItem(R.drawable.a1, "1", ""))
        repository.addWord(WordItem(R.drawable.a2, "2", ""))
        repository.addWord(WordItem(R.drawable.a3, "3", ""))
        repository.addWord(WordItem(R.drawable.a4, "4", ""))
        repository.addWord(WordItem(R.drawable.a5, "5", ""))
        repository.addWord(WordItem(R.drawable.a6, "6", ""))
        repository.addWord(WordItem(R.drawable.a7, "7", ""))
        repository.addWord(WordItem(R.drawable.a8, "8", ""))
        repository.addWord(WordItem(R.drawable.a9, "9", ""))


        repository.addWord(WordItem(R.drawable.alef, "أ", ""))
        repository.addWord(WordItem(R.drawable.aleflam, "ال", ""))
        repository.addWord(WordItem(R.drawable.ayn, "ع", ""))
        repository.addWord(WordItem(R.drawable.ba, "ب", ""))
        repository.addWord(WordItem(R.drawable.da, "د", ""))
        repository.addWord(WordItem(R.drawable.dad, "ض", ""))
        repository.addWord(WordItem(R.drawable.dha, "ظ", ""))
        repository.addWord(WordItem(R.drawable.fa, "ف", ""))
        repository.addWord(WordItem(R.drawable.ghyn, "غ", ""))
        repository.addWord(WordItem(R.drawable.h7a, "ح", ""))
        repository.addWord(WordItem(R.drawable.ha, "ه", ""))
        repository.addWord(WordItem(R.drawable.jeem, "ج", ""))
        repository.addWord(WordItem(R.drawable.kaf, "ك", ""))
        repository.addWord(WordItem(R.drawable.kha, "خ", ""))
        repository.addWord(WordItem(R.drawable.lam, "ل", ""))
        repository.addWord(WordItem(R.drawable.lamalef, "لا", ""))
        repository.addWord(WordItem(R.drawable.linehamza, "ء", ""))
        repository.addWord(WordItem(R.drawable.meem, "م", ""))
        repository.addWord(WordItem(R.drawable.nabrahamza, "ئـ", ""))
        repository.addWord(WordItem(R.drawable.noon, "ن", ""))
        repository.addWord(WordItem(R.drawable.qa, "ق", ""))
        repository.addWord(WordItem(R.drawable.ra, "ر", ""))
        repository.addWord(WordItem(R.drawable.sad, "ص", ""))
        repository.addWord(WordItem(R.drawable.seen, "س", ""))
        repository.addWord(WordItem(R.drawable.sheen, "ش", ""))
        repository.addWord(WordItem(R.drawable.ta2, "ط", ""))
        repository.addWord(WordItem(R.drawable.tamaftoha, "ت", ""))
        repository.addWord(WordItem(R.drawable.tamarbota, "ة", ""))
        repository.addWord(WordItem(R.drawable.tha, "ث", ""))
        repository.addWord(WordItem(R.drawable.thal, "ذ", ""))
        repository.addWord(WordItem(R.drawable.waw, "و", ""))
        repository.addWord(WordItem(R.drawable.wawhamza, "ؤ", ""))
        repository.addWord(WordItem(R.drawable.ya, "ي", ""))
        repository.addWord(WordItem(R.drawable.yahamza, "ئ", ""))
        repository.addWord(WordItem(R.drawable.zay, "ز", ""))


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





