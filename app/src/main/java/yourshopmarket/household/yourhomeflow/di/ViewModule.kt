package yourshopmarket.household.yourhomeflow.di

import yourshopmarket.household.yourhomeflow.ui.viewmodel.AppViewModel
import yourshopmarket.household.yourhomeflow.ui.viewmodel.CartViewModel
import yourshopmarket.household.yourhomeflow.ui.viewmodel.CheckoutViewModel
import yourshopmarket.household.yourhomeflow.ui.viewmodel.IMVXBOnboardingVM
import yourshopmarket.household.yourhomeflow.ui.viewmodel.OrderViewModel
import yourshopmarket.household.yourhomeflow.ui.viewmodel.ProductDetailsViewModel
import yourshopmarket.household.yourhomeflow.ui.viewmodel.ProductViewModel
import yourshopmarket.household.yourhomeflow.ui.viewmodel.IMVXBSplashVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        AppViewModel(
            cartRepository = get()
        )
    }

    viewModel {
        IMVXBSplashVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        IMVXBOnboardingVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        ProductViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        ProductDetailsViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            cartRepository = get(),
            productRepository = get(),
            orderRepository = get(),
        )
    }

    viewModel {
        CartViewModel(
            cartRepository = get(),
            productRepository = get(),
        )
    }

    viewModel {
        OrderViewModel(
            orderRepository = get(),
        )
    }
}