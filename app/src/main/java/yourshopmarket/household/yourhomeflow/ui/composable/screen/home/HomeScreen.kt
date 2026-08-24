package yourshopmarket.household.yourhomeflow.ui.composable.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import org.koin.androidx.compose.koinViewModel
import yourshopmarket.household.yourhomeflow.R
import yourshopmarket.household.yourhomeflow.data.model.Product
import yourshopmarket.household.yourhomeflow.data.model.ProductCategory
import yourshopmarket.household.yourhomeflow.ui.composable.shared.IMVXBContentWrapper
import yourshopmarket.household.yourhomeflow.ui.state.DataUiState
import yourshopmarket.household.yourhomeflow.ui.viewmodel.ProductViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = koinViewModel(),
    onNavigateToProductDetails: (productId: Int) -> Unit,
) {
    val state by viewModel.productsState.collectAsState()
    IMVXBContentWrapper(
        dataState = state,
        dataPopulated = {
            ProductCatalog(
                products = (state as DataUiState.Populated).data,
                modifier = modifier,
                onProductClick = onNavigateToProductDetails,
            )
        },
        dataEmpty = { Text("Fresh finds are arriving soon", modifier = Modifier.padding(24.dp)) },
    )
}

@Composable
private fun ProductCatalog(products: List<Product>, modifier: Modifier, onProductClick: (Int) -> Unit) {
    var selectedCategory by remember { mutableStateOf<ProductCategory?>(null) }
    val visibleProducts = products.filter { selectedCategory == null || it.category == selectedCategory }
    val featured = products.take(4)
    val pagerState = rememberPagerState(pageCount = { featured.size })

    Column(modifier = modifier.fillMaxSize()) {
        Text("Make home feel effortless", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 8.dp))
        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxWidth().height(190.dp)) { index ->
            val product = featured[index]
            Card(
                modifier = Modifier.padding(horizontal = 16.dp).fillMaxSize().clickable { onProductClick(product.id) },
                shape = RoundedCornerShape(20.dp),
            ) {
                AsyncImage(model = product.imageUrl, contentDescription = product.title, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
            }
        }
        LazyRow(contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            item {
                AssistChip(onClick = { selectedCategory = null }, label = { Text("All") })
            }
            items(ProductCategory.entries) { category ->
                AssistChip(onClick = { selectedCategory = category }, label = { Text(stringResource(category.titleRes)) })
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(visibleProducts, key = { it.id }) { product ->
                ProductCard(product = product, onClick = { onProductClick(product.id) })
            }
        }
    }
}

@Composable
private fun ProductCard(product: Product, onClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().clickable(onClick = onClick), shape = RoundedCornerShape(16.dp)) {
        Column {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().height(130.dp).clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Text(product.title, style = MaterialTheme.typography.titleMedium, maxLines = 2)
                Text(stringResource(product.category.titleRes), style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("£%.2f".format(product.price), color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
