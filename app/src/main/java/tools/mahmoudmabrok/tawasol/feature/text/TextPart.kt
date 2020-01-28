package tools.mahmoudmabrok.tawasol.feature.text

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_test_part.*
import org.koin.android.ext.android.inject
import tools.mahmoudmabrok.tawasol.R
import tools.mahmoudmabrok.tawasol.data.Repository
import tools.mahmoudmabrok.tawasol.feature.camera.CameraPart
import tools.mahmoudmabrok.tawasol.utils.*


data class ImageItem(val text: String, val resID: Int)


class TextPart : AppCompatActivity() {

    /**
     * list to hold each image res id along with its text to be displayed
     * */
    val resList = mutableListOf<ImageItem>()
    /**
     * Repository reference by injection, to access local db methods
     */
    lateinit var repo: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_part)

        repo = Repository(this)

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
            val input = edText.text.toString().clear()
            tvResultText.text = input
            try {
                process(input)
            } catch (e: Exception) {
                e.localizedMessage.log()
                tvResultText.text = "Not Found"
            }
        }

    }

    override fun onResume() {
        super.onResume()
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
    }

    private fun process(text: String) {
        // make sure it contains text
        if (!text.isBlank()) {
            "process: $text".log()
            // split text into sentences, process each one
            var resID = -1
            text.split(" ").forEach {
                // get resID from db, non-positive values means not found so will parse it char by char
                resID = repo.getResID(it)
                "id: $resID t: $it"
                when {
                    resID > 0 -> {
                        resList.add(ImageItem(it, resID))
                    }
                    else -> {
                        // not found, loop through all chars.
                        it.forEach {
                            // handle it char by char
                            resID = repo.getResID(it.toString())
                            "## id: $resID t: $it"
                            resList.add(ImageItem(it.toString(), resID))
                        }
                    }
                }
            }

            // display them in UI
            handleResults()

        }
    }

    private fun handleResults() {
        val hanlder = Handler()
        var s = 1
        resList.forEach {
            hanlder.postDelayed({
                processImageItem(it)
            }, ((s * 700).toLong()))
            s++
        }
    }

    private fun processImageItem(it: ImageItem) {
        "pp :${it.resID}".log()
        imResult.setImageResource(it.resID)
        tvResultText.text = it.text
        imResult.animItem()
    }
}