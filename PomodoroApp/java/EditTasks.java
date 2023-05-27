package com.example.cs_300_project_vaggelis_chasiotis_20200101;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditTasks extends AppCompatActivity {

    EditText edittext;
    Button btnEdTask;
    Button btnEdDel;
    int id;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tasks);

        edittext = findViewById(R.id.edittxt);
        btnEdTask = findViewById(R.id.btnEdTask);
        btnEdDel = findViewById(R.id.btnEdDel);
        Bundle bundle=getIntent().getExtras();
        name = bundle.getString("name");
        id = bundle.getInt("id");
        edittext.setText(name);

        btnEdTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fruit = edittext.getText().toString();
                Tasks tasks = new Tasks(name);
                DBManager dbManager = new DBManager(getApplicationContext());
                SQLiteDatabase sqLiteDatabase = dbManager.openDB();
                dbManager.editTask(sqLiteDatabase,tasks, id);
                Intent intent = new Intent(EditTasks.this, CreateTasksLayout.class);
                startActivity(intent);
            }
        });

        btnEdDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBManager dbManager = new DBManager(getApplicationContext());
                SQLiteDatabase sqLiteDatabase = dbManager.openDB();
                dbManager.deleteTasks(sqLiteDatabase,id);
                Intent intent = new Intent(EditTasks.this, CreateTasksLayout.class);
                startActivity(intent);
            }
        });
    }
}