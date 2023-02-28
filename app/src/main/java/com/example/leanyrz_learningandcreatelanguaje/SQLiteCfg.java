package com.example.leanyrz_learningandcreatelanguaje;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteCfg extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDB.db";
    public static final String TABLE_NAME = "ListMenu";

    public static final String ITEM1_TABLE = "idMenu";
    public static final String ITEM2_TABLE = "nameMenu";

    //VERSION Y NOMBRE DE LA BASE DE ATOS
    public SQLiteCfg(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //CREAR LA TABLA Y LAS FILAS
        String menuIdTable = "CREATE TABLE '" + TABLE_NAME + "' (" +

                "'" + ITEM1_TABLE + "' TEXT PRIMARY KEY, " +
                "'" + ITEM2_TABLE + "' TEXT);";
        //EJECUCION DE SQL PARA STRING MenuIdTable
        sqLiteDatabase.execSQL(menuIdTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //SI LA TABLA NO EXISTE ENTONCES CREAR, DE LO CONTRARIO NO CREAR.
        sqLiteDatabase.execSQL("Drop Table If Exists " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
    //METODOS INSERTAR, ACTUALIZAR, BUSCAR, ELIMINAR

    public void insert(String idMenu, String nameMenu){
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(ITEM1_TABLE, idMenu);
            contentValues.put(ITEM2_TABLE, nameMenu);

            db.insert(TABLE_NAME, null, contentValues);
        }catch (Exception ignored){
        }

    }

    public void delete (String idMenu) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.delete(TABLE_NAME, ITEM1_TABLE + " = ?", new String[]{idMenu});
        } catch ( Exception ex) {

        }

    }

    public void update(String idMenu, String nameMenu){
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(ITEM2_TABLE, nameMenu);

            db.update(TABLE_NAME, contentValues,ITEM1_TABLE + " = ?", new String[]{idMenu});
        } catch ( Exception ex) {

        }
    }

    public String search(String idMenu){

        try {
            String value = "";
            SQLiteDatabase db = getReadableDatabase();

            Cursor cursor = db.query(TABLE_NAME,
                    new String[]{ITEM2_TABLE},ITEM1_TABLE+" LIKE ?",
                    new String[]{idMenu},
                    null,null,null,null);

            if (cursor != null && cursor.moveToNext()){
                cursor.moveToFirst();

                value = cursor.getString(0);

                cursor.close();
            }

            return value;

        }catch (Exception exception){

        }



        return "";
    }






}
