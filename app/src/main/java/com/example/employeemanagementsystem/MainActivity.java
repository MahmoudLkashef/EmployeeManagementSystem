package com.example.employeemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.employeemanagementsystem.Database.Database;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    Button login,signup;
    TextInputEditText email,pass;
    Database mydb=new Database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=findViewById(R.id.login_btn);
        signup=findViewById(R.id.signUp_btn);
        email=findViewById(R.id.email_ET);
        pass=findViewById(R.id.pass_ET);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em=email.getText().toString().trim();
                String pa=pass.getText().toString().trim();
                if(mydb.isAdminExist(em,pa)) {
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
                else
                {
                    email.setError("Invalid email or pass");
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}