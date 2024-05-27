package com.example.basic;

public class Employee {
    private String Employee_Id;
    private String First_Name;
    private Double Salary;
    private String Last_Name;
    private String Gender;
    public Employee(String i,String f,String l,Double s,String g)
    {
        Employee_Id=i;
        First_Name=f;
        Last_Name=l;
        Salary=s;
        Gender=g;
    }
    public String getEmployee_Id()
    {
        return Employee_Id;
    }
    public String getFirst_Name()
    {
        return First_Name;
    }
    public String getLast_Name(){
        return Last_Name;
    }
    public Double getSalary()
    {
        return Salary;
    }
    public String getGender()
    {
        return Gender;
    }
}
