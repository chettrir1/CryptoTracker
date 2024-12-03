package com.chettri.cryptotracker.di

import com.chettri.cryptotracker.core.data.networking.HttpClientFactory
import com.chettri.cryptotracker.crypto.data.networking.RemoteCoinDataSource
import com.chettri.cryptotracker.crypto.domain.CoinDataSource
import com.chettri.cryptotracker.crypto.presentation.CoinListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val cryptoModule = module {

    //Externalize engine
    single { HttpClientFactory.create(CIO.create()) }
    singleOf(::RemoteCoinDataSource).bind<CoinDataSource>()

    viewModel { CoinListViewModel(get()) }
}