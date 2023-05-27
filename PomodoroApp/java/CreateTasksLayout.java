package com.example.cs_300_project_vaggelis_chasiotis_20200101;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class CreateTasksLayout extends AppCompatActivity {

    ListView tasks_lv;
    Button btnAdd;
    Button btnSort;
    EditText tasktxt;
    Tasks task;
    SimpleCursorAdapter simpleCursorAdapter;
    Cursor cursor;
    DBManager dbManager;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tasks_layout);

        tasks_lv = findViewById(R.id.listview);
        btnAdd = findViewById(R.id.buttonAdd);
        btnSort = findViewById(R.id.buttonSort);
        tasktxt = findViewById(R.id.taskstxt);
        dbManager = new DBManager(getApplicationContext());
        sqLiteDatabase = dbManager.openDB();
        cursor = dbManager.getMyTasks(sqLiteDatabase);
        simpleCursorAdapter=new SimpleCursorAdapter(getApplicationContext(),R.layout.adapterlayout,cursor,new String[]{"name"},new int[]{R.id.taskname});
        tasks_lv.setAdapter(simpleCursorAdapter);

        tasks_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Cursor cursor = (Cursor)tasks_lv.getItemAtPosition(position);
                int _id = cursor.getInt(cursor.getColumnIndex("_id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                Intent intent = new Intent(CreateTasksLayout.this, DisplayTasks.class);
                intent.putExtra("_id",_id);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });

        //Add Tasks
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = tasktxt.getText().toString();
                Tasks task = new Tasks(name);
                dbManager.addTask(sqLiteDatabase,task);
            }
        });

        //Sort Tasks
        btnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cursor = dbManager.sortTasks(sqLiteDatabase);
                simpleCursorAdapter.changeCursor(cursor);
                simpleCursorAdapter.notifyDataSetChanged();
            }
        });

       }
}