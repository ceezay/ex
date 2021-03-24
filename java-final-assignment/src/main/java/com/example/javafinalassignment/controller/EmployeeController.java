package com.example.javafinalassignment.controller;

import com.example.javafinalassignment.entities.Employee;
import com.example.javafinalassignment.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
public class EmployeeController {

    private EmployeeService employeeService;

    private static Logger log = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }


    @PostMapping("/emp/onboard")
    public  String  onboard(@RequestBody Employee employee) throws ParseException {
        employeeService.onBoarding(employee);
        return "Employee Code : " + employee.getEmpcode() + " Employee Email : " +employee.getEmail();
    }

    @GetMapping("/emp/ondate/{date}")
    public List<Employee > getOndate(@PathVariable String date) throws ParseException {
        return employeeService.OnboardedDate(date);
    }
    @GetMapping("/emp/noticeDate/{date}")
    public List<Employee > noticeDate(@PathVariable String date) throws ParseException {
        return employeeService.noticeDate(date);
    }

    @GetMapping("/emp/{empcode}")
    public Employee getByempCode (@PathVariable String empcode){
        return employeeService.getByEmpCode(empcode);
    }

    @GetMapping("/emp/notice/{empcode}")
    public Employee sendNotice (@PathVariable String empcode) throws ParseException {
        return employeeService.sendNotice(empcode);
    }


}
