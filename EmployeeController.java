package com.example.restservice;

import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.restservice.EmployeeManager;
import com.example.restservice.Employee;
import com.example.restservice.Employees;

@RestController

@RequestMapping("/employees")

public class EmployeeController {
    private final EmployeeManager employeeManager;

    public EmployeeController(EmployeeManager employeeManager){
        this.employeeManager = employeeManager;
    }
  @GetMapping(produces = "application/json")
    public Employees getAllEmployees(){
        return employeeManager.getAllEmployees();
  }

  @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> addEmployee(
            @RequestBody Employee employee){

        int employee_id = employeeManager.getAllEmployees()
                .getEmployeeList()
                .size() + 1;

        employee.setEmployee_id(employee_id);
        employeeManager.addEmployee(employee);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{employee_id}")
                .buildAndExpand(employee.getEmployee_id())
                .toUri();

        return ResponseEntity.created(location).build();
  }

}
