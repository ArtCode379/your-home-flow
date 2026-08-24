package yourshopmarket.household.yourhomeflow.ui.composable.screen.productdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import yourshopmarket.household.yourhomeflow.R
import yourshopmarket.household.yourhomeflow.data.model.Product
import yourshopmarket.household.yourhomeflow.ui.composable.shared.IMVXBContentWrapper
import yourshopmarket.household.yourhomeflow.ui.composable.shared.IMVXBEmptyView
import yourshopmarket.household.yourhomeflow.ui.state.DataUiState
import yourshopmarket.household.yourhomeflow.ui.viewmodel.ProductDetailsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductDetailsScreen(
    productId: Int,
    modifier: Modifier = Modifier,
    viewModel: ProductDetailsViewModel = koinViewModel(),
) {
    val productState by viewModel.productDetailsState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.observeProductDetails(productId)
    }

    ProductDetailsScreenContent(
        productState = productState,
        modifier = modifier,
        onAddToCart = viewModel::addProductToCart
    )
}

@Composable
private fun ProductDetailsScreenContent(
    productState: DataUiState<Product>,
    modifier: Modifier = Modifier,
    onAddToCart: () -> Unit,
) {
    Column(modifier = modifier) {

        IMVXBContentWrapper(
            dataState = productState,

            dataPopulated = {
                val data = (productState as DataUiState.Populated).data

            },

            dataEmpty = {
                IMVXBEmptyView(
                    primaryText = stringResource(R.string.imvxb_product_details_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}