package com.example.cryptoappnew.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cryptoappnew.api.ApiFactory.BASE_IMAGE_URL
import com.example.cryptoappnew.convertTimestampToTime
import com.google.gson.annotations.SerializedName

@Entity(tableName = "full_price_list")
data class CoinPriceInfo(
    @SerializedName("TYPE")
    var type: String?,
    @SerializedName("MARKET")
    var market: String?,
    @PrimaryKey
    @SerializedName("FROMSYMBOL")
    var fromSymbol: String,
    @SerializedName("TOSYMBOL")
    var toSymbol: String?,
    @SerializedName("FLAGS")
    var flags: String?,
    @SerializedName("PRICE")
    var price: Double?,
    @SerializedName("LASTUPDATE")
    var lastUpdate: Long?,
    @SerializedName("LASTVOLUME")
    var lastVolume: Double?,
    @SerializedName("LASTMARKET")
    var lastMarket: String?,
    @SerializedName("HIGHDAY")
    var highDay: String?,
    @SerializedName("LOWDAY")
    var lowDay: String?,
/*    @SerializedName("LASTVOLUMETO") var LASTVOLUMETO: Double? = null,
    @SerializedName("LASTTRADEID") var LASTTRADEID: String? = null,
    @SerializedName("VOLUMEDAY") var VOLUMEDAY: Double? = null,
    @SerializedName("VOLUMEDAYTO") var VOLUMEDAYTO: Double? = null,
    @SerializedName("VOLUME24HOUR") var VOLUME24HOUR: Double? = null,
    @SerializedName("VOLUME24HOURTO") var VOLUME24HOURTO: Double? = null,
    @SerializedName("OPENDAY") var OPENDAY: Double? = null,
    @SerializedName("HIGHDAY") var HIGHDAY: Double? = null,
    @SerializedName("LOWDAY") var LOWDAY: Double? = null,
    @SerializedName("OPEN24HOUR") var OPEN24HOUR: Double? = null,
    @SerializedName("HIGH24HOUR") var HIGH24HOUR: Double? = null,
    @SerializedName("LOW24HOUR") var LOW24HOUR: Double? = null,
    @SerializedName("LASTMARKET") var LASTMARKET: String? = null,
    @SerializedName("VOLUMEHOUR") var VOLUMEHOUR: Double? = null,
    @SerializedName("VOLUMEHOURTO") var VOLUMEHOURTO: Double? = null,
    @SerializedName("OPENHOUR") var OPENHOUR: Double? = null,
    @SerializedName("HIGHHOUR") var HIGHHOUR: Double? = null,
    @SerializedName("LOWHOUR") var LOWHOUR: Double? = null,
    @SerializedName("TOPTIERVOLUME24HOUR") var TOPTIERVOLUME24HOUR: Double? = null,
    @SerializedName("TOPTIERVOLUME24HOURTO") var TOPTIERVOLUME24HOURTO: Double? = null,
    @SerializedName("CHANGE24HOUR") var CHANGE24HOUR: Double? = null,
    @SerializedName("CHANGEPCT24HOUR") var CHANGEPCT24HOUR: Double? = null,
    @SerializedName("CHANGEDAY") var CHANGEDAY: Double? = null,
    @SerializedName("CHANGEPCTDAY") var CHANGEPCTDAY: Double? = null,
    @SerializedName("CHANGEHOUR") var CHANGEHOUR: Double? = null,
    @SerializedName("CHANGEPCTHOUR") var CHANGEPCTHOUR: Double? = null,
    @SerializedName("CONVERSIONTYPE") var CONVERSIONTYPE: String? = null,
    @SerializedName("CONVERSIONSYMBOL") var CONVERSIONSYMBOL: String? = null,
    @SerializedName("SUPPLY") var SUPPLY: Int? = null,
    @SerializedName("MKTCAP") var MKTCAP: Double? = null,
    @SerializedName("MKTCAPPENALTY") var MKTCAPPENALTY: Int? = null,
    @SerializedName("CIRCULATINGSUPPLY") var CIRCULATINGSUPPLY: Int? = null,
    @SerializedName("CIRCULATINGSUPPLYMKTCAP") var CIRCULATINGSUPPLYMKTCAP: Double? = null,
    @SerializedName("TOTALVOLUME24H") var TOTALVOLUME24H: Double? = null,
    @SerializedName("TOTALVOLUME24HTO") var TOTALVOLUME24HTO: Double? = null,
    @SerializedName("TOTALTOPTIERVOLUME24H") var TOTALTOPTIERVOLUME24H: Double? = null,
    @SerializedName("TOTALTOPTIERVOLUME24HTO") var TOTALTOPTIERVOLUME24HTO: Double? = null,*/
    @SerializedName("IMAGEURL")
    var imageUrl: String?,

){
    fun getFormattedTime (): String{
       return convertTimestampToTime(lastUpdate)
    }
    fun getFullImageUrl () : String{
        return BASE_IMAGE_URL + imageUrl
    }
}