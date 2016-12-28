package com.dataorganizer.model;

/**
 * Created by b_ashish on 21-Dec-16.
 */

public class VanStock {

    public final String INVENTORY_ITEM_ID;
    public final String PRODUCT_CODE;
    public final String PRODUCT_DESC;

    public final String MODEL;
    public final int ORGANIZATION_ID;
    public final String LOCATION_CODE;
    public final int QUANTITY;
    public final String ITEM_COST;
    public final String STATUS;
    public final String ITEM_COLOR;
    public final String ITEM_COLOR_DESC;
    public final String BRAND_CODE;
    public final String BRAND_DESC;
    public final String CATEGORY_CODE;
    public final String CATEGORY_DESC;

    public VanStock(String inventory_item_id, String product_code, String product_desc, String model, int organization_id, String location_code, int quantity, String item_cost, String status, String item_color, String item_color_desc, String brand_code, String brand_desc, String category_code, String category_desc) {
        INVENTORY_ITEM_ID = inventory_item_id;
        PRODUCT_CODE = product_code;
        PRODUCT_DESC = product_desc;
        MODEL = model;
        ORGANIZATION_ID = organization_id;
        LOCATION_CODE = location_code;
        QUANTITY = quantity;
        ITEM_COST = item_cost;
        STATUS = status;
        ITEM_COLOR = item_color;
        ITEM_COLOR_DESC = item_color_desc;
        BRAND_CODE = brand_code;
        BRAND_DESC = brand_desc;
        CATEGORY_CODE = category_code;
        CATEGORY_DESC = category_desc;
    }
}
