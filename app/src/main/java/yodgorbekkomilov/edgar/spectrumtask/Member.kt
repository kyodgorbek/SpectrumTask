package yodgorbekkomilov.edgar.spectrumtask


import com.google.gson.annotations.SerializedName

data class Member(
    @SerializedName("age")
    val age: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: Name,
    @SerializedName("phone")
    val phone: String
)