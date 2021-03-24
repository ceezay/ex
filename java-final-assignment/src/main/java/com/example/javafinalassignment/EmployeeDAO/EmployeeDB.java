package com.example.javafinalassignment.EmployeeDAO;

import com.example.javafinalassignment.entities.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDB {

    public List<Employee> list= new ArrayList<Employee>();

    public int employeeCount()
    {
        return list.size();
    }
    public boolean addEmployee(Employee emp)
    {
        try {
            list.add(emp);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    public int onboardingCount(String curDate)
    {
        return (int) list.stream().filter(
                e -> e.getJoiningDate().equals(curDate)
        ).count();
    }
}
