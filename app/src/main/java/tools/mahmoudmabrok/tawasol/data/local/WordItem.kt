package tools.mahmoudmabrok.tawasol.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class WordItem(
        val resID:Int,
        @PrimaryKey
        val text:String,
        val type:String)
