package com.example.truong.onthi;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static com.example.truong.onthi.DBManager.ID_SX;
import static com.example.truong.onthi.DBManager.NAME_SX;
import static com.example.truong.onthi.DBManager.TABLE_NAME_SX;

public class PhoneDAO
{
    private DBManager dbManager;

    public PhoneDAO(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public List<Phone> getAllPhone()
    {
        SQLiteDatabase sqLiteDatabase = dbManager.getReadableDatabase();
        List<Phone> list = new ArrayList<>();
        String SELECT_PHONE = "SELECT * FROM " + DBManager.TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_PHONE,null);
        if (cursor == null){
            return null;
        }
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int ID = cursor.getInt(cursor.getColumnIndex(DBManager.ID));
            String Name = cursor.getString(cursor.getColumnIndex(DBManager.NAME));
            int Price = cursor.getInt(cursor.getColumnIndex(DBManager.PRICE));
            String Pro_id  = cursor.getString(cursor.getColumnIndex(DBManager.PRO_ID));

            list.add(new Phone(ID,Name,Price,Pro_id));
            cursor.moveToNext();
        }
        cursor.close();
        sqLiteDatabase.close();
        return list;
    }

    public long insertSX(PhoneNameSX phoneNameSX)
    {
        SQLiteDatabase sqLiteDatabase = dbManager.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SX, phoneNameSX.idSX);
        contentValues.put(NAME_SX, phoneNameSX.nameSX);

        long result = sqLiteDatabase.insert(TABLE_NAME_SX, null, contentValues);
        return result;
    }

    public List<PhoneNameSX> listAllPhoneNameSX()
    {
        SQLiteDatabase sqLiteDatabase = dbManager.getReadableDatabase();
        List<PhoneNameSX> listsx = new ArrayList<>();
        String SELECT_QUERRY_SX = "SELECT * FROM " + TABLE_NAME_SX;

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_QUERRY_SX, null);

        if (cursor == null)
        {
            return null;
        }

        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            int IDSX = cursor.getInt(cursor.getColumnIndex(DBManager.ID));
            String NameSX = cursor.getString(cursor.getColumnIndex(DBManager.NAME));

            listsx.add(new PhoneNameSX(IDSX,NameSX));
            cursor.moveToNext();
        }
        cursor.close();
        sqLiteDatabase.close();
        return listsx;
    }

    public long insert(Phone phone)
    {
        SQLiteDatabase sqLiteDatabase = dbManager.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBManager.ID, phone.id);
        contentValues.put(DBManager.NAME, phone.name);
        contentValues.put(DBManager.PRICE, phone.price);
        contentValues.put(DBManager.PRO_ID, phone.pro_id);

        long result = sqLiteDatabase.insert(DBManager.TABLE_NAME, null, contentValues);
        Log.d("Khongcogiak", "insert: "+result);
        return result;
    }

    public long update(Phone phone)
    {
        SQLiteDatabase sqLiteDatabase = dbManager.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBManager.ID,phone.id);
        contentValues.put(DBManager.NAME,phone.name);
        contentValues.put(DBManager.PRICE,phone.price);
        contentValues.put(DBManager.PRO_ID,phone.pro_id);

        long result = sqLiteDatabase.update(DBManager.TABLE_NAME, contentValues, DBManager.ID + "=?", new String[]{String.valueOf(phone.id)});
        Log.d("givay", "update: "+result);
        return result;
    }

    public long delete(Phone phone){
        SQLiteDatabase sqLiteDatabase = dbManager.getReadableDatabase();

        long result = sqLiteDatabase.delete(DBManager.TABLE_NAME, DBManager.ID + "=?", new String[]{String.valueOf(phone.id)});
        return result;
    }
}
