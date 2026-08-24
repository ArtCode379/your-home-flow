package yourshopmarket.household.yourhomeflow.ui.composable.screen.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel
import yourshopmarket.household.yourhomeflow.ui.composable.shared.IMVXBContentWrapper
import yourshopmarket.household.yourhomeflow.ui.state.CartItemUiState
import yourshopmarket.household.yourhomeflow.ui.state.DataUiState
import yourshopmarket.household.yourhomeflow.ui.viewmodel.CartViewModel

@Composable
fun CartScreen(modifier: Modifier = Modifier, viewModel: CartViewModel = koinViewModel(), onNavigateToCheckoutScreen: () -> Unit) {
    val state by viewModel.cartItemsState.collectAsStateWithLifecycle()
    val total by viewModel.totalPrice.collectAsStateWithLifecycle()

    IMVXBContentWrapper(
        dataState = state,
        dataPopulated = {
            CartContent(
                items = (state as DataUiState.Populated).data,
                total = total,
                modifier = modifier,
                onPlus = viewModel::incrementProductInCart,
                onMinus = viewModel::decrementItemInCart,
                onRemove = viewModel::deleteFromCart,
                onCheckout = onNavigateToCheckoutScreen,
            )
        },
        dataEmpty = {
            Column(modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Icon(Icons.Default.RemoveShoppingCart, null, modifier = Modifier.size(72.dp), tint = MaterialTheme.colorScheme.primary)
                Text("Your basket is ready for inspiration", style = MaterialTheme.typography.titleLarge)
                Text("Start Shopping from the Home tab", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        },
    )
}

@Composable
private fun CartContent(items: List<CartItemUiState>, total: Double, modifier: Modifier, onPlus: (Int) -> Unit, onMinus: (Int) -> Unit, onRemove: (Int) -> Unit, onCheckout: () -> Unit) {
    Column(modifier.fillMaxSize().padding(16.dp)) {
        LazyColumn(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(items, key = { it.productId }) { item ->
                Card(shape = RoundedCornerShape(16.dp)) {
                    Row(modifier = Modifier.fillMaxWidth().padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                        AsyncImage(model = item.productImageUrl, contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.size(64.dp))
                        Column(modifier = Modifier.weight(1f).padding(horizontal = 12.dp)) {
                            Text(item.productTitle, fontWeight = FontWeight.SemiBold)
                            Text("£%.2f".format(item.productPrice), color = MaterialTheme.colorScheme.primary)
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                OutlinedButton(onClick = { if (item.quantity == 1) onRemove(item.productId) else onMinus(item.productId) }) { Text("−") }
                                Text(item.quantity.toString(), modifier = Modifier.padding(horizontal = 12.dp))
                                OutlinedButton(onClick = { onPlus(item.productId) }) { Text("+") }
                            }
                        }
                        IconButton(onClick = { onRemove(item.productId) }) {
                            Icon(Icons.Default.RemoveShoppingCart, "Remove item")
                        }
                    }
                }
            }
        }
        Text("Subtotal  £%.2f".format(total), modifier = Modifier.fillMaxWidth(), style = MaterialTheme.typography.bodyLarge)
        Text("Total  £%.2f".format(total), style = MaterialTheme.typography.headlineMedium)
        Button(onClick = onCheckout, modifier = Modifier.fillMaxWidth().height(52.dp)) {
            Text("Proceed to Checkout")
        }
    }
}
