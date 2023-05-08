package com.springboot.ems.service.impl;

import com.springboot.ems.exception.ResourceNotFoundException;
import com.springboot.ems.entity.Employee;
import com.springboot.ems.payload.EmployeeRequest;
import com.springboot.ems.repository.EmployeeRepository;
import com.springboot.ems.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository; //passing the repository into the service


    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setEmail(employeeRequest.getEmail());
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

//    @Override
//    public Employee getSingleEmployee(long employeeId) {
//        return employeeRepository.findById(employeeId);
//    }

    @Override
    public Employee getEmployeeById(long employeeId) {
//        Optional<Employee> employee = employeeRepository.findById(employeeId);
//        if (employee.isPresent()){
//            return employee.get();
//        } else {
//            throw new ResourceNotFoundException("Employee", "Id", employeeId);
//        }

        return employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", employeeId)); //using lambda function
    }

    @Override
    public Employee updateEmployee(Employee employee, long employeeId) {
        //check if employee with the id exist in database.
        Employee existingEmployee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", employeeId));

        //update existing employee with the details, the client is passing.
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
//        save updated existing employee

        employeeRepository.save(existingEmployee);
        return existingEmployee;
    }

    @Override
    public void deleteEmployee(long employeeId) {
        //check if employee with id exist
        Employee existingEmployee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", employeeId));
        employeeRepository.deleteById(employeeId);
    }
}
