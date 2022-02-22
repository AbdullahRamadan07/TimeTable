package com.example.timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class Register_page extends AppCompatActivity {

    EditText username, password, repeatpassword;
    MaterialButton signup;
    MyDataBase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repeatpassword = (EditText) findViewById(R.id.repeatpassword);
        signup = (MaterialButton) findViewById(R.id.regiser);
        db = new MyDataBase(this);
        signup.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String name = username.getText().toString();
                String pass = password.getText().toString();
                String rePass  =repeatpassword.getText().toString();

                if(name.equals("") || pass.equals("") || rePass.equals("")){
                    Toast.makeText(Register_page.this,"Fill all the fields",Toast.LENGTH_LONG).show();
                }
                else{
                    if(pass.equals(rePass)){
                        Boolean check = db.checkUserName(name);
                        if(check == false){
                            Boolean insert = db.EditData(name,pass);
                            if(insert == true){
                                Toast.makeText(Register_page.this,"Registered Successfully",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(),Home_Page.class);
                                startActivity(intent);
                            }
                            else
                                Toast.makeText(Register_page.this,"Registered Failed",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(Register_page.this, "The account is exixt", Toast.LENGTH_LONG).show();
                            Toast.makeText(Register_page.this, "Please, Sign in", Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                        Toast.makeText(Register_page.this,"The password is not matching",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}