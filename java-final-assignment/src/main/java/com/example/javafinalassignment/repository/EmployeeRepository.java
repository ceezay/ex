package com.example.javafinalassignment.repository;

import com.example.javafinalassignment.entities.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeRepository {

    private static  List<Employee> employees = new ArrayList<>();

    public Employee getByEmpCode(String empCode){
        return employees.stream().filter(employee -> employee.getEmpcode() == empCode).findFirst().orElse(null);
    }

    public Employee save(Employee employee){
        employees.add(employee);
        System.out.print("Printing the List " + employee.toString());
        return employee;
    }

    public boolean checkAvailEmailId(String email){
        return employees.stream().anyMatch(employee -> employee.getEmail() == email);
    }

    public List <Employee> employeeOnboardedOnThisDate(Calendar Date){
        return employees.stream().filter(employee -> employee.getJoiningDate().equals(Date)).collect(Collectors.toList());
    }

    public List <Employee> employeeNoticeOnThisDate(Calendar Date){
        return employees.stream().filter(employee -> employee.getNoticePeriodEndDate().equals(Date)).collect(Collectors.toList());
    }

}
