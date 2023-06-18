package com.example.newinventoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int TEXT_PERMISSION_REQUEST = 0;
    private EditText userName;
    private EditText Password;
    private TextView userRegister;
    private Button Login;
    private TextView userPermission;
    DBActivity DB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText)findViewById(R.id.userLoginName);
        Password = (EditText)findViewById(R.id.userPasswordField);
        Login = (Button)findViewById(R.id.loginButton);
        userRegister = (TextView)findViewById(R.id.textViewRegister);
        userPermission = (TextView)findViewById(R.id.textViewPermission);
        DB = new DBActivity(this);

        Login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {


                String user = userName.getText().toString();
                String pwd = Password.getText().toString();


                if (userName.equals("") || Password.equals("")) {
                    Toast.makeText(MainActivity.this, "Fill out blank fields", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkUserPwd = DB.checkpwd(user, pwd);
                    if(checkUserPwd == true) {
                        //   Toast.makeText(MainActivity.this, "Signed in", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainScreen.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        userRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
    }
}