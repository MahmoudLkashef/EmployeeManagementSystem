package com.example.employeemanagementsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employeemanagementsystem.Database.Database;

import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {
    Context context;
    ArrayList <Employee>arrayList=new ArrayList<>();
    onUserClicked onUserClicked;

    public EmployeeAdapter(Context context, ArrayList<Employee> arrayList,onUserClicked onUserClicked) {
        this.context = context;
        this.arrayList = arrayList;
        this.onUserClicked=onUserClicked;
    }
    public interface onUserClicked
    {
        public void onUserSingleClicked(Employee e);
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_emp,parent,false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee e=arrayList.get(position);
        holder.emp_id.setText(String.valueOf(e.getId()));
        holder.emp_name.setText(e.getName());
        holder.emp_position.setText(e.getPosition());
        holder.daily_salary.setText(String.valueOf(e.getDailySalary()));
        holder.days_worked.setText(String.valueOf(e.getDaysWorked()));
        holder.total.setText(String.valueOf(e.getTotal()));
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onUserClicked.onUserSingleClicked(e);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder
    {
        TextView emp_id,emp_name,emp_position,daily_salary,days_worked,total;
        ConstraintLayout parent;
        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            emp_id=itemView.findViewById(R.id.emp_id);
            emp_name=itemView.findViewById(R.id.emp_name);
            emp_position=itemView.findViewById(R.id.emp_postion);
            daily_salary=itemView.findViewById(R.id.daily_salary);
            days_worked=itemView.findViewById(R.id.days_worked);
            total=itemView.findViewById(R.id.total);
            parent=itemView.findViewById(R.id.parent);
        }
    }
    public void filter(String text,Context context) {
        Database mydb=new Database(context);
        arrayList.clear();
        if(text.isEmpty()){
            arrayList.addAll(mydb.getAllEmployeesDataSorted());
        } else{
            text = text.toLowerCase();
            for(Employee item: mydb.getAllEmployeesDataSorted()){
                if(item.getName().toLowerCase().contains(text) || String.valueOf(item.getId()).toLowerCase().contains(text)||item.getPosition().toLowerCase().contains(text)){
                    arrayList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }
}
