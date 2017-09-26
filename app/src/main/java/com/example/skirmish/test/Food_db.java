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


    private static final int DB_VERSION = 10;
    private static final String DB_NAME = "food.db";
    private static final String TABLE_NAME1Q = "Cat1_q";
    private static final String TABLE_NAME1F = "Cat1_f";
    private static final String TABLE_NAME2Q = "Cat2_q";
    private static final String TABLE_NAME2F = "Cat2_f";
    private static final String TABLE_NAME3Q = "Cat3_q";
    private static final String TABLE_NAME3F = "Cat3_f";
    private static final String TABLE_NAME4Q = "Cat4_q";
    private static final String TABLE_NAME4F = "Cat4_f";
    private static final String TABLE_NAME5Q = "Cat5_q";
    private static final String TABLE_NAME5F = "Cat5_f";
    private static final String C_ID = "_id";
    private static final String C_F1 = "f1";
    private static final String C_F2 = "f2";
    private static final String C_F3 = "f3";
    private static final String C_F4 = "f4";
    private static final String C_F5 = "f5";
    private static final String C_F6 = "f6";
    private static final String C_F7 = "f7";
    private static final String C_F8 = "f8";
    private static final String C_F9 = "f9";
    private static final String C_F10 = "f10";
    private static final String C_F11 = "f11";
    //private static final String C_HN = "hn_id";
    //private static final String C_PIC = "pic";
    SQLiteDatabase db;
    private static final String TABLE1Q_CREATE = "create table "+TABLE_NAME1Q+ " ("+C_ID+" int primary key, "+C_F1+" int not null , "+C_F2+" int not null, "+C_F3+" int not null)";
    private static final String TABLE1F_CREATE = "create table "+TABLE_NAME1F+ " ("+C_ID+" int primary key, "+C_F1+" int not null , "+C_F2+" int not null, "+C_F3+" int not null)";
    private static final String TABLE2Q_CREATE = "create table "+TABLE_NAME2Q+ " ("+C_ID+" int primary key, "+C_F1+" int not null , "+C_F2+" int not null, "+C_F3+" int not null, "+C_F4+" int not null, "+C_F5+" int not null, "+C_F6+" int not null, "+C_F7+" int not null, "+C_F8+" int not null, "+C_F9+" int not null, "+C_F10+" int not null, "+C_F11+" int not null)";
    private static final String TABLE2F_CREATE = "create table "+TABLE_NAME2F+ " ("+C_ID+" int primary key, "+C_F1+" int not null , "+C_F2+" int not null, "+C_F3+" int not null, "+C_F4+" int not null, "+C_F5+" int not null, "+C_F6+" int not null, "+C_F7+" int not null, "+C_F8+" int not null, "+C_F9+" int not null, "+C_F10+" int not null, "+C_F11+" int not null)";
    private static final String TABLE3Q_CREATE = "create table "+TABLE_NAME3Q+ " ("+C_ID+" int primary key, "+C_F1+" int not null , "+C_F2+" int not null, "+C_F3+" int not null)";
    private static final String TABLE3F_CREATE = "create table "+TABLE_NAME3F+ " ("+C_ID+" int primary key, "+C_F1+" int not null , "+C_F2+" int not null, "+C_F3+" int not null)";
    private static final String TABLE4Q_CREATE = "create table "+TABLE_NAME4Q+ " ("+C_ID+" int primary key, "+C_F1+" int not null , "+C_F2+" int not null, "+C_F3+" int not null, "+C_F4+" int not null, "+C_F5+" int not null, "+C_F6+" int not null)";
    private static final String TABLE4F_CREATE = "create table "+TABLE_NAME4F+ " ("+C_ID+" int primary key, "+C_F1+" int not null , "+C_F2+" int not null, "+C_F3+" int not null, "+C_F4+" int not null, "+C_F5+" int not null, "+C_F6+" int not null)";
    private static final String TABLE5Q_CREATE = "create table "+TABLE_NAME5Q+ " ("+C_ID+" int primary key, "+C_F1+" int not null , "+C_F2+" int not null, "+C_F3+" int not null, "+C_F4+" int not null, "+C_F5+" int not null, "+C_F6+" int not null, "+C_F7+" int not null)";
    private static final String TABLE5F_CREATE = "create table "+TABLE_NAME5F+ " ("+C_ID+" int primary key, "+C_F1+" int not null , "+C_F2+" int not null, "+C_F3+" int not null, "+C_F4+" int not null, "+C_F5+" int not null, "+C_F6+" int not null, "+C_F7+" int not null)";

    public Food_db(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE1Q_CREATE);
        db.execSQL(TABLE1F_CREATE);
        db.execSQL(TABLE2Q_CREATE);
        db.execSQL(TABLE2F_CREATE);
        db.execSQL(TABLE3Q_CREATE);
        db.execSQL(TABLE3F_CREATE);
        db.execSQL(TABLE4Q_CREATE);
        db.execSQL(TABLE4F_CREATE);
        db.execSQL(TABLE5Q_CREATE);
        db.execSQL(TABLE5F_CREATE);
        this.db=db;
        ContentValues v = new ContentValues();
        v.put(C_ID,0);
        v.put(C_F1,0);
        v.put(C_F2,0);
        v.put(C_F3,0);
        db.insert(TABLE_NAME1Q,null,v);
        db.insert(TABLE_NAME1F,null,v);
        db.insert(TABLE_NAME3Q,null,v);
        db.insert(TABLE_NAME3F,null,v);
        v.put(C_F4,0);
        v.put(C_F5,0);
        v.put(C_F6,0);
        db.insert(TABLE_NAME4Q,null,v);
        db.insert(TABLE_NAME4F,null,v);
        v.put(C_F7,0);
        db.insert(TABLE_NAME5Q,null,v);
        db.insert(TABLE_NAME5F,null,v);
        v.put(C_F8,0);
        v.put(C_F9,0);
        v.put(C_F10,0);
        v.put(C_F11,0);
        db.insert(TABLE_NAME2F,null,v);
        db.insert(TABLE_NAME2Q,null,v);
        // insertx();
    }

