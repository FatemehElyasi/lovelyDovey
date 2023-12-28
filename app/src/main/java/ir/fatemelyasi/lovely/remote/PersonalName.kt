package ir.fatemelyasi.lovely.remote


import com.google.gson.annotations.SerializedName



data class apibody(
    @SerializedName("personalNames")
    val personalNames: List<PersonalName>
)
data class PersonalName(
    @SerializedName("genderScale")
    val genderScale: Double, // 0.9824793433203705
    @SerializedName("id")
    val id: String, // 8c34c749-279c-4de8-b588-9cda951c9fdd
    @SerializedName("likelyGender")
    val likelyGender: String, // female
    @SerializedName("name")
    val name: String, // 早苗 山本
    @SerializedName("probabilityCalibrated")
    val probabilityCalibrated: Double, // 0.9912396716601852
    @SerializedName("score")
    val score: Double, // 26.120365131524423
    @SerializedName("script")
    val script: String // HAN
)