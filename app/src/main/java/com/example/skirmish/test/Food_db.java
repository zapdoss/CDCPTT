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

public class Food_db extends SQLiteOpenHelper {


    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "food.db";
    private static final String TABLE_NAME1 = "fats";
    private static final String TABLE_NAME2 = "carbo";
    private static final String TABLE_NAME3 = "prot";
    private static final String TABLE_NAME4 = "vit";
    private static final String C_ID = "_id";
    private static final String C_F1 = "f1";
    private static final String C_F2 = "f2";
    //private static final String C_HN = "hn_id";
    //private static final String C_PIC = "pic";
    SQLiteDatabase db;
    private static final String TABLE1_CREATE = "create table fats (_id int primary key, f1 int not null , f2 int not null)";
    private static final String TABLE2_CREATE = "create table carbo (_id int primary key, f1 int not null , f2 int not null)";
    private static final String TABLE3_CREATE = "create table prot (_id int primary key, f1 int not null , f2 int not null)";
    private static final String TABLE4_CREATE = "create table vit (_id int primary key, f1 int not null , f2 int not null)";

    public Food_db(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE1_CREATE);
        db.execSQL(TABLE2_CREATE);
        db.execSQL(TABLE3_CREATE);
        db.execSQL(TABLE4_CREATE);
        this.db=db;
        ContentValues v = new ContentValues();
        v.put(C_ID,0);
        v.put(C_F1,0);
        v.put(C_F2,0);
        db.insert(TABLE_NAME1,null,v);
        db.insert(TABLE_NAME2,null,v);
        db.insert(TABLE_NAME3,null,v);
        db.insert(TABLE_NAME4,null,v);
        // insertx();
    }

    public void insertx(int id,int f1, int f2, int tab){
        db=this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(C_ID,id);
        v.put(C_F1,f1);
        v.put(C_F2,f2);
        if(tab==0) db.insert(TABLE_NAME2,null,v);
        else if(tab==1) db.insert(TABLE_NAME1,null,v);
        else if(tab==2) db.insert(TABLE_NAME3,null,v);
        else if(tab==3) db.insert(TABLE_NAME4,null,v);
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

    public Cursor getData(int id, int tab){
        db=this.getReadableDatabase();
        //String q = "select * from patient where hn_id like ? ";
        // Cursor c = db.rawQuery(q,new String[]{usr});
        String q="";
        if(tab==0) q = "SELECT * FROM carbo WHERE _id =? ";
        else if(tab==1) q = "SELECT * FROM fats WHERE _id =? ";
        else if(tab==2) q = "SELECT * FROM prot WHERE _id =? ";
        else if(tab==3) q = "SELECT * FROM vit WHERE _id =? ";
        Cursor c = db.rawQuery(q,new String[]{Integer.toString(id)});
        c.moveToFirst();
        return c;
    }

    public void update(int id, int tab, List<Integer> values){
        db=this.getReadableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(C_F1,values.get(0));
        cv.put(C_F2,values.get(1));
        if(tab==0) db.update(TABLE_NAME2,cv,"_id="+id,null);
        else if(tab==1) db.update(TABLE_NAME1,cv,"_id="+id,null);
        else if(tab==2) db.update(TABLE_NAME3,cv,"_id="+id,null);
        else if(tab==3) db.update(TABLE_NAME4,cv,"_id="+id,null);
    }

    public int[] getArray(int id){
        int[] arr={0,0,0,0};
        Cursor c;
        int i,j;
        for(j=0;j<4;j++) {
            c = getData(id, j);
            for (i = 1; i < c.getColumnCount(); i++) {
                arr[j] = arr[j] + Integer.parseInt(c.getString(i));
            }
        }
        return arr;
    }

    /*public Cursor getCursor(String str, String usr) {
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
        String query = "DROP TABLE IF EXISTS "+TABLE_NAME1;
        db.execSQL(query);

        query = "DROP TABLE IF EXISTS "+TABLE_NAME2;
        db.execSQL(query);

        query = "DROP TABLE IF EXISTS "+TABLE_NAME3;
        db.execSQL(query);

        query = "DROP TABLE IF EXISTS "+TABLE_NAME4;
        db.execSQL(query);

        this.onCreate(db);
    }
}