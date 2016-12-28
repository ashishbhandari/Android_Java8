package com.dataorganizer.persistence;

import android.provider.BaseColumns;

/**
 * Created by b_ashish on 21-Dec-16.
 */

public interface VanStockTable {

    String NAME = "vanstock";

    String COLUMN_ID = BaseColumns._ID;
    String COLUMN_INVENTORY_ITEM_ID = "inventory_item_id";
    String COLUMN_PRODUCT_CODE = "product_code";
    String COLUMN_PRODUCT_DESC = "product_desc";
    String COLUMN_MODEL = "model";

    String COLUMN_ORGANIZATION_ID = "oraganization_id";
    String COLUMN_LOCATION_CODE = "location_code";
    String COLUMN_QUANTITY = "quantity";
    String COLUMN_ITEM_COST = "item_cost";

    String COLUMN_STATUS = "status";
    String COLUMN_ITEM_COLOR = "item_color";
    String COLUMN_ITEM_COLOR_DESC = "item_color_desc";
    String COLUMN_BRAND_CODE = "brand_code";

    String COLUMN_BRAND_DESC = "brand_desc";
    String COLUMN_CATEGORY_CODE = "category_code";
    String COLUMN_CATEGORY_DESC = "category_desc";

    String[] PROJECTION = new String[]{COLUMN_ID, COLUMN_INVENTORY_ITEM_ID,
            COLUMN_PRODUCT_CODE, COLUMN_PRODUCT_DESC,
            COLUMN_MODEL, COLUMN_ORGANIZATION_ID,
            COLUMN_LOCATION_CODE, COLUMN_QUANTITY,
            COLUMN_ITEM_COST, COLUMN_STATUS,
            COLUMN_ITEM_COLOR, COLUMN_ITEM_COLOR_DESC,
            COLUMN_BRAND_CODE, COLUMN_BRAND_DESC,
            COLUMN_CATEGORY_CODE, COLUMN_CATEGORY_DESC};

    String CREATE = "CREATE TABLE " + NAME + " ("
            + COLUMN_ID + " TEXT PRIMARY KEY, "
            + COLUMN_INVENTORY_ITEM_ID + " TEXT, "
            + COLUMN_PRODUCT_CODE + " TEXT, "
            + COLUMN_PRODUCT_DESC + " TEXT, "
            + COLUMN_MODEL + " TEXT, "
            + COLUMN_ORGANIZATION_ID + " TEXT, "
            + COLUMN_LOCATION_CODE + " TEXT, "
            + COLUMN_QUANTITY + " TEXT, "
            + COLUMN_ITEM_COST + " TEXT, "
            + COLUMN_STATUS + " TEXT, "
            + COLUMN_ITEM_COLOR + " TEXT, "
            + COLUMN_ITEM_COLOR_DESC + " TEXT, "
            + COLUMN_BRAND_CODE + " TEXT, "
            + COLUMN_BRAND_DESC + " TEXT, "
            + COLUMN_CATEGORY_CODE + " TEXT, "
            + COLUMN_CATEGORY_DESC + " TEXT);";

}
