package tools.mahmoudmabrok.tawasol.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tools.mahmoudmabrok.tawasol.utils.log


@Database(entities = [WordItem::class],version = 1,exportSchema = false)
abstract class WordDB :RoomDatabase(){

    abstract fun wordDao():WordDao

}