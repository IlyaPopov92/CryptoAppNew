package com.example.cryptoappnew

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_coin_detail.*

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        if (!intent.hasExtra(EXTRA_STRING_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_STRING_FROM_SYMBOL)
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.getDetailInfoCoin(fromSymbol!!).observe(this, Observer {
            val symbolTemplate = getString(R.string.symbols_template)
            tvSymbols.text = String.format(symbolTemplate, it.fromSymbol, it.toSymbol)
            tvPrice.text = String.format(getString(R.string.price_template), it.price.toString())
            tvLowDay.text = String.format(getString(R.string.min_price_template), it.lowDay)
            tvHighDay.text = String.format(getString(R.string.max_price_template), it.highDay)
            Picasso.get().load(it.getFullImageUrl()).into(ivLogoCoin)
        })
    }

    companion object {
        private const val EXTRA_STRING_FROM_SYMBOL = "fsym"

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_STRING_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}
