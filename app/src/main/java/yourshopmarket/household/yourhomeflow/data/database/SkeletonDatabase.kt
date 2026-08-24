package yourshopmarket.household.yourhomeflow.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import yourshopmarket.household.yourhomeflow.data.dao.CartItemDao
import yourshopmarket.household.yourhomeflow.data.dao.OrderDao
import yourshopmarket.household.yourhomeflow.data.database.converter.Converters
import yourshopmarket.household.yourhomeflow.data.entity.CartItemEntity
import yourshopmarket.household.yourhomeflow.data.entity.OrderEntity

@Database(
    entities = [CartItemEntity::class, OrderEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class IMVXBDatabase : RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao

    abstract fun orderDao(): OrderDao
}