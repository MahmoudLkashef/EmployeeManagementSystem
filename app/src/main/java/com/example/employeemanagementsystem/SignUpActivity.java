package com.example.employeemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.employeemanagementsystem.Database.Database;

import java.util.Locale;

public class SignUpActivity extends AppCompatActivity {
EditText email,pass,confirmPass;
Database mydb=new Database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        email=findViewById(R.id.email_ET_signUp);
        pass=findViewById(R.id.pass_ET_signUp);
        confirmPass=findViewById(R.id.confirm_pass_ET);
        Button signup=findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckEditTextInput check=new CheckEditTextInput(SignUpActivity.this);
                if(check.validInput(email)&&check.validInput(pass)&&check.validInput(confirmPass))
                {
                    if(!pass.getText().toString().equals(confirmPass.getText().toString()))
                    {
                        confirmPass.setError("password not match");
                        return;
                    }
                    String em=email.getText().toString().trim();
                    String pa=pass.getText().toString().trim();
                    mydb.insertAdminData(em,pa);
                    Toast.makeText(SignUpActivity.this, "SignUp successfully", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SignUpActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}