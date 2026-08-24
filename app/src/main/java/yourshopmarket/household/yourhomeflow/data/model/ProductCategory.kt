package yourshopmarket.household.yourhomeflow.data.model

import androidx.annotation.StringRes
import yourshopmarket.household.yourhomeflow.R

enum class ProductCategory(@field:StringRes val titleRes: Int) {
    HOME(R.string.imvxb_category_home),
    KITCHEN(R.string.imvxb_category_kitchen),
    TEXTILES(R.string.imvxb_category_textiles),
    STATIONERY(R.string.imvxb_category_stationery),
    ACCESSORIES(R.string.imvxb_category_accessories),
}
