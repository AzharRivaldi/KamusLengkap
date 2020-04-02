package com.azhar.kamus.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Azhar Rivaldi on 31-03-2020.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    public static String DATABASE_NAME = "db_kamus";

    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_ENGLISH = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            DatabaseContract.TABLE_ENGLISH,
            DatabaseContract.KamusColumns._ID,
            DatabaseContract.KamusColumns.KATA,
            DatabaseContract.KamusColumns.DESKRIPSI
    );

    private static final String CREATE_TABLE_INDONESIA = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            DatabaseContract.TABLE_INDONESIA,
            DatabaseContract.KamusColumns._ID,
            DatabaseContract.KamusColumns.KATA,
            DatabaseContract.KamusColumns.DESKRIPSI
    );

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ENGLISH);
        db.execSQL(CREATE_TABLE_INDONESIA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_ENGLISH);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_INDONESIA);
        onCreate(db);
    }
}
