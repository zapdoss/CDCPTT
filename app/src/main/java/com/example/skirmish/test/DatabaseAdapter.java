package com.example.skirmish.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theakshaypant on 5/21/17.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static android.R.attr.version;

/**
 * Created by alakazam on 21/5/17.
 */

public class DatabaseAdapter extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "patients";
    private static final String TABLE_NAME = "PTABLENew";
    // private static final int DATABASE_VERSION=25;
    private static final String UID = "_id";
    private static final String NAME = "name";
    private static final String AREA = "area";
    private static final String AGE = "age";
    private static final String SEX = "sex";
    private static final String HN = "hn";
    private static final String PROTEINS = "protein";
    private static final String GRAINS = "grains";
    private static final String VEGETABLES = "vegetables";
    private static final String FATS = "fats";
    private static final String CARBOHYDRATES = "carbohydrates";

    public DatabaseAdapter(Context context) {
        super(context, DATABASE_NAME, null, 44);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        Log.d("in table" ,"table creating");
        db.execSQL(
                "create table PTABLENew" +
                        "(id integer primary key autoincrement,name,area,age,sex,hn,protein,grains,vegetables,fats,carbohydrates)");
        Log.d("create table" ,"creaed success");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }


    public boolean insertContact(String name, String area, String age, String sex,String hn, String protein, String grains, String vegetables,String fats, String carbohydrates) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Log.d("inserting " ,sex);
        contentValues.put("name", name);
        contentValues.put("area", area);
        contentValues.put("age", age);
        contentValues.put("sex", sex);
        contentValues.put("hn",hn);
        contentValues.put("protein", protein);
        contentValues.put("grains", grains);
        contentValues.put("vegetables", vegetables);
        contentValues.put("fats", fats);
        contentValues.put("carbohydrates", carbohydrates);
        Log.d("insert","done");
        db.insert("PTABLENew", null, contentValues);
        return true;
    }

    public ArrayList<String> getAllContacts(String name) {
        ArrayList<String> stats = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from PTABLENew where name='" + name+"'", null);
        res.moveToFirst();

        while(res.moveToNext())
        {
            //int index1=cursor.getColumnIndex(DbHelper.UID);

            int index1=res.getColumnIndex(PROTEINS);
            int index2=res.getColumnIndex(GRAINS);
            int index3=res.getColumnIndex(VEGETABLES);
            int index4=res.getColumnIndex(CARBOHYDRATES);
            int index5=res.getColumnIndex(FATS);

            // int cid=cursor.getInt(0);
            // String name=cursor.getString(1);
            //String area=cursor.getString(2);
            String proteins=res.getString(index1);
            String grains=res.getString(index2);
            String vegetables=res.getString(index3);
            String carbohydrates=res.getString(index4);
            String fats=res.getString(index5);
            stats.add(proteins);
            stats.add(grains);
            stats.add(vegetables);
            stats.add(carbohydrates);
            stats.add(fats);
        }
        // Log.d("fsdsdf",res.getString(0));
        Log.d("frfrfr", String.valueOf(stats.size()));
        return stats;

    }

    public boolean updateContact (String name,String protein,String grains,String vegetables,String carbohydrates,String fats) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("protein",protein);
        contentValues.put("grains",grains);
        contentValues.put("vegetables",vegetables);
        contentValues.put("carbohydrates",carbohydrates);
        contentValues.put("fats",fats);
        String [] whereArgs={name};
        db.update("PtableNew", contentValues, "name =? ", whereArgs );
        return true;
    }
}
/*
public class DatabaseAdapter{
    DbHelper helper;
    public DatabaseAdapter(Context context)
    {
        helper =new DbHelper(context);
    }

    public long insertData(String name, String area, String age, String sex,String hn,String proteins, String grains, String vegetables, String carbohydrates, String fats )
    {
        SQLiteDatabase db =helper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DbHelper.NAME,name);
        contentValues.put(DbHelper.AREA,area);
        contentValues.put(DbHelper.AGE,age);
        contentValues.put(DbHelper.SEX,sex);
        contentValues.put(DbHelper.HN,hn);
        contentValues.put(DbHelper.PROTEINS,proteins);
        contentValues.put(DbHelper.GRAINS,grains);
        contentValues.put(DbHelper.VEGETABLES,vegetables);
        contentValues.put(DbHelper.CARBOHYDRATES,carbohydrates);
        contentValues.put(DbHelper.FATS,fats);
        long id=db.insert(DbHelper.TABLE_NAME,null,contentValues);
        db.close();
        return id;
    }

    //pant's array to generate piechart
    public ArrayList<String> getAllData(String name)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        String [] columns={DbHelper.PROTEINS,DbHelper.GRAINS,DbHelper.VEGETABLES,DbHelper.CARBOHYDRATES,DbHelper.FATS};
        Cursor cursor =db.query(DbHelper.TABLE_NAME,columns,DbHelper.NAME+"='"+name+"'",null,null,null,null);
        // StringBuffer buffer =new StringBuffer();
        Log.d("hello inn connection", "Yes!");
        ArrayList<String> stats=new ArrayList<String>();
        while(cursor.moveToNext())
        {
            //int index1=cursor.getColumnIndex(DbHelper.UID);

            int index1=cursor.getColumnIndex(DbHelper.PROTEINS);
            int index2=cursor.getColumnIndex(DbHelper.GRAINS);
            int index3=cursor.getColumnIndex(DbHelper.VEGETABLES);
            int index4=cursor.getColumnIndex(DbHelper.CARBOHYDRATES);
            int index5=cursor.getColumnIndex(DbHelper.FATS);

            // int cid=cursor.getInt(0);
            // String name=cursor.getString(1);
            //String area=cursor.getString(2);
            String proteins=cursor.getString(index1);
            String grains=cursor.getString(index2);
            String vegetables=cursor.getString(index3);
            String carbohydrates=cursor.getString(index4);
            String fats=cursor.getString(index5);
            stats.add(proteins);
            stats.add(grains);
            stats.add(vegetables);
            stats.add(carbohydrates);
            stats.add(fats);
        }
        return stats;
    }

    public String getData(String name)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        String [] columns={DbHelper.NAME,DbHelper.AREA};
        Cursor cursor =db.query(DbHelper.TABLE_NAME,columns,DbHelper.NAME+"='"+name+"'",null,null,null,null);
        StringBuffer buffer =new StringBuffer();
        while(cursor.moveToNext())
        {
            int index1=cursor.getColumnIndex(DbHelper.NAME);
            int index2=cursor.getColumnIndex(DbHelper.AREA);
            //int cid=cursor.getInt(0);
            String personName=cursor.getString(index1);
            String area=cursor.getString(index2);
            buffer.append(personName+" "+area+"\n");
        }
        return buffer.toString();
    }

    //updating databse

    public int updateName(String oldName,String newName)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DbHelper.NAME,newName);
        String[] whereArgs={oldName};
        int count=db.update(DbHelper.TABLE_NAME,contentValues,DbHelper.NAME+ "?", whereArgs);
        return count;
    }

    //updating database
    public int updateDatabase(String pro, String grains, String carbs, String vegetables, String fats, String patientname)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DbHelper.PROTEINS,pro);
        contentValues.put(DbHelper.GRAINS,grains);
        contentValues.put(DbHelper.CARBOHYDRATES,carbs);
        contentValues.put(DbHelper.VEGETABLES,vegetables);
        contentValues.put(DbHelper.FATS,fats);
        String [] whereArgs={patientname};
        int count=db.update(DbHelper.TABLE_NAME,contentValues,DbHelper.NAME+ "?",whereArgs);
        return count;
    }


    static class DbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME="patients";
        private static final String TABLE_NAME="PTABLE";
        private static final int DATABASE_VERSION=30;
        private static final String UID="_id";
        private static final String NAME="name";
        private static final String AREA="area";
        private static final String AGE="age";
        private static final String SEX="sex";
        private static final String HN="hn";
        private static final String PROTEINS="protein";
        private static final String GRAINS="grains";
        private static final String VEGETABLES="vegetables";
        private static final String CARBOHYDRATES="carbohydrates";
        private static final String FATS="fats";
        private static final String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+ "("+UID+"INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" VARCHAR(255),"+AREA+" VARCHAR(255),"+AGE+"  VARCHAR(255),"+SEX+" VARCHAR(255),"+HN+" VARCHAR(255), "+PROTEINS+"  VARCHAR(255), "+GRAINS+"  VARCHAR(255),"+VEGETABLES+"  VARCHAR(255),"+CARBOHYDRATES+"  VARCHAR(255),"+FATS+"  VARCHAR(255));";
        private static final String DROP_TABLE="DROP TABLE IF EXISTS "+TABLE_NAME+";";
        private Context context;
        public DbHelper(Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
            this.context=context;
            //Message.message(context,"constructor called");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            try {
                Message.message(context, "onCreate called ");
                db.execSQL(CREATE_TABLE);
            }
            catch(SQLException e){
                Message.message(context, ""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL(DROP_TABLE);
                onCreate(db);
        }
    }
}
*/
