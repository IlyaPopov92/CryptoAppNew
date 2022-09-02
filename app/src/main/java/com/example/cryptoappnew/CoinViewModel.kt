package com.example.cryptoappnew

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.cryptoappnew.api.ApiFactory
import com.example.cryptoappnew.database.AppDataBase
import com.example.cryptoappnew.pojo.CoinPriceInfo
import com.example.cryptoappnew.pojo.CoinPriceInfoRawData
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDataBase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    val priceList = db.coinPriceInfoDao().getPriceList()

    fun getDetailInfoCoin (fsym : String) : LiveData<CoinPriceInfo>{
        return db.coinPriceInfoDao().getPriceInfoCoin(fsym)
    }

    //в этом бллоке будет вызыватьсся автоматически при создании класса/объекта
    init {
        loadData()
    }



    private fun loadData() {
        val disposable = ApiFactory.apiService.getTopCoinsInfo(limit = 50)
            .map { it.data?.map { it.coinInfo?.name }?.joinToString(",") }
            .flatMap { ApiFactory.apiService.getFullPriceList(fsyms = it) }
            .map { getPriceListFromRawData(it) }
                // через какое время будет происходить загрузка данных
            .delaySubscription(10, TimeUnit.SECONDS)
                //repeat() этот метод будет повторять загрузку в чейн будет продолжаться до тех пор пока все идет успешно
            .repeat()
            //то же что и repeat(), но будет повторять загрузку если все прошло не успешно
            .retry()
            .subscribeOn(Schedulers.io())
            .subscribe({
                db.coinPriceInfoDao().insertPriceList(it)
                Log.d("TEST_OF_LOAD", "Success: $it")
            }, {
                Log.d("TEST_OF_LOAD", "Fatal: " + it.message.toString())
            })
        compositeDisposable.add(disposable)
    }

    private fun getPriceListFromRawData(
        coinPriceInfoRawData: CoinPriceInfoRawData
    ): List<CoinPriceInfo> {
        val result = ArrayList<CoinPriceInfo>()
        val jsonObject = coinPriceInfoRawData.coinPriceInfoJsonObject ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinPriceInfo::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}

