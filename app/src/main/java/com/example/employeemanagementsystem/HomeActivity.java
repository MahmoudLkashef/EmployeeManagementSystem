package com.example.employeemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {
CardView newEmp,viewInfo,salary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        newEmp=findViewById(R.id.new_employee);
        viewInfo=findViewById(R.id.view);
        salary=findViewById(R.id.salary);

        newEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,EmployeeActivity.class);
                startActivity(intent);
            }
        });
        viewInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,ViewActivity.class);
                startActivity(intent);
            }
        });
        salary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,SalaryActivity.class);
                startActivity(intent);
            }
        });
    }
}