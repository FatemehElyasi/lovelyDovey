package ir.fatemelyasi.lovely

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StoryDataRecycler(
    val date:String,
    var title:String,
    var imageUri: String
): Parcelable
