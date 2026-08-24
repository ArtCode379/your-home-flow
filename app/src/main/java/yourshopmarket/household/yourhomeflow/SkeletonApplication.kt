package yourshopmarket.household.yourhomeflow

import android.app.Application
//[ANY][import_PrepRepository]
import yourshopmarket.household.yourhomeflow.di.dataModule
import yourshopmarket.household.yourhomeflow.di.dispatcherModule
import yourshopmarket.household.yourhomeflow.di.viewModule
//[COMMON][import_DiModule]
//[REFERRER][import_InstallReferrerManager]
//[APPSFLYER][imports_AppsFlyer]
//[FIREBASE][import_FirebaseMessaging]
//[FIREBASE][imports_coroutines]
//[ANY][import_getKoin]
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class IMVXBApplication : Application() {
    //[FIREBASE][appScope]

    override fun onCreate() {
        super.onCreate()

        val appModules = dataModule + viewModule + dispatcherModule /*[COMMON][diModule]*/

        startKoin {
            androidLogger()
            androidContext(this@IMVXBApplication)
            modules(appModules)
        }

        //[ANY][repository]

        //[APPSFLYER][devKey]

        //[APPSFLYER][appsFlyerSettings]

        //[REFERRER][referrerManagerSettings]

        //[APPSFLYER][appsFlyerId]

        //[FIREBASE][FirebaseMessaging]
    }
}
