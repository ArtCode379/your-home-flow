package yourshopmarket.household.yourhomeflow.di

import yourshopmarket.household.yourhomeflow.data.repository.CartRepository
import yourshopmarket.household.yourhomeflow.data.repository.IMVXBOnboardingRepo
import yourshopmarket.household.yourhomeflow.data.repository.OrderRepository
import yourshopmarket.household.yourhomeflow.data.repository.ProductRepository

import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        IMVXBOnboardingRepo(
            imvxbOnboardingStoreManager = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single { ProductRepository() }

    single {
        CartRepository(
            cartItemDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single {
        OrderRepository(
            orderDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }
}