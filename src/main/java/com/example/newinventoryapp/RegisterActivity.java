package com.example.newinventoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText userName, userPassword;
    private Button rButton;
    private TextView userLogin;

    DBActivity DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        DB = new DBActivity(this);

        userName = (EditText)findViewById(R.id.editTextNewUserName);
        userPassword = (EditText)findViewById(R.id.editTextNewPassword);

        rButton = (Button)findViewById(R.id.buttonRegister);

        rButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){


                String user = userName.getText().toString();
                String pwd = userPassword.getText().toString();


                if(user.equals("") || pwd.equals("")){

                    Toast.makeText(RegisterActivity.this, "Fill out blank fields", Toast.LENGTH_SHORT).show();

                }

                else{

                    Boolean checkUser = DB.checkusername(user);

                    if(checkUser == false){

                        Boolean insert = DB.insertData(user, pwd);

                        if(insert == true){

                            Toast.makeText(RegisterActivity.this, "Registered", Toast.LENGTH_SHORT).show();

                        }

                        else{

                            Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();

                        }

                    }

                    else{

                        Toast.makeText(RegisterActivity.this, "User exists", Toast.LENGTH_SHORT).show();

                    }

                }

            }

        });

    }

}