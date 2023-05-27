package com.example.cs_300_project_vaggelis_chasiotis_20200101;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBManager extends SQLiteOpenHelper {
    public DBManager (Context context) {
        super(context, "UsersDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table Users(" +
                "_id integer primary key autoincrement," +
                "username text, " +
                "password text, " +
                "description text);");

        sqLiteDatabase.execSQL("create table Tasks(" +
                "_id integer primary key autoincrement," +
                "name text);");

        sqLiteDatabase.execSQL("create table Assignment(" +
                "_id integer primary key autoincrement," +
                "taskid integer, " +
                "userid integer);");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public SQLiteDatabase openDB(){
        SQLiteDatabase sqLiteDatabase=null;
        try{
            sqLiteDatabase=getWritableDatabase();//connects to db. the first time also calls oncreate()

        }

        catch(Exception e){

        }
        return sqLiteDatabase;
    }

    public void addTask (SQLiteDatabase sqLiteDatabase, Tasks task) {
        ContentValues taskvalues = new ContentValues();
        taskvalues.put("name", task.name);
        sqLiteDatabase.insert("Tasks", null, taskvalues);
    }

    public void editTask (SQLiteDatabase sqLiteDatabase, Tasks tasks, int id) {
        ContentValues taskvalues = new ContentValues();
        taskvalues.put("name",tasks.name);
        sqLiteDatabase.update("Tasks", taskvalues,"_id",null);
    }

    public void addUser(SQLiteDatabase sqLiteDatabase, Users user) {
        ContentValues uservalues = new ContentValues();
        uservalues.put("username", user.username);
        uservalues.put("password", user.password);
        uservalues.put("description", user.description);
        sqLiteDatabase.insert("Users", null, uservalues);
    }

    public void addAssignment(SQLiteDatabase sqLiteDatabase, int taskid, int userid) {
        ContentValues assignmentvalues = new ContentValues();
        assignmentvalues.put("taskid", taskid);
        assignmentvalues.put("userid", userid);
        sqLiteDatabase.insert("Assignment", null, assignmentvalues);
    }

    public Cursor getUser (SQLiteDatabase sqLiteDatabase, String username, String password) {
        Cursor cursor = sqLiteDatabase.rawQuery("select * from Users where username ='" + username + "' and password='" + password + "'", null);
        return cursor;

    }

    @SuppressLint("Range")
    public int getLastInsertedId (SQLiteDatabase sqLiteDatabase) {
        Cursor cursor = sqLiteDatabase.rawQuery("select * from Users", null);
            cursor.moveToLast();
            return cursor.getInt(cursor.getColumnIndex("_id"));
    }

    public Cursor getMyTasks(SQLiteDatabase sqLiteDatabase) {
        Cursor cursor = sqLiteDatabase.rawQuery("select * from Tasks",null);
        cursor.moveToFirst();
        return cursor;
    }

    public void deleteTasks(SQLiteDatabase sqLiteDatabase,int id) {
        sqLiteDatabase.execSQL("delete from Tasks where _id=" +id);
    }

    public Cursor sortTasks (SQLiteDatabase sqLiteDatabase) {
        Cursor cursor = sqLiteDatabase.rawQuery("select * from Tasks order by name", null);
        return cursor;
    }
}
