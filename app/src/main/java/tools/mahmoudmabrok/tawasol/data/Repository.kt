package tools.mahmoudmabrok.tawasol.data

import org.koin.core.KoinComponent
import org.koin.core.inject
import tools.mahmoudmabrok.tawasol.data.local.WordDB
import tools.mahmoudmabrok.tawasol.data.local.WordItem

class Repository : DataSource, KoinComponent {

    private val db: WordDB by inject()


    override fun getResID(text: String): Int {
        return db.wordDao().getWordIDByText(text)
    }

    override fun addWord(word: WordItem) {
        db.wordDao().insert(word)
    }


}