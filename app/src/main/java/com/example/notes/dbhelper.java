package com.example.notes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class dbhelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="noteDB.db";
    private static final String TABLE_NAME="Notes";
    private static final String COL_1="NoteTitle";
    private static final String COL_2="NoteDesc";

    public dbhelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"("+COL_1+" TEXT PRIMARY KEY,"+COL_2+" TEXT)";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void add(notes note){
        String s1=note.getTitle();
        String s2=note.getDescription();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,s1);
        contentValues.put(COL_2,s2);
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
    }

    public void update(String t,String d){
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,t);
        contentValues.put(COL_2,d);
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME,contentValues,COL_1+"='"+t+"'",null);
    }


    public void delete(String title){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME,COL_1+"='"+title+"'",null);
    }

    public ArrayList<String> fetch(){
        ArrayList<String> arrayList=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT "+COL_1+" FROM "+TABLE_NAME,null);
        if(cursor.getCount()!=0){
            while (cursor.moveToNext()){
                arrayList.add(cursor.getString(0));
            }
        }
        return arrayList;
    }

    public Cursor getAll(String tablename){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + tablename, null);
        return res;
    }

//    public String get(String title){
//        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
//        String[] proj={COL_1,COL_2};
//        Cursor res=sqLiteDatabase.query(TABLE_NAME,proj,proj[0]+"='"+title+"'",null,null,null);
//        int index=res.getColumnIndex(COL_2);
//        return res.getString(1);
//    }

}
