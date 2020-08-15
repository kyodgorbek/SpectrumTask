package yodgorbekkomilov.edgar.spectrumtask

import com.google.gson.annotations.SerializedName

data class Member(
    @SerializedName("age") val age: Int,
    @SerializedName("email") val email: String,
    @SerializedName("_id") val id: String,
    @SerializedName("name") val name: Name?,
    @SerializedName("phone") val phone: String
)

data class RootItem(
    @SerializedName("id") val _id: String,
    @SerializedName("about") val about: String,
    @SerializedName("company") val company: String,
    @SerializedName("logo")  val logo: String,
    @SerializedName("members")  val members: List<Member>,
    @SerializedName("website")  val website: String
)