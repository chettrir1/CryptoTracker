package com.chettri.cryptotracker.crypto.data.networking

import android.annotation.SuppressLint
import com.chettri.cryptotracker.core.data.networking.constructUrl
import com.chettri.cryptotracker.core.data.networking.safeCall
import com.chettri.cryptotracker.core.domain.util.NetworkError
import com.chettri.cryptotracker.core.domain.util.Result
import com.chettri.cryptotracker.core.domain.util.map
import com.chettri.cryptotracker.crypto.data.mappers.toCoin
import com.chettri.cryptotracker.crypto.data.mappers.toCoinPrice
import com.chettri.cryptotracker.crypto.data.networking.dto.CoinPriceResponseDto
import com.chettri.cryptotracker.crypto.data.networking.dto.CoinResponseDto
import com.chettri.cryptotracker.crypto.domain.Coin
import com.chettri.cryptotracker.crypto.domain.CoinDataSource
import com.chettri.cryptotracker.crypto.domain.CoinPrice
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.time.ZonedDateTime

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

    @SuppressLint("NewApi")
    override suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime,
    ): Result<List<CoinPrice>, NetworkError> {
        val startMillis = start
            .withZoneSameInstant(java.time.ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()
        val endMillis = end
            .withZoneSameInstant(java.time.ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()
        return safeCall<CoinPriceResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets/$coinId/history")
            ) {
                parameter("interval", "h6")
                parameter("start", startMillis)
                parameter("end", endMillis)
            }
        }.map { response ->
            response.data.map { it.toCoinPrice() }
        }
    }

}