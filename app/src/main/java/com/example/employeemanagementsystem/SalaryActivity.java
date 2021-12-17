package com.example.employeemanagementsystem;

import androidx.annotation.ArrayRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.employeemanagementsystem.Database.Database;

import java.util.ArrayList;

public class SalaryActivity extends AppCompatActivity {
    Database mydb = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary);
        LinearLayout emp_data=findViewById(R.id.emp_data);
        EditText empId = findViewById(R.id.emp_id_salary);
        EditText workedDays = findViewById(R.id.worked_days_salary);
        Button getSalary = findViewById(R.id.getSalary_btn);
        TextView emp_id, emp_name, emp_position, daysWorked, dailySalary, total;
        emp_id = findViewById(R.id.emp_id_res);
        emp_name = findViewById(R.id.emp_name_res);
        emp_position = findViewById(R.id.emp_postion_res);
        daysWorked = findViewById(R.id.days_worked_res);
        dailySalary = findViewById(R.id.daily_salary_res);
        total = findViewById(R.id.total_res);


        getSalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckEditTextInput check = new CheckEditTextInput(SalaryActivity.this);
                if (check.validInput(empId) && check.validInput(workedDays)) {
                    String id = empId.getText().toString().trim();
                    String wk = workedDays.getText().toString().trim();

                    if (mydb.isIdExist(id)) {
                        int calcTotal = mydb.getAllEmployeeInfo(id).getDailySalary() * Integer.parseInt(wk);
                        String totalSalary = String.valueOf(calcTotal);
                        mydb.insertEmployeeInfo(id, wk, totalSalary);
                        emp_id.setText(String.valueOf(mydb.getAllEmployeeInfo(id).getId()));
                        daysWorked.setText(String.valueOf(mydb.getAllEmployeeInfo(id).getDaysWorked()));
                        dailySalary.setText(String.valueOf(mydb.getAllEmployeeInfo(id).getDailySalary()));
                        total.setText(String.valueOf(mydb.getAllEmployeeInfo(id).getTotal()));
                        emp_name.setText(mydb.getAllEmployeeInfo(id).getName());
                        emp_position.setText(mydb.getAllEmployeeInfo(id).getPosition());
                        emp_data.setVisibility(View.VISIBLE);
                        return;
                    }
                    empId.setError("ID not exist");
                }
            }
        });
    }
}