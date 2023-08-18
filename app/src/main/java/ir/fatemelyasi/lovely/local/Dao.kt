package ir.fatemelyasi.lovely.local
import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(story :StoryDataRecycler)

//    @Insert
//    fun insert(story: StoryDataRecycler)

    @Insert()
    fun insertAllStory(data :List<StoryDataRecycler> )

//    @Update
//    fun update(story: StoryDataRecycler)

    @Delete
    fun deleteStory(story: StoryDataRecycler)

    @Query("DELETE FROM table_Story")
    fun deleteAllStory()

    @Query("SELECT * FROM table_Story ORDER BY date ")
    fun getAllStory(): List<StoryDataRecycler>


//    @Query("SELECT * FROM table_Story WHERE txtSubject LIKE '%' || :searching || '%' ")
//    fun search(searching: String): List<StoryDataRecycler>


}