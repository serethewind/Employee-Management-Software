package com.springboot.ems.service;

import com.springboot.ems.entity.Employee;
import com.springboot.ems.payload.EmployeeRequest;

import java.util.List;

public interface EmployeeService {

    //method that saves an employee object of the Employee Class created in the model package.
    Employee saveEmployee(EmployeeRequest employeeRequest);

    //method to get all of the employees

    List<Employee> getAllEmployees();

    //method to get single employee
    Employee getEmployeeById(long employeeId);

    Employee updateEmployee(Employee employee, long employeeId);

    void deleteEmployee(long employeeId);

}
