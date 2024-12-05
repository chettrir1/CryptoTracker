@file:OptIn(ExperimentalMaterial3AdaptiveApi::class)

package com.chettri.cryptotracker.core.navigation

import android.widget.Toast
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chettri.cryptotracker.core.domain.util.toString
import com.chettri.cryptotracker.core.presentation.util.observeAsEvents
import com.chettri.cryptotracker.crypto.presentation.CoinListViewModel
import com.chettri.cryptotracker.crypto.presentation.coin_detail.CoinDetailScreen
import com.chettri.cryptotracker.crypto.presentation.coin_list.CoinListAction
import com.chettri.cryptotracker.crypto.presentation.coin_list.CoinListEvent
import com.chettri.cryptotracker.crypto.presentation.coin_list.CoinListScreen
import com.chettri.cryptotracker.crypto.presentation.coin_list.CoinListState
import org.koin.androidx.compose.koinViewModel

@Composable
fun AdaptiveCoinListDetailPane(
    viewModel: CoinListViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val state: CoinListState by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    observeAsEvents(events = viewModel.events) { event ->
        when (event) {
            is CoinListEvent.Error -> {
                Toast.makeText(
                    context,
                    event.error.toString(context),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    NavigableListDetailPaneScaffold(
        navigator = navigator,
        listPane = {
            AnimatedPane {
                CoinListScreen(
                    state = state,
                    modifier = modifier,
                    onAction = { action ->
                        viewModel.onAction(action)
                        when (action) {
                            is CoinListAction.OnCoinClick -> {
                                navigator.navigateTo(
                                    pane = ListDetailPaneScaffoldRole.Detail
                                )
                            }

                            CoinListAction.OnRefresh -> TODO()
                        }
                    }
                )
            }
        },
        detailPane = {
            AnimatedPane {
                CoinDetailScreen(
                    state = state
                )
            }
        },
        modifier = modifier
    )
}