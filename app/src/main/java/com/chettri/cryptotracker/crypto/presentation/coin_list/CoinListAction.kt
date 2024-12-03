package com.chettri.cryptotracker.crypto.presentation.coin_list

import com.chettri.cryptotracker.crypto.models.CoinUi

sealed interface CoinListAction {
    data class OnCoinClick(val coinUi: CoinUi) : CoinListAction
    data object OnRefresh : CoinListAction
}