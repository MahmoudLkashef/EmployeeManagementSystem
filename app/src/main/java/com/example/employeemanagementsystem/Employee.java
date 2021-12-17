package com.example.employeemanagementsystem;

public class Employee {
    String name,position;
    int id,daysWorked,dailySalary,total;

    public Employee(String name, String position, int id, int daysWorked, int dailySalary, int total) {
        this.name = name;
        this.position = position;
        this.id = id;
        this.daysWorked = daysWorked;
        this.dailySalary = dailySalary;
        this.total = total;
    }

    public Employee(String name, String position, int id, int dailySalary) {
        this.name = name;
        this.position = position;
        this.id = id;
        this.dailySalary = dailySalary;
    }

    public Employee(String name, String position, int id, int dailySalary, int total) {
        this.name = name;
        this.position = position;
        this.id = id;
        this.dailySalary = dailySalary;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDaysWorked() {
        return daysWorked;
    }

    public void setDaysWorked(int daysWorked) {
        this.daysWorked = daysWorked;
    }

    public int getDailySalary() {
        return dailySalary;
    }

    public void setDailySalary(int dailySalary) {
        this.dailySalary = dailySalary;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
