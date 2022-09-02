package com.example.cryptoappnew.api

import com.example.cryptoappnew.pojo.CoinInfoListOfData
import com.example.cryptoappnew.pojo.CoinPriceInfoRawData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TSYM = "tsym"

        private const val QUERY_PARAM_TSYMS = "tsyms"
        private const val QUERY_PARAM_FSYMS = "fsyms"

        private const val CURRENCY = "USD"
    }
    @GET("top/totalvolfull")
    fun getTopCoinsInfo (
        @Query (QUERY_PARAM_API_KEY) apiKey : String = "a3dcfbbba841317c7539b7da8428d80dd0967e52a76c2d4f2ee53b3c6d19e017",
        @Query (QUERY_PARAM_LIMIT) limit : Int = 10,
        @Query (QUERY_PARAM_TSYM) tsym : String = CURRENCY
    ) : Single<CoinInfoListOfData>

    @GET ("pricemultifull")
    fun getFullPriceList (
        @Query (QUERY_PARAM_API_KEY) apiKey : String = "a3dcfbbba841317c7539b7da8428d80dd0967e52a76c2d4f2ee53b3c6d19e017",
        @Query(QUERY_PARAM_FSYMS) fsyms : String,
        @Query (QUERY_PARAM_TSYMS) tsyms : String = CURRENCY
    ) : Single <CoinPriceInfoRawData>
}