package com.example.cs_300_project_vaggelis_chasiotis_20200101;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

public class LoginLayout extends AppCompatActivity {

    TextView l_username;
    TextView l_password;
    TextView l_forgot;
    Button btn_login;
    Button btn_signup;
    Button btn_edit;
    DBManager dbManager;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);

        TextView l_username =  findViewById(R.id.l_username);
        TextView l_password = findViewById(R.id.l_password);
        Button btn_login = findViewById(R.id.btn_login);
        Button btn_signup = findViewById(R.id.btn_signup);
        dbManager = new DBManager(getApplicationContext());
        sqLiteDatabase = dbManager.openDB();

        dbManager.addUser(sqLiteDatabase, new Users("vaggelis", "321", "Hi people"));
        dbManager.addUser(sqLiteDatabase, new Users("nikola", "123", "hey yo"));
        dbManager.addTask(sqLiteDatabase, new Tasks("Study for CS300"));
        dbManager.addTask(sqLiteDatabase, new Tasks("Study 20 pages of my book"));
        dbManager.addAssignment(sqLiteDatabase, 1,1);
        dbManager.addAssignment(sqLiteDatabase, 1,2);
        dbManager.addAssignment(sqLiteDatabase, 2,1);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginLayout.this, SignUpLayout.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View v) {
                Cursor cursor = dbManager.getUser(sqLiteDatabase, l_username.getText().toString(), l_password.getText().toString());


                if (cursor.moveToFirst()) {
                    Toast.makeText(LoginLayout.this, "Login Successful!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginLayout.this, DashboardScreen.class);
                    intent.putExtra("_id", cursor.getInt(cursor.getColumnIndex("_id")));
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginLayout.this, "Login Failed. Try Again!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}