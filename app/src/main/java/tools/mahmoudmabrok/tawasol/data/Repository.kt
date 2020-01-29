package tools.mahmoudmabrok.tawasol.data

import android.content.Context
import org.koin.core.KoinComponent
import org.koin.core.inject
import tools.mahmoudmabrok.tawasol.data.local.WordDB
import tools.mahmoudmabrok.tawasol.data.local.WordItem
import tools.mahmoudmabrok.tawasol.utils.log

class Repository(val ctx:Context) : DataSource,KoinComponent{

    private val db : WordDB by inject()

  //  private val dao = WordDB.getAppDataBase(ctx).wordDao()


    override fun getResID(text: String): Int {
            return db.wordDao().getWordIDByText(text)
    }

    override fun addWord(word: WordItem) {
        db.wordDao().insert(word)
    }


}