package com.chettri.cryptotracker.crypto.domain

import com.chettri.cryptotracker.core.domain.util.NetworkError
import com.chettri.cryptotracker.core.domain.util.Result

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
}