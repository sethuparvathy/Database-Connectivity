package com.example.sethu.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 1/29/2019.
 */
public class databasehelp  extends SQLiteOpenHelper {
    public static final String Dbname = "MyDb.db";
    public static final String Tablename = "student";
    public static final String col1 = "id";
    public static final String col2 = "name";
    public static final String col3 = "email";

    public databasehelp(Context context) {

        super(context, Dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "create table " + Tablename + "( " + col1 + " integer primary key autoincrement," + col2 + " text," + col3 + " text)";
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "drop table if exists" + Tablename;
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);

    }

    public boolean insertData(String name, String email) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(col2, name);
        content.put(col3, email);
        Long status = sqLiteDatabase.insert(Tablename, null, content);
        if (status == -1) {
            return false;
        } else

        {
            return true;
        }
    }

    public Cursor searchdata(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM " + Tablename + " WHERE " + col2 + "='" + name + "'", null);
        return cur;
    }

    public boolean updatedata(String id, String email) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(col3, email);
        long status = sqLiteDatabase.update(Tablename, content, col1 + "=" + id, null);
        if (status == -1) {
            return false;
        } else {
            return true;
        }

    }

    public boolean deletedata(String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        long status = database.delete(Tablename, col1 + "=" + id, null);
        if (status == -1) {
            return false;
        } else {
            return true;
        }

    }
}