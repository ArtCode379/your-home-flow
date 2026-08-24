package yourshopmarket.household.yourhomeflow.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import yourshopmarket.household.yourhomeflow.data.model.Product
import yourshopmarket.household.yourhomeflow.data.model.ProductCategory

class ProductRepository {
    private val products = listOf(
        Product(
            1, "Woven Storage Basket", "A sturdy handwoven basket that keeps blankets, toys, and everyday essentials beautifully organised.",
            ProductCategory.HOME, 24.99, "https://images.unsplash.com/photo-1616486338812-3dadae4b4ace?w=1200",
        ),
        Product(
            2, "Stoneware Dinner Set", "A calm, contemporary 12-piece stoneware set for relaxed weekday meals and special gatherings.",
            ProductCategory.KITCHEN, 48.00, "https://images.unsplash.com/photo-1610701596007-11502861dcfa?w=1200",
        ),
        Product(
            3, "Soft Cotton Throw", "Breathable cotton with a tactile woven finish, ideal for layering over a sofa or bed.",
            ProductCategory.TEXTILES, 32.50, "https://images.unsplash.com/photo-1600369671738-fa3a43efeced?w=1200",
        ),
        Product(
            4, "Minimal Desk Planner", "An undated weekly planner with generous space for priorities, notes, and small wins.",
            ProductCategory.STATIONERY, 12.95, "https://images.unsplash.com/photo-1506784983877-45594efa4cbe?w=1200",
        ),
        Product(
            5, "Amber Glass Vase", "A warm amber statement vase that catches the light and suits fresh or dried stems.",
            ProductCategory.ACCESSORIES, 18.75, "https://images.unsplash.com/photo-1618220179428-22790b461013?w=1200",
        ),
        Product(
            6, "Acacia Serving Board", "Responsibly sourced acacia wood with a practical handle for sharing bread, cheese, and snacks.",
            ProductCategory.KITCHEN, 21.00, "https://images.unsplash.com/photo-1556911220-bff31c812dba?w=1200",
        ),
        Product(
            7, "Linen Cushion Cover", "Washed linen texture and a concealed zip create an effortless refresh for any room.",
            ProductCategory.TEXTILES, 16.50, "https://images.unsplash.com/photo-1584100936595-c0654b55a2e2?w=1200",
        ),
        Product(
            8, "Ceramic Table Lamp", "A softly curved ceramic base and linen shade bring a warm glow to bedside tables and reading corners.",
            ProductCategory.HOME, 39.99, "https://images.unsplash.com/photo-1507473885765-e6ed057f782c?w=1200",
        ),
        Product(
            9, "Everyday Notebook Set", "Three thread-bound notebooks with smooth paper for lists, sketches, and bright ideas.",
            ProductCategory.STATIONERY, 9.50, "https://images.unsplash.com/photo-1531346878377-a5be20888e57?w=1200",
        ),
        Product(
            10, "Brass Photo Frame", "A slim brass-tone frame that turns a favourite photograph into a polished shelf accent.",
            ProductCategory.ACCESSORIES, 14.25, "https://images.unsplash.com/photo-1513519245088-0e12902e5a38?w=1200",
        ),
        Product(
            11, "Ribbed Glass Tumblers", "A set of four stackable tumblers with subtle ribbing for water, juice, and cocktails.",
            ProductCategory.KITCHEN, 19.99, "https://images.unsplash.com/photo-1513558161293-cdaf765ed2fd?w=1200",
        ),
        Product(
            12, "Scented Soy Candle", "A clean-burning soy candle with cedar, fig, and soft amber notes for slow evenings at home.",
            ProductCategory.ACCESSORIES, 15.00, "https://images.unsplash.com/photo-1603006905003-be475563bc59?w=1200",
        ),
    )

    fun observeById(id: Int): Flow<Product?> = flowOf(getById(id))

    fun getById(id: Int): Product? = products.find { it.id == id }

    fun observeAll(): Flow<List<Product>> = flowOf(products)
}
