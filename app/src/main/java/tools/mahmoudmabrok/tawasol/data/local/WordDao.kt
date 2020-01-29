package tools.mahmoudmabrok.tawasol.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item:WordItem)

    @Query("select resID from words where text = :text")
    fun getWordIDByText(text:String):Int

}