//    public void insertx(int id,int f1, int f2, int tab){
//        db=this.getWritableDatabase();
//        ContentValues v = new ContentValues();
//        v.put(C_ID,id);
//        v.put(C_F1,f1);
//        v.put(C_F2,f2);
//        if(tab==0) db.insert(TABLE_NAME2,null,v);
//        else if(tab==1) db.insert(TABLE_NAME1,null,v);
//        else if(tab==2) db.insert(TABLE_NAME3,null,v);
//        else if(tab==3) db.insert(TABLE_NAME4,null,v);
//        // db.close();
//    }

    public void insertCat1(int id,int q1, int f1, int q2, int f2, int q3, int f3){
        db=this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(C_ID,id);
        v.put(C_F1,f1);
        v.put(C_F2,f2);
        v.put(C_F3,f3);
        db.insert(TABLE_NAME1F,null,v);
        ContentValues w = new ContentValues();
        w.put(C_ID,id);
        w.put(C_F1,q1);
        w.put(C_F2,q2);
        w.put(C_F3,q3);
        db.insert(TABLE_NAME1Q,null,w);
        // db.close();
    }

    public void insertCat2(int id,int q1, int f1, int q2, int f2, int q3, int f3, int q4, int f4, int q5, int f5, int q6, int f6, int q7, int f7, int q8, int f8, int q9, int f9, int q10, int f10, int q11, int f11){
        db=this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(C_ID,id);
        v.put(C_F1,f1);
        v.put(C_F2,f2);
        v.put(C_F3,f3);
        v.put(C_F4,f4);
        v.put(C_F5,f5);
        v.put(C_F6,f6);
        v.put(C_F7,f7);
        v.put(C_F8,f8);
        v.put(C_F9,f9);
        v.put(C_F10,f10);
        v.put(C_F11,f11);
        db.insert(TABLE_NAME2F,null,v);
        ContentValues w = new ContentValues();
        w.put(C_ID,id);
        w.put(C_F1,q1);
        w.put(C_F2,q2);
        w.put(C_F3,q3);
        w.put(C_F4,q4);
        w.put(C_F5,q5);
        w.put(C_F6,q6);
        w.put(C_F7,q7);
        w.put(C_F8,q8);
        w.put(C_F9,q9);
        w.put(C_F10,q10);
        w.put(C_F11,q11);
        db.insert(TABLE_NAME2Q,null,w);
        // db.close();
    }

    public void insertCat3(int id,int q1, int f1, int q2, int f2, int q3, int f3){
        db=this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(C_ID,id);
        v.put(C_F1,f1);
        v.put(C_F2,f2);
        v.put(C_F3,f3);
        db.insert(TABLE_NAME3F,null,v);
        ContentValues w = new ContentValues();
        w.put(C_ID,id);
        w.put(C_F1,q1);
        w.put(C_F2,q2);
        w.put(C_F3,q3);
        db.insert(TABLE_NAME3Q,null,w);
        // db.close();
    }

    public void insertCat4(int id,int q1, int f1, int q2, int f2, int q3, int f3, int q4, int f4, int q5, int f5, int q6, int f6){
        db=this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(C_ID,id);
        v.put(C_F1,f1);
        v.put(C_F2,f2);
        v.put(C_F3,f3);
        v.put(C_F4,f4);
        v.put(C_F5,f5);
        v.put(C_F6,f6);
        db.insert(TABLE_NAME4F,null,v);
        ContentValues w = new ContentValues();
        w.put(C_ID,id);
        w.put(C_F1,q1);
        w.put(C_F2,q2);
        w.put(C_F3,q3);
        w.put(C_F4,q4);
        w.put(C_F5,q5);
        w.put(C_F6,q6);
        db.insert(TABLE_NAME4Q,null,w);
        // db.close();
    }

    public void insertCat5(int id,int q1, int f1, int q2, int f2, int q3, int f3, int q4, int f4, int q5, int f5, int q6, int f6, int q7, int f7){
        db=this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(C_ID,id);
        v.put(C_F1,f1);
        v.put(C_F2,f2);
        v.put(C_F3,f3);
        v.put(C_F4,f4);
        v.put(C_F5,f5);
        v.put(C_F6,f6);
        v.put(C_F7,f7);
        db.insert(TABLE_NAME5F,null,v);
        ContentValues w = new ContentValues();
        w.put(C_ID,id);
        w.put(C_F1,q1);
        w.put(C_F2,q2);
        w.put(C_F3,q3);
        w.put(C_F4,q4);
        w.put(C_F5,q5);
        w.put(C_F6,q6);
        w.put(C_F7,q7);
        db.insert(TABLE_NAME5Q,null,w);
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

    public int[] getData(int id, String tab){
        db=this.getReadableDatabase();
        //String q = "select * from patient where hn_id like ? ";
        // Cursor c = db.rawQuery(q,new String[]{usr});
        String q="";
        if(tab=="1q") q = "SELECT * FROM "+TABLE_NAME1Q+" WHERE _id =? ";
        else if(tab=="1f") q = "SELECT * FROM "+TABLE_NAME1F+" WHERE _id =? ";
        else if(tab=="2q") q = "SELECT * FROM "+TABLE_NAME2Q+" WHERE _id =? ";
        else if(tab=="2f") q = "SELECT * FROM "+TABLE_NAME2F+" WHERE _id =? ";
        else if(tab=="3q") q = "SELECT * FROM "+TABLE_NAME3Q+" WHERE _id =? ";
        else if(tab=="3f") q = "SELECT * FROM "+TABLE_NAME3F+" WHERE _id =? ";
        else if(tab=="4q") q = "SELECT * FROM "+TABLE_NAME4Q+" WHERE _id =? ";
        else if(tab=="4f") q = "SELECT * FROM "+TABLE_NAME4F+" WHERE _id =? ";
        else if(tab=="5q") q = "SELECT * FROM "+TABLE_NAME5Q+" WHERE _id =? ";
        else if(tab=="5f") q = "SELECT * FROM "+TABLE_NAME5F+" WHERE _id =? ";

        Cursor c = db.rawQuery(q,new String[]{Integer.toString(id)});
        c.moveToFirst();
        int i,j;
        int[] arr = new int[c.getColumnCount()-1];
        for(i=0;i<c.getColumnCount()-1;i++){
            arr[i]=Integer.parseInt(c.getString(i+1));
        }
        return arr;
    }

    public void update(int id, int tab, int[] q, int[] f){
        db=this.getReadableDatabase();
        ContentValues cv=new ContentValues();
        ContentValues cf=new ContentValues();

        if(tab==0) {
            cv.put(C_F1, q[0]);
            cv.put(C_F2, q[1]);
            cv.put(C_F3, q[2]);
            db.update(TABLE_NAME1Q, cv, "_id=" + id, null);
            cf.put(C_F1, f[0]);
            cf.put(C_F2, f[1]);
            cf.put(C_F3, f[2]);
            db.update(TABLE_NAME1F, cf, "_id=" + id, null);
        }
        else if(tab==1) {
            cv.put(C_F1, q[0]);
            cv.put(C_F2, q[1]);
            cv.put(C_F3, q[2]);
            cv.put(C_F4, q[3]);
            cv.put(C_F5, q[4]);
            cv.put(C_F6, q[5]);
            cv.put(C_F7, q[6]);
            cv.put(C_F8, q[7]);
            cv.put(C_F9, q[8]);
            cv.put(C_F10, q[9]);
            cv.put(C_F11, q[10]);
            db.update(TABLE_NAME2Q, cv, "_id=" + id, null);
            cf.put(C_F1, f[0]);
            cf.put(C_F2, f[1]);
            cf.put(C_F3, f[2]);
            cf.put(C_F4, f[3]);
            cf.put(C_F5, f[4]);
            cf.put(C_F6, f[5]);
            cf.put(C_F7, f[6]);
            cf.put(C_F8, f[7]);
            cf.put(C_F9, f[8]);
            cf.put(C_F10, f[9]);
            cf.put(C_F11, f[10]);
            db.update(TABLE_NAME2F, cf, "_id=" + id, null);
        }
        else if(tab==2) {
            cv.put(C_F1, q[0]);
            cv.put(C_F2, q[1]);
            cv.put(C_F3, q[2]);
            db.update(TABLE_NAME3Q, cv, "_id=" + id, null);
            cf.put(C_F1, f[0]);
            cf.put(C_F2, f[1]);
            cf.put(C_F3, f[2]);
            db.update(TABLE_NAME3F, cf, "_id=" + id, null);
        }
        else if(tab==3) {
            cv.put(C_F1, q[0]);
            cv.put(C_F2, q[1]);
            cv.put(C_F3, q[2]);
            cv.put(C_F4, q[3]);
            cv.put(C_F5, q[4]);
            cv.put(C_F6, q[5]);
            db.update(TABLE_NAME4Q, cv, "_id=" + id, null);
            cf.put(C_F1, f[0]);
            cf.put(C_F2, f[1]);
            cf.put(C_F3, f[2]);
            cf.put(C_F4, f[3]);
            cf.put(C_F5, f[4]);
            cf.put(C_F6, f[5]);
            db.update(TABLE_NAME4F, cf, "_id=" + id, null);
        }

        else if(tab==4) {
            cv.put(C_F1, q[0]);
            cv.put(C_F2, q[1]);
            cv.put(C_F3, q[2]);
            cv.put(C_F4, q[3]);
            cv.put(C_F5, q[4]);
            cv.put(C_F6, q[5]);
            cv.put(C_F7, q[6]);
            db.update(TABLE_NAME5Q, cv, "_id=" + id, null);
            cf.put(C_F1, f[0]);
            cf.put(C_F2, f[1]);
            cf.put(C_F3, f[2]);
            cf.put(C_F4, f[3]);
            cf.put(C_F5, f[4]);
            cf.put(C_F6, f[5]);
            cf.put(C_F7, f[6]);
            db.update(TABLE_NAME5F, cf, "_id=" + id, null);
        }
    }

    public int[] getSumArrayQ(int id){
        int[] arr={0,0,0,0,0};
        int i,j;
        int[] a=getData(id,"1q");
        for(i=0;i<a.length;i++){
            arr[0]=arr[0]+a[i];
        }
        a=getData(id,"2q");
        for(i=0;i<a.length;i++){
            arr[1]=arr[1]+a[i];
        }
        a=getData(id,"3q");
        for(i=0;i<a.length;i++){
            arr[2]=arr[2]+a[i];
        }
        a=getData(id,"4q");
        for(i=0;i<a.length;i++){
            arr[3]=arr[3]+a[i];
        }
        a=getData(id,"5q");
        for(i=0;i<a.length;i++){
            arr[4]=arr[4]+a[i];
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
        String query = "DROP TABLE IF EXISTS "+TABLE_NAME1Q;
        db.execSQL(query);

        query = "DROP TABLE IF EXISTS "+TABLE_NAME1F;
        db.execSQL(query);

        query = "DROP TABLE IF EXISTS "+TABLE_NAME2Q;
        db.execSQL(query);

        query = "DROP TABLE IF EXISTS "+TABLE_NAME2F;
        db.execSQL(query);

        query = "DROP TABLE IF EXISTS "+TABLE_NAME3Q;
        db.execSQL(query);

        query = "DROP TABLE IF EXISTS "+TABLE_NAME3F;
        db.execSQL(query);

        query = "DROP TABLE IF EXISTS "+TABLE_NAME4Q;
        db.execSQL(query);

        query = "DROP TABLE IF EXISTS "+TABLE_NAME4F;
        db.execSQL(query);

        query = "DROP TABLE IF EXISTS "+TABLE_NAME5Q;
        db.execSQL(query);

        query = "DROP TABLE IF EXISTS "+TABLE_NAME5F;
        db.execSQL(query);


        this.onCreate(db);
    }
}