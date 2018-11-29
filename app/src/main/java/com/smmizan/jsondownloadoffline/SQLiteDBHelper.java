package com.smmizan.jsondownloadoffline;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mizan on 13/11/2018.
 */

public class SQLiteDBHelper extends SQLiteOpenHelper{
    static String DATABASE_NAME="mizandb";

    public static final String TABLE_NAME="mzntables";

    public static final String COL_ID ="id";
    public static final String COL_Name ="productsName";
    public static final String Col_Code ="productsCode";
    public static final String column_products_type ="productsType";
    public static final String COL_Pack_Size ="productsPackSize";
    public static final String COL_Quantity ="productsQuantity";
    public static final String COL_Price ="productsPrice";

    public SQLiteDBHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+ COL_ID +" INTEGER PRIMARY KEY, "+ COL_Name +" VARCHAR, "+ Col_Code +" VARCHAR, "+column_products_type+" VARCHAR, "+COL_Pack_Size+" VARCHAR, "+COL_Quantity+" VARCHAR, "+COL_Price+" VARCHAR )";
        database.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }
}
