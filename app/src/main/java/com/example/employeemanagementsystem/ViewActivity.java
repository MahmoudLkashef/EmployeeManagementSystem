package com.example.employeemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

import com.example.employeemanagementsystem.Database.Database;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity implements EmployeeAdapter.onUserClicked {
Database mydb=new Database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        RecyclerView recyclerView=findViewById(R.id.rv);
        SearchView search=findViewById(R.id.search);


        EmployeeAdapter adapter=new EmployeeAdapter(this,mydb.getAllEmployeesDataSorted(),this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.filter(s.trim(), getApplicationContext());
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.filter(s.trim(), getApplicationContext());
                return true;
            }
        });
    }

    @Override
    public void onUserSingleClicked(Employee e) {
        EditEmployeeDialog dialog=new EditEmployeeDialog(ViewActivity.this);
        dialog.EditCarDialog(e);
    }
}