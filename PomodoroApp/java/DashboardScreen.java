package com.example.cs_300_project_vaggelis_chasiotis_20200101;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DashboardScreen extends AppCompatActivity {

    private Button btn_tasks;
    private Button btn_pomodoro;
    private Button btn_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_screen);

        btn_tasks = (Button) findViewById(R.id.btn_tasks);
        btn_pomodoro = (Button) findViewById(R.id.btn_pomodoro);
        btn_info = (Button) findViewById(R.id.info);

        btn_tasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardScreen.this, CreateTasksLayout.class);
                startActivity(intent);
            }
        });

        btn_pomodoro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardScreen.this, PomodoroLayout.class);
                startActivity(intent);
            }
        });

        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardScreen.this, PopUp.class);
                startActivity(intent);
            }
        });

    }
}