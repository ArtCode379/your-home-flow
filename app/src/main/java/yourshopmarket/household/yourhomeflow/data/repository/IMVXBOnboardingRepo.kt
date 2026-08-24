package yourshopmarket.household.yourhomeflow.data.repository

import yourshopmarket.household.yourhomeflow.data.datastore.IMVXBOnboardingPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class IMVXBOnboardingRepo(
    private val imvxbOnboardingStoreManager: IMVXBOnboardingPrefs,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeOnboardingState(): Flow<Boolean?> {
        return imvxbOnboardingStoreManager.onboardedStateFlow
    }

    suspend fun setOnboardingState(state: Boolean) {
        withContext(coroutineDispatcher) {
            imvxbOnboardingStoreManager.setOnboardedState(state)
        }
    }
}