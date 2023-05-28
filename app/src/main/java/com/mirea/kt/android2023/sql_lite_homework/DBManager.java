package com.mirea.kt.android2023.sql_lite_homework;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBManager {

    private SQLiteOpenHelper sqLiteOpenHelper;

    public DBManager(SQLiteOpenHelper sqLiteOpenHelper) {
        this.sqLiteOpenHelper = sqLiteOpenHelper;
    }

    public boolean save(Car car) {
        SQLiteDatabase database = sqLiteOpenHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("model", car.getModel());
        contentValues.put("number", car.getNumber());
        contentValues.put("year", car.getYear());

        long rowId = database.insert("CARS", null, contentValues);
        contentValues.clear();
        database.close();

        return rowId != -1;
    }

    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        SQLiteDatabase database = sqLiteOpenHelper.getWritableDatabase();
        Cursor cursor = database.query("CARS",
                null, null, null,
                null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String model = cursor.getString(cursor.getColumnIndexOrThrow("model"));
                String number = cursor.getString(cursor.getColumnIndexOrThrow("number"));
                int year = cursor.getInt(cursor.getColumnIndexOrThrow("year"));
                cars.add(new Car(model, number, year));
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return cars;
    }
}
