package com.example.parkinGenie;



import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import java.text.SimpleDateFormat;

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "ParkinGenie.db";
    public static final String BOOKING_TABLE_NAME = "bookingPage";
    public static final String DETAILS1 = "carParkId";
    public static final String DETAILS2 = "name";
    public static final String DETAILS3 = "carRegistration";
    public static final String DETAILS4 = "email";
    public static final String DETAILS5 = "phone";
    public static final String DETAILS6 = "dateOfBooking";




    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + BOOKING_TABLE_NAME + "(carParkId INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, " +
                "carRegistration TEXT, email TEXT, phone TEXT, dateOfBooking TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BOOKING_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String carRegistration, String email,
                              String phone, String dateOfBooking){
        SQLiteDatabase myDb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DETAILS2,name);
        contentValues.put(DETAILS3,carRegistration);
        contentValues.put(DETAILS4,email);
        contentValues.put(DETAILS5,phone);
        contentValues.put(DETAILS6,dateOfBooking);

        long result = myDb.insert(BOOKING_TABLE_NAME,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

}



