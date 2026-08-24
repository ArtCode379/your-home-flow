package yourshopmarket.household.yourhomeflow.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import yourshopmarket.household.yourhomeflow.data.repository.IMVXBOnboardingRepo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class IMVXBSplashVM(
    private val onboardingRepository: IMVXBOnboardingRepo,
) : ViewModel() {
    val onboardedState: StateFlow<Boolean> =
        onboardingRepository.observeOnboardingState()
            .map { it == true }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = false
            )

}