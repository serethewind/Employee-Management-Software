package com.springboot.ems.controller;

import com.springboot.ems.entity.Employee;
import com.springboot.ems.payload.EmployeeRequest;
import com.springboot.ems.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private EmployeeService employeeService; //accessing the interface in the service package

    public EmployeeController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }

    //build create employee REST API
    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return new ResponseEntity<Employee>(employeeService.saveEmployee(employeeRequest), HttpStatus.CREATED);
    }

    //build get All employee Rest API
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    //build get single employee by Id
    //It's better to return a ResponseEntity than to return the Object. The resonse Entity takes in status
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getSingleEmployee(@PathVariable("id") long employeeId) {
//        return employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
    }

    //build update employee REST API
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long employeeId, @RequestBody Employee employee){
    return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, employeeId), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long employeeId){
       employeeService.deleteEmployee(employeeId);
       return new ResponseEntity<String>("Employee deleted successfully", HttpStatus.OK);
    }

}
