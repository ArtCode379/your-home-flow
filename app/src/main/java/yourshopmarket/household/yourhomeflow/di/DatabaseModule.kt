package yourshopmarket.household.yourhomeflow.di

import androidx.room.Room
import yourshopmarket.household.yourhomeflow.data.database.IMVXBDatabase
import org.koin.dsl.module

private const val DB_NAME = "imvxb_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = IMVXBDatabase::class.java,
            name = DB_NAME
        ).build()
    }

    single { get<IMVXBDatabase>().cartItemDao() }

    single { get<IMVXBDatabase>().orderDao() }
}