package com.example.cs_300_project_vaggelis_chasiotis_20200101;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpLayout extends AppCompatActivity {

    EditText r_username;
    EditText r_password;
    EditText r_description;
    Button btn_create_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_layout);

        r_username = findViewById(R.id.r_username);
        r_password = findViewById(R.id.r_password);
        r_description = findViewById(R.id.r_description);
        btn_create_user = findViewById(R.id.btn_create_user);

        btn_create_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBManager dbManager = new DBManager(getApplicationContext());
                SQLiteDatabase sqLiteDatabase = dbManager.openDB();
                dbManager.addUser(sqLiteDatabase, new Users(r_username.getText().toString(), r_password.getText().toString(), r_description.getText().toString()));
                Intent intent = new Intent(SignUpLayout.this, LoginLayout.class);
                intent.putExtra("_id",dbManager.getLastInsertedId(sqLiteDatabase));
                Toast.makeText(SignUpLayout.this, "Sign up Successful!", Toast.LENGTH_LONG).show();
                startActivity(intent);
                }

            });
    }
}