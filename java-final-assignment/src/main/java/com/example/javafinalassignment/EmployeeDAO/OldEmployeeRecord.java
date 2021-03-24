package com.example.javafinalassignment.EmployeeDAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class OldEmployeeRecord {
    List<SeperatedEmployee>  list= new ArrayList<SeperatedEmployee>();

    public int seperationCount(String curDate)
    {
        return (int) list.stream().filter(
                e -> e.getDateofSeparation().equals(curDate)
        ).count();
    }

}
