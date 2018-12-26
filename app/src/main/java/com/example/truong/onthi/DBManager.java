package com.example.truong.onthi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

public class DBManager extends SQLiteOpenHelper
{
    public final static String TABLE_NAME = "Phone";

    public final static String ID = "id";
    public final static String NAME = "name";
    public final static String PRICE = "price";
    public final static String PRO_ID = "pro_id";

    public final static String CREATE_TABLE_PHONE = "CREATE TABLE " + TABLE_NAME + " (" +
            "" + ID + " TEXT PRIMARY KEY, " +
            "" + NAME + " TEXT, " +
            "" + PRICE + " TEXT, " +
            "" + PRO_ID + " TEXT " +
            ")";

    //
    public final static String TABLE_NAME_SX = "Manufacturer";

    public static String ID_SX = "id";
    public final static String NAME_SX = "name";

    public final static String CREATE_TABLE_PHONE_SX = "CREATE TABLE " + TABLE_NAME_SX + " (" +
            "" + ID_SX + " TEXT PRIMARY KEY, " +
            "" + NAME_SX + " TEXT " +
            ")";

    public DBManager(Context context) {
        super(context, "phone.sql", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PHONE);
        db.execSQL(CREATE_TABLE_PHONE_SX);
        Log.d("???", "onCreate: "+CREATE_TABLE_PHONE);
        Log.d("???", "onCreate: "+CREATE_TABLE_PHONE_SX);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
