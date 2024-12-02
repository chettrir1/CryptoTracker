package com.chettri.cryptotracker.crypto.presentation.coin_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.chettri.cryptotracker.crypto.models.toCoinUi
import com.chettri.cryptotracker.crypto.presentation.coin_list.components.CoinListItem
import com.chettri.cryptotracker.crypto.presentation.coin_list.components.previewCoin
import com.chettri.cryptotracker.ui.theme.AppTheme

@Composable
fun CoinListScreen(
    state: CoinListState,
    modifier: Modifier = Modifier,
) {
    if (state.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.coins) { coinUi ->
                CoinListItem(
                    coinUi = coinUi,
                    onClick = {},
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun CoinListScreenPreview() {
    AppTheme {
        CoinListScreen(
            modifier = Modifier.background(
                MaterialTheme.colorScheme.background
            ),
            state = CoinListState(
                coins = (1..100).map {
                    previewCoin.toCoinUi().copy(id = it.toString())
                }
            ),
        )
    }

}