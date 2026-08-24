package yourshopmarket.household.yourhomeflow.ui.composable.screen.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import coil3.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel
import yourshopmarket.household.yourhomeflow.ui.viewmodel.IMVXBOnboardingVM

private data class OnboardingPage(val title: String, val body: String, val image: String, val icon: ImageVector)

private val pages = listOf(
    OnboardingPage(
        "Thoughtful finds for every room",
        "Explore practical homeware, textiles, stationery, and accessories selected to make daily life flow better.",
        "https://images.unsplash.com/photo-1616486338812-3dadae4b4ace?w=1000",
        Icons.Default.Home,
    ),
    OnboardingPage(
        "Everything neatly organised",
        "Browse clear categories and save your favourite essentials together in one simple basket.",
        "https://images.unsplash.com/photo-1556911220-bff31c812dba?w=1000",
        Icons.Default.Inventory2,
    ),
    OnboardingPage(
        "Reserve now, collect with ease",
        "Confirm your reservation and your order will be waiting in store for the next 24 hours.",
        "https://images.unsplash.com/photo-1607082349566-187342175e2f?w=1000",
        Icons.Default.ShoppingBag,
    ),
)

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: IMVXBOnboardingVM = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit,
) {
    val completed by viewModel.onboardingSetState.collectAsState()
    val pagerState = rememberPagerState(pageCount = { pages.size })

    LaunchedEffect(completed) {
        if (completed) {
            onNavigateToHomeScreen()
        }
    }

    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HorizontalPager(state = pagerState, modifier = Modifier.weight(1f)) { index ->
            val page = pages[index]
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Icon(page.icon, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(64.dp))
                Spacer(Modifier.height(24.dp))
                Text(page.title, style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(12.dp))
                Text(page.body, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(Modifier.height(28.dp))
                AsyncImage(
                    model = page.image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(180.dp, 120.dp).clip(RoundedCornerShape(20.dp)),
                )
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            pages.indices.forEach { index ->
                Box(
                    Modifier
                        .size(if (index == pagerState.currentPage) 24.dp else 8.dp, 8.dp)
                        .clip(CircleShape)
                        .then(Modifier),
                )
            }
        }
        Spacer(Modifier.height(16.dp))
        if (pagerState.currentPage == pages.lastIndex) {
            Button(onClick = viewModel::setOnboarded, modifier = Modifier.fillMaxWidth()) {
                Text("Get Started")
            }
        } else {
            Button(
                onClick = { viewModel.setOnboarded() },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Skip to shop")
            }
        }
    }
}
