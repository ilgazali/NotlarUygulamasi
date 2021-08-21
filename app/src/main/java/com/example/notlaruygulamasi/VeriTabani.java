package com.example.notlaruygulamasi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class VeriTabani extends SQLiteOpenHelper {

    public VeriTabani(@Nullable Context context) {
        super(context, "notlar", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE notlar(not_id INTEGER PRIMARY KEY AUTOINCREMENT, ders_adi TEXT,not1 INTEGER,not2 INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS notlar");
        onCreate(sqLiteDatabase);
    }
}
