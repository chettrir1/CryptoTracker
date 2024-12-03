package com.chettri.cryptotracker.crypto.presentation.coin_list

import com.chettri.cryptotracker.core.domain.util.NetworkError

sealed interface CoinListEvent {
    data class Error(val error: NetworkError) : CoinListEvent
}