package yourshopmarket.household.yourhomeflow.di

import yourshopmarket.household.yourhomeflow.data.datastore.IMVXBOnboardingPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { IMVXBOnboardingPrefs(androidContext()) }
}