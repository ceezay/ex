package com.example.javafinalassignment.service;

import com.example.javafinalassignment.entities.Employee;
import com.example.javafinalassignment.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;


@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    private static Logger log = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }
    // Get Employee

    public Employee getByEmpCode( String empcode){
        return employeeRepository.getByEmpCode(empcode);
    }

    //Create Employee

    public Employee create(Employee employee)   {
        employeeRepository.save(employee);
        return employee;
    }

    /*
    Function to Onboard Employee & set email & set EmpCode
     */

    public Employee onBoarding(Employee employee) throws ParseException {
        employee.setEmail(generateEmailID(employee));
        employee.setEmpcode(generateEmpCode(employee));

        // To Save Employee Joining Date & Save Notice Period End Date
        setDates(employee);

        return create(employee);
    }

    // To Get A list of Employees OnBoarded on a particular date
    public List <Employee> OnboardedDate(String date) throws ParseException {
        Calendar calen = dateFormater(date);

        return employeeRepository.employeeOnboardedOnThisDate(calen);
    }

    // Function to get a list of employees ending their Notice period on Given Date
    public List <Employee> noticeDate(String date) throws ParseException {
        Calendar calen = dateFormater(date);

        return employeeRepository.employeeNoticeOnThisDate(calen);
    }

    public Employee sendNotice(String empCode) throws ParseException {
        Employee emp = employeeRepository.getByEmpCode(empCode);
        int noticePeriod = getNoticePeriod(emp);
        emp.setNoticePeriodEndDate(setNoticeDate(noticePeriod));
        return emp;
    }


    //Generate Email ID
    public void setDates (Employee employee) throws ParseException {

        Calendar calen = dateFormater(employee.getJDate());
        employee.setJoiningDate(calen);
        calen.add(Calendar.MONTH, + 6);
        employee.setProbationPeriodEndDate(calen);
    }

    public Calendar dateFormater (String date) throws ParseException {

        SimpleDateFormat formater  = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calen = Calendar.getInstance();

        try{
            calen.setTime(formater.parse(date));
        } catch (ParseException Pex){
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyy");
            String formattedDate = myDateObj.format(myFormatObj);
            calen.setTime(formater.parse(formattedDate));
        }
        return calen;
    }

    public Calendar setNoticeDate(int date) throws ParseException {
        Calendar calen = Calendar.getInstance();
        SimpleDateFormat formater  = new SimpleDateFormat("dd/MM/yyyy");
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyy");
        String formattedDate = myDateObj.format(myFormatObj);
        calen.setTime(formater.parse(formattedDate));
        calen.add(Calendar.DAY_OF_MONTH,date);
        return calen;
    }

    public int getNoticePeriod(Employee employee){
        String role = employee.getRole();
        if( role == "FTE")
            return 45;
        else if(role == "INT" || role == "STE")
            return 30;
        else{
            return 1;
        }
    }

    public String generateEmailID (Employee employee){
        String email = employee.getFname()+"."+employee.getLname()+"@hoppipolla.com";
        int i = 0;
        while (i < 50){
            if (employeeRepository.checkAvailEmailId(email)){
                i++;
                email = employee.getFname()+"."+employee.getLname()+i+"@hoppipolla.com";
            }else
                break;
        }
        return email;
    }

    // Generating Employee 4 Digit ID
    public String getStringId(Employee employee) {
        String strId = String.valueOf(employee.getId());
        if(strId.length() > 3)
            return strId;
        else if( strId.length() > 2)
            return "0"+strId;
        else if(strId.length() >1)
            return "00"+strId;
        else
            return "000"+strId;
    }

    //Generate EMP CODE
    public String generateEmpCode(Employee employee){
        String empCode = "HOP-"+employee.getRole()+getStringId(employee);
        //Add Functionality Checking Duplicates
        return empCode;

    }


}
