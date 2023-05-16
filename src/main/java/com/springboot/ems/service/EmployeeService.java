package com.springboot.ems.service;

import com.springboot.ems.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    //method that saves an employee object of the Employee Class created in the model package.
    EmployeeDto saveEmployee(EmployeeDto employeeRequest);

    //method to get all of the employees

    List<EmployeeDto> getAllEmployees();

    //method to get single employee
    EmployeeDto getEmployeeById(long employeeId);

    EmployeeDto updateEmployee(EmployeeDto employeeDto, long employeeId);

    String deleteEmployee(long employeeId);

}
