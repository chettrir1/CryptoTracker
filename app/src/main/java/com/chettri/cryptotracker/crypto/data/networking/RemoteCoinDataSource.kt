package com.chettri.cryptotracker.crypto.data.networking

import com.chettri.cryptotracker.core.data.networking.constructUrl
import com.chettri.cryptotracker.core.data.networking.safeCall
import com.chettri.cryptotracker.core.domain.util.NetworkError
import com.chettri.cryptotracker.core.domain.util.Result
import com.chettri.cryptotracker.core.domain.util.map
import com.chettri.cryptotracker.crypto.data.mappers.toCoin
import com.chettri.cryptotracker.crypto.data.networking.dto.CoinResponseDto
import com.chettri.cryptotracker.crypto.domain.Coin
import com.chettri.cryptotracker.crypto.domain.CoinDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteCoinDataSource(
    private val httpClient: HttpClient,
) : CoinDataSource {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            )
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }
}