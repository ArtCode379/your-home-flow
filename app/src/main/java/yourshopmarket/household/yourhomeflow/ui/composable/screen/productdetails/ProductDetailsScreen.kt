package yourshopmarket.household.yourhomeflow.ui.composable.screen.productdetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel
import yourshopmarket.household.yourhomeflow.R
import yourshopmarket.household.yourhomeflow.data.model.Product
import yourshopmarket.household.yourhomeflow.ui.composable.shared.IMVXBContentWrapper
import yourshopmarket.household.yourhomeflow.ui.state.DataUiState
import yourshopmarket.household.yourhomeflow.ui.viewmodel.ProductDetailsViewModel

@Composable
fun ProductDetailsScreen(productId: Int, modifier: Modifier = Modifier, viewModel: ProductDetailsViewModel = koinViewModel()) {
    val state by viewModel.productDetailsState.collectAsState()
    var cartAdded by remember { mutableStateOf(false) }

    LaunchedEffect(productId) {
        viewModel.observeProductDetails(productId)
    }
    LaunchedEffect(cartAdded) {
        if (cartAdded) {
            delay(2000)
            cartAdded = false
        }
    }

    IMVXBContentWrapper(
        dataState = state,
        dataPopulated = {
            ProductDetailsContent(
                product = (state as DataUiState.Populated).data,
                cartAdded = cartAdded,
                modifier = modifier,
                onAdd = {
                    viewModel.addProductToCart()
                    cartAdded = true
                },
            )
        },
        dataEmpty = { Text("This product is no longer available", modifier = Modifier.padding(24.dp)) },
    )
}

@Composable
private fun ProductDetailsContent(product: Product, cartAdded: Boolean, modifier: Modifier, onAdd: () -> Unit) {
    Column(modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        AsyncImage(
            model = product.imageUrl,
            contentDescription = product.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth().height(300.dp).clip(RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp)),
        )
        Column(modifier = Modifier.padding(20.dp)) {
            Text(product.title, style = MaterialTheme.typography.titleLarge)
            Text("£%.2f".format(product.price), style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            Surface(color = MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(50), modifier = Modifier.padding(vertical = 12.dp)) {
                Text(stringResource(product.category.titleRes), modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp))
            }
            Text(product.description, color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.height(24.dp))
            Button(onClick = onAdd, modifier = Modifier.fillMaxWidth()) {
                Text("Add to Cart")
            }
        }
        AnimatedVisibility(visible = cartAdded, enter = slideInVertically { it }, exit = fadeOut()) {
            Surface(color = MaterialTheme.colorScheme.primary, modifier = Modifier.fillMaxWidth()) {
                Text("✓ Added to cart", color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.padding(16.dp))
            }
        }
    }
}
