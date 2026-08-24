package yourshopmarket.household.yourhomeflow.ui.composable.screen.order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import yourshopmarket.household.yourhomeflow.R
import yourshopmarket.household.yourhomeflow.data.entity.OrderEntity
import yourshopmarket.household.yourhomeflow.ui.composable.shared.IMVXBContentWrapper
import yourshopmarket.household.yourhomeflow.ui.composable.shared.IMVXBEmptyView
import yourshopmarket.household.yourhomeflow.ui.state.DataUiState
import yourshopmarket.household.yourhomeflow.ui.viewmodel.OrderViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrdersScreen(
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel = koinViewModel(),
) {
    val ordersState by viewModel.ordersState.collectAsState()

    OrdersContent(
        ordersState = ordersState,
        modifier = modifier,
    )
}

@Composable
private fun OrdersContent(
    ordersState: DataUiState<List<OrderEntity>>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {

        IMVXBContentWrapper(
            dataState = ordersState,

            dataPopulated = {
                val data = (ordersState as DataUiState.Populated).data

            },

            dataEmpty = {
                IMVXBEmptyView(
                    primaryText = stringResource(R.string.imvxb_orders_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}