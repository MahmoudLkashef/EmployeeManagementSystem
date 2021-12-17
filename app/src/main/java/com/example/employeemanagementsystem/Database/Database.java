package com.example.employeemanagementsystem.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.employeemanagementsystem.Employee;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public static final String DatabaseName="employee.db";
    public Database(@Nullable Context context) {
        super(context,DatabaseName , null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table employees(id integer primary key AUTOINCREMENT,emp_id integer,emp_name text,emp_position text,daily_salary integer,days_worked integer,total integer)");
        sqLiteDatabase.execSQL("create table admin(email text,pass text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS employees");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS admin");
        onCreate(sqLiteDatabase);
    }
    public void insertAdminData(String email,String pass)
    {
        SQLiteDatabase sq=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put("email",email);
        values.put("pass",pass);

        sq.insert("admin",null,values);
    }
    public void insertEmployeeData(Employee e)
    {
        SQLiteDatabase sq=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put("emp_id",e.getId());
        values.put("emp_name",e.getName());
        values.put("emp_position",e.getPosition());
        values.put("daily_salary",e.getDailySalary());
        values.put("days_worked",e.getDaysWorked());
        values.put("total",e.getTotal());

        sq.insert("employees",null,values);
    }
    public ArrayList<Employee> getAllEmployeesData()
    {
        ArrayList<Employee>arrayList=new ArrayList<>();
        SQLiteDatabase sq=this.getReadableDatabase();
        Cursor cursor=sq.rawQuery("select * from employees",null);
        cursor.moveToFirst();
        while(cursor.isAfterLast()==false)
        {
            int id=cursor.getInt(cursor.getColumnIndexOrThrow("emp_id"));
            String name=cursor.getString(cursor.getColumnIndexOrThrow("emp_name"));
            String position=cursor.getString(cursor.getColumnIndexOrThrow("emp_position"));
            int daily_salary=cursor.getInt(cursor.getColumnIndexOrThrow("daily_salary"));
            int days_worked=cursor.getInt(cursor.getColumnIndexOrThrow("days_worked"));
            int total=cursor.getInt(cursor.getColumnIndexOrThrow("total"));

            arrayList.add(new Employee(name,position,id,days_worked,daily_salary,total));
            cursor.moveToNext();
        }
        return arrayList;
    }
    public ArrayList<Employee> getAllEmployeesDataSorted()
    {
        ArrayList<Employee>arrayList=new ArrayList<>();
        SQLiteDatabase sq=this.getReadableDatabase();
        Cursor cursor=sq.rawQuery("select * from employees order by emp_id asc",null);
        cursor.moveToFirst();
        while(cursor.isAfterLast()==false)
        {
            int id=cursor.getInt(cursor.getColumnIndexOrThrow("emp_id"));
            String name=cursor.getString(cursor.getColumnIndexOrThrow("emp_name"));
            String position=cursor.getString(cursor.getColumnIndexOrThrow("emp_position"));
            int daily_salary=cursor.getInt(cursor.getColumnIndexOrThrow("daily_salary"));
            int days_worked=cursor.getInt(cursor.getColumnIndexOrThrow("days_worked"));
            int total=cursor.getInt(cursor.getColumnIndexOrThrow("total"));

            arrayList.add(new Employee(name,position,id,days_worked,daily_salary,total));
            cursor.moveToNext();
        }
        return arrayList;
    }
    public Employee getAllEmployeeInfo(String id)
    {
        //ArrayList<Employee>arrayList=new ArrayList<>();
        SQLiteDatabase sq=this.getReadableDatabase();
        Cursor cursor=sq.rawQuery("select * from employees where emp_id=?",new String[]{id});
        cursor.moveToFirst();

        Employee e=null;
        while(cursor.isAfterLast()==false)
        {
            int emp_id=cursor.getInt(cursor.getColumnIndexOrThrow("emp_id"));
            String emp_name=cursor.getString(cursor.getColumnIndexOrThrow("emp_name"));
            String emp_position=cursor.getString(cursor.getColumnIndexOrThrow("emp_position"));
            int daily_salary=cursor.getInt(cursor.getColumnIndexOrThrow("daily_salary"));
            int days_worked=cursor.getInt(cursor.getColumnIndexOrThrow("days_worked"));
            int total=cursor.getInt(cursor.getColumnIndexOrThrow("total"));

            e=new Employee(emp_name,emp_position,emp_id,days_worked,daily_salary,total);
            cursor.moveToNext();
        }
        Log.i("ex",e.getName());
        return e;

    }

    public boolean isAdminExist(String email,String pass)
    {
        SQLiteDatabase sq=this.getWritableDatabase();
        Cursor cursor=sq.rawQuery("select * from admin where email=? and pass=?",new String[]{email,pass});
        if(cursor.getCount()>0)return true;
        else return false;
    }
    public boolean isIdExist(String id)
    {
        SQLiteDatabase sq=this.getWritableDatabase();
        Cursor cursor=sq.rawQuery("select * from employees where emp_id=?",new String[]{id});
        if(cursor.getCount()>0)return true;
        else return false;
    }
    public void insertEmployeeInfo(String id,String workedDays,String total)
    {
        SQLiteDatabase sq=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("days_worked",workedDays);
        values.put("total",total);
        sq.update("employees",values,"emp_id=?",new String[]{id});
    }
    public void deleteEmployee(String id)
    {
        SQLiteDatabase sq=this.getWritableDatabase();
        sq.delete("employees", "emp_id=?", new String[]{id});
    }
    public void updateEmployeeInfo(String id,Employee e)
    {
        //emp_id integer,emp_name text,emp_position text,daily_salary integer,days_worked integer,total integer
        SQLiteDatabase sq = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("emp_id",String.valueOf(e.getId()));
        values.put("daily_salary",String.valueOf(e.getDailySalary()));
        values.put("emp_position",e.getPosition());
        values.put("emp_name",e.getName());
        values.put("total",String.valueOf(e.getTotal()));
        sq.update("employees",values,"emp_id=?",new String[]{id});
    }

}
