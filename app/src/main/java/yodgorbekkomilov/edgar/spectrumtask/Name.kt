package yodgorbekkomilov.edgar.spectrumtask


import com.google.gson.annotations.SerializedName

data class Name(
    @SerializedName("first")
    val first: String?,
    @SerializedName("last")
    val last: String?
)