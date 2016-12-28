package com.dataorganizer.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.dataorganizer.model.VanStock;

/**
 * Created by b_ashish on 21-Dec-16.
 */

public class DataOrganizerDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DataOrganizerDatabaseHelper";
    private static final String DB_NAME = "dataorg";
    private static final String DB_SUFFIX = ".db";
    private static final int DB_VERSION = 1;
    private final Resources mResources;

    private static DataOrganizerDatabaseHelper mInstance;

    public DataOrganizerDatabaseHelper(Context context) {
        //prevents external instance creation
        super(context, DB_NAME + DB_SUFFIX, null, DB_VERSION);
        mResources = context.getResources();
    }

    public static DataOrganizerDatabaseHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DataOrganizerDatabaseHelper(context);
        }
        return mInstance;
    }

    private static SQLiteDatabase getReadableDatabase(Context context) {
        return getInstance(context).getReadableDatabase();
    }

    private static SQLiteDatabase getWritableDatabase(Context context) {
        return getInstance(context).getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(VanStockTable.CREATE);
//        preFillDatabase(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static void emptyVanStockTable(@NonNull Context context) {
        SQLiteDatabase db = getWritableDatabase(context);
        int delete = db.delete(VanStockTable.NAME, null, null);
    }

    public static void saveVanStocks(Context context, VanStock[] vanStocks) {
        ContentValues values = new ContentValues(); // reduce, reuse
        SQLiteDatabase db = getReadableDatabase(context);
        fillVanStocks(db, values, vanStocks);
    }

    private static void fillVanStocks(SQLiteDatabase db, ContentValues values, VanStock[] vanStocks) {
        for (int i = 0; i < vanStocks.length; i++) {
            values.clear();
            values.put(VanStockTable.COLUMN_INVENTORY_ITEM_ID, vanStocks[i].INVENTORY_ITEM_ID);
            values.put(VanStockTable.COLUMN_PRODUCT_CODE, vanStocks[i].PRODUCT_CODE);
            values.put(VanStockTable.COLUMN_PRODUCT_DESC, vanStocks[i].PRODUCT_DESC);
            values.put(VanStockTable.COLUMN_MODEL, vanStocks[i].MODEL);

            values.put(VanStockTable.COLUMN_ORGANIZATION_ID, vanStocks[i].ORGANIZATION_ID);
            values.put(VanStockTable.COLUMN_LOCATION_CODE, vanStocks[i].LOCATION_CODE);
            values.put(VanStockTable.COLUMN_QUANTITY, vanStocks[i].QUANTITY);

            values.put(VanStockTable.COLUMN_ITEM_COST, vanStocks[i].ITEM_COST);
            values.put(VanStockTable.COLUMN_STATUS, vanStocks[i].STATUS);
            values.put(VanStockTable.COLUMN_ITEM_COLOR, vanStocks[i].ITEM_COLOR);

            values.put(VanStockTable.COLUMN_ITEM_COLOR_DESC, vanStocks[i].ITEM_COLOR_DESC);
            values.put(VanStockTable.COLUMN_BRAND_CODE, vanStocks[i].BRAND_CODE);
            values.put(VanStockTable.COLUMN_BRAND_DESC, vanStocks[i].BRAND_DESC);
            values.put(VanStockTable.COLUMN_CATEGORY_CODE, vanStocks[i].CATEGORY_CODE);
            values.put(VanStockTable.COLUMN_CATEGORY_DESC, vanStocks[i].CATEGORY_DESC);

            long insert = db.insert(VanStockTable.NAME, null, values);
//            Log.e(TAG, "inserted row id : " + insert);
        }
    }

//    private static List<VanStock> getVanStocks(final String categoryId, SQLiteDatabase database) {
//        final List<VanStock> quizzes = new ArrayList<>();
//        final Cursor cursor = database.query(VanStockTable.NAME, VanStockTable.PROJECTION,
//                QuizTable.FK_CATEGORY + " LIKE ?", new String[]{categoryId}, null, null, null);
//        cursor.moveToFirst();
//        do {
//            quizzes.add(createVanStockToType(cursor));
//        } while (cursor.moveToNext());
//        cursor.close();
//        return quizzes;
//    }
//
//    private static VanStock createVanStockToType(Cursor cursor) {
//        return new VanStock(cursor.getColumnName());
//    }

}
