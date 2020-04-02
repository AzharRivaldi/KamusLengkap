package com.azhar.kamus.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.azhar.kamus.model.ModelKamus;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.azhar.kamus.database.DatabaseContract.KamusColumns.DESKRIPSI;
import static com.azhar.kamus.database.DatabaseContract.KamusColumns.KATA;
import static com.azhar.kamus.database.DatabaseContract.TABLE_ENGLISH;
import static com.azhar.kamus.database.DatabaseContract.TABLE_INDONESIA;

/**
 * Created by Azhar Rivaldi on 31-03-2020.
 */

public class KamusHelper {

    private Context context;
    private DatabaseHelper dataBaseHelper;

    private SQLiteDatabase database;

    private String table;

    public KamusHelper(Context context){
        this.context = context;
    }

    private void checkLanguage(boolean language){
        if (language){ //language is true (1)
            table = TABLE_ENGLISH;
        }
        else { //language is false (0)
            table = TABLE_INDONESIA;
        }
    }

    public KamusHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dataBaseHelper.close();
    }

    public ArrayList<ModelKamus> selectAll(boolean language){
        checkLanguage(language);

        Cursor cursor = database.query(table,null,null,null,null,null,_ID+ " ASC",null);
        cursor.moveToFirst();
        ArrayList<ModelKamus> arrayList = new ArrayList<>();
        ModelKamus kamus;
        if (cursor.getCount()>0) {
            do {
                kamus = new ModelKamus(cursor.getString(cursor.getColumnIndexOrThrow(KATA)), cursor.getString(cursor.getColumnIndexOrThrow(DESKRIPSI)));
                arrayList.add(kamus);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<ModelKamus> selectByKata(String kata, boolean language){
        checkLanguage(language);

        Cursor cursor = database.rawQuery("SELECT * FROM "+table+" WHERE kata LIKE '"+kata+"%'", null);
        cursor.moveToFirst();
        ArrayList<ModelKamus> arrayList = new ArrayList<>();
        ModelKamus kamus;
        if (cursor.getCount()>0) {
            do {
                kamus = new ModelKamus(cursor.getString(cursor.getColumnIndexOrThrow(KATA)), cursor.getString(cursor.getColumnIndexOrThrow(DESKRIPSI)));
                arrayList.add(kamus);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(ModelKamus kamus, boolean language){
        checkLanguage(language);

        ContentValues initialValues =  new ContentValues();
        initialValues.put(KATA, kamus.getKata());
        initialValues.put(DESKRIPSI, kamus.getDeskripsi());
        return database.insert(table, null, initialValues);
    }

    public int update(ModelKamus kamus, boolean language){
        checkLanguage(language);

        ContentValues args = new ContentValues();
        args.put(KATA, kamus.getKata());
        args.put(DESKRIPSI, kamus.getDeskripsi());
        return database.update(table, args, _ID + "= '" + kamus.getId() + "'", null);
    }

    public int delete(int id ,boolean language){
        checkLanguage(language);
        return database.delete(table, _ID + " = '"+id+"'", null);
    }

    public void insertTransaction(ArrayList<ModelKamus> listKamus, boolean language){
        checkLanguage(language);

        String sql = "INSERT INTO "+table+" ("+KATA+", "+DESKRIPSI
                +") VALUES (?, ?)";

        database.beginTransaction();

        SQLiteStatement stmt = database.compileStatement(sql);
        for (int i = 0; i < listKamus.size(); i++) {
            stmt.bindString(1, listKamus.get(i).getKata());
            stmt.bindString(2, listKamus.get(i).getDeskripsi());
            stmt.execute();
            stmt.clearBindings();
        }
        database.setTransactionSuccessful();
        database.endTransaction();
    }
}
