package com.springboot.ems.controller;

import com.springboot.ems.dto.EmployeeDto;
import com.springboot.ems.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {


    private EmployeeService employeeService; //accessing the interface in the service package

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //build create employee REST API
    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody @Valid EmployeeDto employeeRequest) {
        return new ResponseEntity<EmployeeDto>(employeeService.saveEmployee(employeeRequest), HttpStatus.CREATED);
    }

    //build get All employee Rest API
    @GetMapping
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    //build get single employee by Id
    //It's better to return a ResponseEntity than to return the Object. The response Entity takes in status
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getSingleEmployee(@PathVariable("id") long employeeId) {
//        return employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<EmployeeDto>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
    }

    //build update employee REST API
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") long employeeId, @RequestBody @Valid EmployeeDto employee){
    return new ResponseEntity<EmployeeDto>(employeeService.updateEmployee(employee, employeeId), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long employeeId){
       return new ResponseEntity<String>(employeeService.deleteEmployee(employeeId), HttpStatus.OK);
    }

}
