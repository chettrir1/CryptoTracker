package com.chettri.cryptotracker.crypto.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinPriceResponseDto(
    val data: List<CoinPriceDto>,
)

@Serializable
data class CoinPriceDto(
    val priceUsd: Double,
    val time: Long,
)
