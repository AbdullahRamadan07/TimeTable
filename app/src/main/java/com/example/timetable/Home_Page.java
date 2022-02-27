package com.example.timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Home_Page extends AppCompatActivity {

    MaterialButton save;
    EditText task;
    ListView listView;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__page);
        final MyDataBase db = new MyDataBase(this);
        final ArrayList arrayList =db.getAllData();
        task = findViewById(R.id.task);
        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter(Home_Page.this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!task.getText().toString().isEmpty()){
                    if(db.insertTask(task.getText().toString()))
                        Toast.makeText(Home_Page.this,"Added Successfully",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(Home_Page.this,"Added Failed",Toast.LENGTH_LONG).show();
                }
                else
                    task.setError("Enter Task");
            }
        });

    }
}