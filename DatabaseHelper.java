package com.example.mahe.lab6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mahe on 3/7/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public  static final String DATABASE="climgmApp.db";
    public static final  String TABLE1="doctors";
    public static final  String COL1="ID";
    public static final  String COL2="NAME";
    public static final  String COL3="SPEC";
    public static final  String TABLE2="users";
    public static final  String COL4="USERNAME";
    public static final  String COL5="PASSWORD";
    public static final  String TABLE3="appoints";
    public static final  String COL6="ID";
    public static final  String COL7="USERNAME";
    public static final  String COL8="DOCTOR";
    public static final  String COL9="DATEAPP";
    public static final  String COL10="TIMEAPP";
    public static final  String TABLE4="timeslot";
    public static final String COL11="TIMEAPP";

    public DatabaseHelper(Context context) {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table doctors(ID integer primary key autoincrement,NAME text,SPEC text);");
        db.execSQL("create table users(USERNAME text,PASSWORD text primary key);");
        db.execSQL("create table appoints(ID integer primary key autoincrement,USERNAME text,DOCTOR text,DATEAPP text,TIMEAPP text);");
        db.execSQL("create table timeslot(TIMEAPP text primary key);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        onCreate(db);
    }

     public boolean inserusers(String uname,String pwd)
     {
         SQLiteDatabase db=this.getWritableDatabase();
         ContentValues values=new ContentValues();
         values.put(COL4,uname);
         values.put(COL5,pwd);
         long x=db.insert(TABLE2,null,values);
         if(x==-1)
             return false;
         else
             return true;
     }

    public  boolean insertDoctor(String name,String spec)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COL2,name);
        values.put(COL3,spec);
        long x=db.insert(TABLE1,null,values);
        if(x==-1)
            return false;
        else
            return true;
    }

    public  boolean insertTime(String timestr)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COL11,timestr);
        long x=db.insert(TABLE4,null,values);
        if(x==-1)
            return false;
        else
            return true;
    }
    public  Cursor verUser(String pwd)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cur=db.rawQuery("select * from "+ TABLE2 +" where PASSWORD=?",new String[]{pwd});
        return cur;
    }

    public  boolean inserApp(String uname, String Dname,String Adate, String Atime)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COL7,uname);
        values.put(COL8,Dname);
        values.put(COL9,Adate);
        values.put(COL10,Atime);
        long res=db.insert(TABLE3,null,values);
        if(res==-1)
            return false;
        else
            return true;
    }

    public List<String> getAllSpec()
    {
        List<String> specn=new ArrayList<String>();
        SQLiteDatabase db=this.getReadableDatabase();
       // Cursor cur=db.query(true,TABLE1,new String[]{COL3},null,null,null,null,null,null);
        Cursor cur=db.rawQuery("select distinct SPEC from "+TABLE1,null);
        if(cur.moveToFirst())
        {
            do
            {
                specn.add(cur.getString(0));
            }while(cur.moveToNext());
        }
        return specn;
    }

    public List<String> getAllSpecDoc(String Spec)
    {
        List<String> specn=new ArrayList<String>();
        SQLiteDatabase db=this.getReadableDatabase();
        //Cursor cur=db.query(true,TABLE1,new String[]{COL3},null,null,null,null,null,null);
        Cursor cur=db.rawQuery("select NAME from "+TABLE1+" where SPEC=?",new String[]{Spec});
        if(cur.moveToFirst())
        {
            do
            {
                specn.add(cur.getString(0));
            }while(cur.moveToNext());
        }
        return specn;
    }

    public List<String> getAllTime(String adate,String dname)
    {
        List<String> specn=new ArrayList<String>();
        SQLiteDatabase db=this.getReadableDatabase();
         Cursor cur=db.rawQuery("select * from "+TABLE4+" where TIMEAPP not in(select TIMEAPP from "+TABLE3+" where DATEAPP=? and DOCTOR=?)",new String[]{adate,dname});
        if(cur.moveToFirst())
        {
            do
            {
                specn.add(cur.getString(0));
            }while(cur.moveToNext());
        }
        return specn;
    }
}
