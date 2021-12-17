package com.example.employeemanagementsystem;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.employeemanagementsystem.Database.Database;

public class EditEmployeeDialog {
    Context context;

    public EditEmployeeDialog(Context context) {
        this.context = context;
    }

    public void EditCarDialog(Employee e)
    {
        Dialog dialog=new Dialog(context);
        dialog.setContentView(R.layout.custom_edit_dialog);
        dialog.setCancelable(false);

        Button edit,delete;
        EditText emp_id,emp_name,emp_position,daily_salary;
        ImageView close;
        emp_id=dialog.findViewById(R.id.emp_id_edit);
        emp_name=dialog.findViewById(R.id.emp_name_edit);
        emp_position=dialog.findViewById(R.id.emp_position_edit);
        daily_salary=dialog.findViewById(R.id.daily_salary_edit);
        edit=dialog.findViewById(R.id.edit_btn);
        delete=dialog.findViewById(R.id.delete_btn_edit);

        close=dialog.findViewById(R.id.close_edit);


        close.setOnClickListener(view -> {
            dialog.dismiss();
        });

        edit.setOnClickListener(view -> {
            CheckEditTextInput check=new CheckEditTextInput(context);
            if(check.validInput(emp_id)&&check.validInput(emp_name)&&check.validInput(emp_position)&&check.validInput(daily_salary))
            {
                Database mydb=new Database(context);
                String id=emp_id.getText().toString().trim();
                String empName=emp_name.getText().toString().trim();
                String empPosition=emp_position.getText().toString().trim();
                String dailySalary=daily_salary.getText().toString().trim();
                int daysWorked= mydb.getAllEmployeeInfo(String.valueOf(e.getId())).getDaysWorked();
                int total=daysWorked*Integer.parseInt(dailySalary);
                mydb.updateEmployeeInfo(String.valueOf(e.getId()),new Employee(empName,empPosition,Integer.parseInt(id),Integer.parseInt(dailySalary),total));
                Toast.makeText(context, "Done Successfully", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                Intent intent=new Intent(context,context.getClass());
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database mydb=new Database(context);
                mydb.deleteEmployee(String.valueOf(e.getId()));
                Toast.makeText(context, "Deleted successfully", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                Intent intent=new Intent(context,context.getClass());
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });
        dialog.show();
    }
}
