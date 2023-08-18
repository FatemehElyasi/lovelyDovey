package ir.fatemelyasi.lovely.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [StoryDataRecycler::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

     abstract val storyDao: Dao

    companion object {

        private var dataBase: MyDatabase? = null
        fun getDatabase(context: Context): MyDatabase {
            if (dataBase == null) {
                dataBase = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "myDatabase.db"
                )
                        //for access to run in main thread
                    .allowMainThreadQueries()
                    .build()
            }
            return dataBase!!
        }

    }

}
