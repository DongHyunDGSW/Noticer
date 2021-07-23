package kr.hs.dgsw.donghyeon.data

import android.accounts.AuthenticatorDescription
import com.google.gson.annotations.SerializedName

data class RequestModel(
    @SerializedName("body-json")
    val itemUrl : String
)

data class DataModel(
    @SerializedName("0")
    val item : NumberItemModel,
    @SerializedName("1")
    val item1 : NumberItemModel,
    @SerializedName("2")
    val item2 : NumberItemModel,
    @SerializedName("3")
    val item3 : NumberItemModel,
    @SerializedName("4")
    val item4 : NumberItemModel,
    @SerializedName("5")
    val item5 : NumberItemModel,
    @SerializedName("6")
    val item6 : NumberItemModel,
    @SerializedName("7")
    val item7 : NumberItemModel,
    @SerializedName("8")
    val item8 : NumberItemModel,
    @SerializedName("9")
    val item9 : NumberItemModel,
    @SerializedName("item_info")
    val itemInfo : String,
    @SerializedName("web_page")
    val webPage : String
)

data class NumberItemModel(
    @SerializedName("blocks")
    val blocks : ArrayList<BlockData>
)

data class BlockData(
    @SerializedName("confidence")
    val confidence : Double,
    @SerializedName("points")
    val pointData : ArrayList<PointData>,
    @SerializedName("selected")
    val selectedValue : Boolean,
    @SerializedName("text")
    val textDescription : String
)

data class PointData(
    @SerializedName("x")
    val x : Float,
    @SerializedName("y")
    val y : Float
)
