package com.example.timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    MaterialButton login,sign_up;
    MyDataBase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (MaterialButton) findViewById(R.id.login);
        sign_up = (MaterialButton) findViewById(R.id.regiser);
        db = new MyDataBase(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                String pass = password.getText().toString();

                if(name.equals("") || pass.equals(""))
                    Toast.makeText(MainActivity.this,"Fill your data",Toast.LENGTH_LONG).show();
                else{
                    Boolean check = db.checkAllData(name,pass);
                    if(check == true){
                        Toast.makeText(MainActivity.this,"Login Successfully",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),Home_Page.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this,"Invalid name or password",Toast.LENGTH_LONG).show();
                    }

                }

            }
        });/*{
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("abdullah")&& password.getText().toString().equals("fuckOff")){
                    Toast.makeText(MainActivity.this, "Successful",Toast.LENGTH_LONG);
                }
                else
                    Toast.makeText(MainActivity.this, "Failed",Toast.LENGTH_LONG);
            }
        });*/

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Register_page.class);
                startActivity(intent);
            }
        });

    }
}