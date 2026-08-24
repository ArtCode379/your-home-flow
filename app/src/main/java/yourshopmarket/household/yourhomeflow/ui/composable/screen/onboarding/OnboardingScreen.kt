package yourshopmarket.household.yourhomeflow.ui.composable.screen.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import yourshopmarket.household.yourhomeflow.R
import yourshopmarket.household.yourhomeflow.ui.viewmodel.IMVXBOnboardingVM
import org.koin.androidx.compose.koinViewModel

data class OnboardingContent(
    @field:StringRes val titleRes: Int,
    @field:StringRes val descriptionRes: Int,
    @field:DrawableRes val imageRes: Int
)

private val onboardingPagesContent = listOf<OnboardingContent>(
    OnboardingContent(
        titleRes = R.string.imvxb_page_1_title,
        descriptionRes = R.string.imvxb_page_1_description,
        imageRes = R.drawable.imvxb_ic_launcher_background,
    ),
    OnboardingContent(
        titleRes = R.string.imvxb_page_2_title,
        descriptionRes = R.string.imvxb_page_2_description,
        imageRes = R.drawable.imvxb_ic_launcher_background,
    ),
    OnboardingContent(
        titleRes = R.string.imvxb_page_2_title,
        descriptionRes = R.string.imvxb_page_3_description,
        imageRes = R.drawable.imvxb_ic_launcher_background,
    ),
)

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: IMVXBOnboardingVM = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit,
) {
    val onboardingSetState by viewModel.onboardingSetState.collectAsState()

    LaunchedEffect(onboardingSetState) {
        if (onboardingSetState) {
            onNavigateToHomeScreen()
        }
    }


}