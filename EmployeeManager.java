package com.example.restservice;

import org.springframework.stereotype.Repository;
import com.example.restservice.Employee;
import com.example.restservice.Employees;

@Repository
public class EmployeeManager {
     private final Employees employees = new Employees();


  public EmployeeManager(){
      employees.getEmployeeList().add(new Employee(1, "Prem", "Tiwari", "prem@gmail.com", "Software Engineer"));
      employees.getEmployeeList().add(new Employee(2, "Vikash", "Kumar", "vikash@gmail.com", "Manager"));
      employees.getEmployeeList().add(new Employee(3, "Ritesh", "Ojha", "ritesh@gmail.com", "DevOps Engineer"));
  }

  public Employees getAllEmployees(){
      return employees;
  }

  public Employee getEmployeesByEmployee_id(int employee_id){
      return employees.getEmployeeList()
              .stream()
              .filter(employee -> employee.getEmployee_id() == employee_id)
              .findFirst()
              .orElse(null);
  }

  public  void addEmployee(Employee employee){
      employees.getEmployeeList().add(employee);
  }
}
