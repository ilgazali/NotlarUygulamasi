package com.example.notlaruygulamasi;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class NotlarDao {

    public void notEkle( VeriTabani veriTabani,String ders_adi, int not1, int not2){

        SQLiteDatabase sqLiteDatabase = veriTabani.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("ders_adi",ders_adi);
        values.put("not1",not1);
        values.put("not2",not2);

        sqLiteDatabase.insertOrThrow("notlar",null,values);
        sqLiteDatabase.close();
    }


    public void notGuncelle( VeriTabani veriTabani,int not_id,String ders_adi, int not1, int not2){

        SQLiteDatabase sqLiteDatabase = veriTabani.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("ders_adi",ders_adi);
        values.put("not1",not1);
        values.put("not2",not2);

        sqLiteDatabase.update("notlar",values,"not_id=?",new String[]{String.valueOf(not_id)});
        sqLiteDatabase.close();
    }

    public void notSil(VeriTabani veriTabani, int not_id){ //Silmek için verinin id numarası lazım.
        SQLiteDatabase sqLiteDatabase = veriTabani.getWritableDatabase();
        sqLiteDatabase.delete("notlar","not_id=?",new String[]{String.valueOf(not_id)});
        sqLiteDatabase.close();
    }
    public ArrayList<Notlar> tumNotlar(VeriTabani veriTabani){

        ArrayList<Notlar> notlarArrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = veriTabani.getWritableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM notlar",null);

        while (c.moveToNext()){

            Notlar notlar = new Notlar(c.getInt(c.getColumnIndex("not_id")),c.getString(c.getColumnIndex("ders_adi"))
                    ,c.getInt(c.getColumnIndex("not1")),c.getInt(c.getColumnIndex("not2")));
            notlarArrayList.add(notlar);
        }

        return notlarArrayList;
    }

}
