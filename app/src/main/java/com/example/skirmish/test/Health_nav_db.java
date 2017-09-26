package com.example.skirmish.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

/**
 * Created by skirmish on 3/1/17.
 */

public class Health_nav_db extends SQLiteOpenHelper {


    private static final int DB_VERSION = 10;
    private static final String DB_NAME = "health_nav.db";
    private static final String TABLE_NAME = "health_nav";
    private static final String C_ID = "id";
    private static final String C_NAME = "name";
    private static final String C_USER = "user";
    private static final String C_PASS = "pass";
    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table health_nav (name text not null , user text primary key , pass text not null)";

    public Health_nav_db(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db=db;
        ContentValues v = new ContentValues();
        v.put(C_NAME,"hn1");
        v.put(C_USER,"hn1");
        v.put(C_PASS,"hn1");
        db.insert(TABLE_NAME,null,v);
       // insertx();
    }

    public Cursor getAllData () {

        String buildSQL = "SELECT rowid _id, * FROM health_nav";
        return db.rawQuery(buildSQL, null);
    }

    public Cursor getCursor(String str) {
        Cursor mCursor = null;
        if (str == null || str.length() == 0) {
            mCursor = getAllData();
        } else {
            db = this.getReadableDatabase();
            String q = "SELECT rowid _id,* FROM health_nav WHERE user LIKE 'kj' AND name LIKE ?" ;
            Cursor c  = db.rawQuery(q, new String[]{"%"+str + "%"});
            //Cursor c = db.query(TABLE_NAME, new String[]{"rowid _id" ,C_NAME, C_USER}, "name like " + "'%" + str + "%'", null, null, null, null);
            c.moveToFirst();
            mCursor = c;
        }
        return mCursor;
    }

    public void insertx(){
        db=this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(C_NAME,"kashish");
        v.put(C_USER,"kj");
        v.put(C_PASS,"kj");
        db.insert(TABLE_NAME,null,v);
       // db.close();
    }

    public void insert(health_nav hn){
        db=this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(C_NAME,hn.getName());
        v.put(C_USER,hn.getUsername());
        v.put(C_PASS,hn.getPassword());
        db.insert(TABLE_NAME,null,v);
    }

    public String getName(String usr){
        db=this.getReadableDatabase();
        String q = "select name from health_nav where user =? %'"+ usr+"%'";
        Cursor c = db.query(TABLE_NAME, new String[] {"name"}, "user like " + "'%"+usr+"%'", null, null, null, null);
        c.moveToFirst();
        String s = c.getString(0);
        return s;
    }


    public boolean checkusername(String un){
        db=this.getReadableDatabase();
        String q = "select user from health_nav";
        Cursor c = db.rawQuery(q,null);
        c.moveToFirst();
        while(true){
            if((c.getString(0)).equals(un)){
                return false;
            }
            if(c.isLast()){
                break;
            }
            c.moveToNext();
        }
        return true;
    }

    public boolean validate(String u, String p){
        db=this.getReadableDatabase();
        String query = "select user, pass, name from health_nav";
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        String a,b;
        while(true){
            a=cursor.getString(0);
            b=cursor.getString(1);
            if(a.equals(u) && b.equals(p)){
                //n=cursor.getString(2);
                return true;
            }
            if(cursor.isLast()){
                break;
            }
            cursor.moveToNext();
        }
        db.close();

        return false;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS health_nav";
        db.execSQL(query);
        this.onCreate(db);
    }
}
