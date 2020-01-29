package tools.mahmoudmabrok.tawasol.data

import tools.mahmoudmabrok.tawasol.data.local.WordItem

interface DataSource {

    fun addWord(word:WordItem)
    fun getResID(text:String):Int
}