package com.example.javafinalassignment.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Employee-Table")
public class Employee {
    @Id
    @GeneratedValue
    private int id;
    private String empcode;
    private String email;
    private String fname;
    private String lname;
    private String address;
    private String city;
    private int pin;
    private String state;
    private long primaryContact;
    private long secondaryContact;
    private String role;
    private String jDate;
    private Calendar joiningDate;
    private Calendar probationPeriodEndDate;
    private Calendar noticePeriodEndDate;

}



