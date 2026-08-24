package yourshopmarket.household.yourhomeflow.ui.composable.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import yourshopmarket.household.yourhomeflow.R
import yourshopmarket.household.yourhomeflow.data.model.Product
import yourshopmarket.household.yourhomeflow.ui.composable.shared.IMVXBContentWrapper
import yourshopmarket.household.yourhomeflow.ui.composable.shared.IMVXBEmptyView
import yourshopmarket.household.yourhomeflow.ui.state.DataUiState
import yourshopmarket.household.yourhomeflow.ui.viewmodel.ProductViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = koinViewModel(),
    onNavigateToProductDetails: (productId: Int) -> Unit,
) {
    val productsState by viewModel.productsState.collectAsState()

    HomeContent(
        productsState = productsState,
        modifier = modifier,
        onNavigateToProductDetails = onNavigateToProductDetails,
        onAddProductToCart = viewModel::addToCart,
    )
}

@Composable
private fun HomeContent(
    productsState: DataUiState<List<Product>>,
    modifier: Modifier = Modifier,
    onNavigateToProductDetails: (productId: Int) -> Unit,
    onAddProductToCart: (productId: Int) -> Unit,
) {
    Column(modifier = modifier) {

        IMVXBContentWrapper(
            dataState = productsState,

            dataPopulated = {
                val data = (productsState as DataUiState.Populated).data
            },

            dataEmpty = {
                IMVXBEmptyView(
                    primaryText = stringResource(R.string.imvxb_products_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}