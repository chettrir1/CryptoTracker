package com.chettri.cryptotracker.crypto.presentation.coin_list

import androidx.compose.runtime.Immutable
import com.chettri.cryptotracker.crypto.models.CoinUi

@Immutable
data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<CoinUi> = emptyList(),
    val selectedCoin: CoinUi? = null,
)