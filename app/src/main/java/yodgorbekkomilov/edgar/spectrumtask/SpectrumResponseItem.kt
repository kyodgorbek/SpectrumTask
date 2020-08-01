package yodgorbekkomilov.edgar.spectrumtask


import com.google.gson.annotations.SerializedName

data class SpectrumResponseItem(
    @SerializedName("about")
    val about: String,
    @SerializedName("company")
    val company: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("members")
    val members: List<Member>,
    @SerializedName("website")
    val website: String
)