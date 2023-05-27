package com.example.cs_300_project_vaggelis_chasiotis_20200101;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayTasks extends AppCompatActivity {

    TextView taskname;
    String name;
    int id;
    Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_tasks);

        taskname = findViewById(R.id.taskname);
        btnEdit = findViewById(R.id.btnEdit);
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("name");
        id = bundle.getInt("_id");
        taskname.setText(name);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayTasks.this, DisplayTasks.class);
                intent.putExtra("_id", id);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });

    }
}