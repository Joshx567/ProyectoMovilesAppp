    package com.calyrsoft.ucbp1.features.dollar.presentation

    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.material3.CircularProgressIndicator
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.collectAsState
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import org.koin.androidx.compose.koinViewModel
    import androidx.compose.foundation.layout.*
    import androidx.compose.material3.*
    import androidx.compose.ui.unit.dp
    import org.koin.androidx.compose.koinViewModel

    @Composable
    fun DollarScreen(viewModelDollar: DollarViewModel = koinViewModel()) {
        val state = viewModelDollar.uiState.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            when (val stateValue = state.value) {
                is DollarViewModel.DollarUIState.Error -> Text(stateValue.message)
                DollarViewModel.DollarUIState.Loading -> CircularProgressIndicator()
                is DollarViewModel.DollarUIState.Success -> {
                    DollarCard(
                        title = "Dólar Oficial",
                        buy = stateValue.data.dollarOfficialBuy ?: "--",
                        sell = stateValue.data.dollarOfficialSell ?: "--"
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    DollarCard(
                        title = "Dólar Paralelo",
                        buy = stateValue.data.dollarParallelBuy ?: "--",
                        sell = stateValue.data.dollarParallelSell ?: "--"
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Última actualización: ${stateValue.data.lastUpdate ?: "--"}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }

    @Composable
    fun DollarCard(title: String, buy: String, sell: String) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            elevation = CardDefaults.cardElevation(6.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = title, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Compra: $buy", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Venta: $sell", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
