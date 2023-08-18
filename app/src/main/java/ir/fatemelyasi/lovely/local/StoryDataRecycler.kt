package ir.fatemelyasi.lovely.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "table_Story")
data class StoryDataRecycler(

    @PrimaryKey(true)
    val id: Int? = null,

    val date: String,
    var title: String,
    var imageUri: String

)
