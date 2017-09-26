package com.example.skirmish.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

/**
 * Created by skirmish on 3/1/17.
 */

public class Patient_db extends SQLiteOpenHelper {


    private static final int DB_VERSION = 10;
    private static final String DB_NAME = "patient.db";
    private static final String TABLE_NAME = "patient";
    private static final String C_ID = "_id";
    private static final String C_NAME = "name";
    private static final String C_AGE = "age";
    private static final String C_HN = "hn_id";
    private static final String C_PIC = "pic";
    private static final String C_SEX = "sex";
    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table "+TABLE_NAME+" (_id integer primary key AUTOINCREMENT, name text not null , age int not null , hn_id text not null , pic BLOB, sex int not null)";

    public Patient_db(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db=db;
        ContentValues v = new ContentValues();
        //v.put(C_ID,0);
        v.put(C_NAME,"admin");
        v.put(C_AGE,20);
        v.put(C_SEX,1);
        v.put(C_HN,"admin");
        db.insert(TABLE_NAME,null,v);
        // insertx();
    }

    public void insertx(String b, int c, String d, int sex, byte[] e){
        db=this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(C_NAME,b);
        v.put(C_AGE,c);
        v.put(C_HN,d);
        v.put(C_SEX,sex);
        if(e!=null) v.put(C_PIC,e);
        db.insert(TABLE_NAME,null,v);
        // db.close();
    }

    /*public void insert(health_nav hn){
        db=this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(C_NAME,hn.getName());
        v.put(C_USER,hn.getUsername());
        v.put(C_PASS,hn.getPassword());
        db.insert(TABLE_NAME,null,v);
    }*/

    public Cursor getAllData(String usr){
        db=this.getReadableDatabase();
        //String q = "select * from patient where hn_id like ? ";
       // Cursor c = db.rawQuery(q,new String[]{usr});
        Cursor c = db.query(TABLE_NAME, new String[] {C_ID,C_NAME,C_AGE,C_HN,C_PIC,C_SEX}, "hn_id like " + "'%"+usr+"%'", null, null, null, null);
        c.moveToFirst();
        //String s = c.getString(0);
        return c;
    }

    public Cursor getCursor(String str, String usr) {
        Cursor mCursor = null;
        if (str == null || str.length() == 0) {
            mCursor = getAllData(usr);
        } else {
            String q = "SELECT * FROM patient WHERE hn_id LIKE ? AND name LIKE ? OR hn_id LIKE ? AND _id LIKE ?" ;
            Cursor c  = db.rawQuery(q, new String[]{usr,str+"%",usr,str+"%"});
         //   Cursor c = db.query(TABLE_NAME, new String[]{C_ID ,C_NAME, C_AGE, C_HN, C_PIC}, "name like ? and hn_id like ?" + new String[]{"'%" + str + "%'",usr}, null, null, null, null);
            c.moveToFirst();
            mCursor = c;
        }
        return mCursor;
    }

    public int getLastID(){
        String q = "SELECT _id FROM patient" ;
        Cursor c  = db.rawQuery(q, new String[]{});
        c.moveToLast();
        return Integer.parseInt(c.getString(0));
    }


//    public void update(int id, int[] values){
//        db=this.getReadableDatabase();
//        ContentValues cv=new ContentValues();
//        cv.put("carbo",values[0]);
//        cv.put("fats",values[1]);
//        cv.put("prot",values[2]);
//        cv.put("vit",values[3]);
//        db.update(TABLE_NAME,cv,"_id="+id,null);
//    }

//    public int[] getArray(int id){
//        db=this.getReadableDatabase();
//        int[] arr={0,0,0,0};
//        Cursor c;
//        int i;
//        String q="SELECT * FROM patient where _id=?";
//        c = db.rawQuery(q,new String[]{id+""});
//        c.moveToFirst();
//            for (i = 5; i < c.getColumnCount(); i++) {
//                arr[i-5] = Integer.parseInt(c.getString(i));
//            }
//        return arr;
//    }

   /* public boolean checkusername(String un){
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

    public boolean validate(String u, String p, String n){
        db=this.getReadableDatabase();
        String query = "select user, pass, name from health_nav";
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        String a,b;
        while(true){
            a=cursor.getString(0);
            b=cursor.getString(1);
            if(a.equals(u) && b.equals(p)){
                n=cursor.getString(2);
                return true;
            }
            if(cursor.isLast()){
                break;
            }
            cursor.moveToNext();
        }
        db.close();

        return false;

    }*/

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS patient";
        db.execSQL(query);
        this.onCreate(db);
    }
}
