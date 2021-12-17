package com.example.employeemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.employeemanagementsystem.Database.Database;

public class EmployeeActivity extends AppCompatActivity {
Database mydb=new Database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        EditText emp_id=findViewById(R.id.emp_id_emp);
        EditText emp_name=findViewById(R.id.emp_name_emp);
        EditText emp_position=findViewById(R.id.emp_position_emp);
        EditText daily_salary=findViewById(R.id.daily_salary_emp);
        Button add=findViewById(R.id.add_emp);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckEditTextInput check=new CheckEditTextInput(EmployeeActivity.this);
                if(check.validInput(emp_id)&&check.validInput(emp_name)&&check.validInput(emp_position)&&check.validInput(daily_salary))
                {
                    String id_str=emp_id.getText().toString().trim();
                    String name=emp_name.getText().toString().trim();
                    String position=emp_position.getText().toString().trim();
                    String dailySalary_str=daily_salary.getText().toString().trim();
                    int id=Integer.parseInt(id_str);
                    int dailySalary=Integer.parseInt(dailySalary_str);

                    if(mydb.isIdExist(id_str))
                    {
                        emp_id.setError("ID exist");
                        return;
                    }
                    mydb.insertEmployeeData(new Employee(name,position,id,dailySalary));
                    Toast.makeText(EmployeeActivity.this, "Inserted successfully", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(EmployeeActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }
}