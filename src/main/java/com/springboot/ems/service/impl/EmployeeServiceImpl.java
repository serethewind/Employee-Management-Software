package com.springboot.ems.service.impl;

import com.springboot.ems.exception.EmailAlreadyExistsException;
import com.springboot.ems.exception.ResourceNotFoundException;
import com.springboot.ems.entity.Employee;
import com.springboot.ems.dto.EmployeeDto;
import com.springboot.ems.repository.EmployeeRepository;
import com.springboot.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository; //passing the repository into the service

    private ModelMapper modelMapper;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeRequest) {
//        Employee employee = new Employee();
//        employee.setFirstName(employeeRequest.getFirstName());
//        employee.setLastName(employeeRequest.getLastName());
//        employee.setEmail(employeeRequest.getEmail());
        Optional<Employee> compareEmployee = employeeRepository.findByEmail(employeeRequest.getEmail());

        if (compareEmployee.isPresent()){
            throw new EmailAlreadyExistsException("Email Already Exists for User");
        }

        Employee employee = modelMapper.map(employeeRequest, Employee.class);
        return modelMapper.map(employeeRepository.save(employee), EmployeeDto.class);
//        return new EmailAlreadyExistsException("Email for user already exists!");
}

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        for (Employee employee : employeeList) {
            EmployeeDto mappedEmployee = modelMapper.map(employee, EmployeeDto.class);
            employeeDtoList.add(mappedEmployee);
        }
        return employeeDtoList;
    }

    @Override
    public EmployeeDto getEmployeeById(long employeeId) {

        return modelMapper.map(employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", employeeId)), EmployeeDto.class);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employee, long employeeId) {
        //check if employee with the id exist in database.
        Employee existingEmployee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", employeeId));

        //update existing employee with the details, the client is passing.
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
//        save updated existing employee

        employeeRepository.save(existingEmployee);
        return modelMapper.map(existingEmployee, EmployeeDto.class);
    }

    @Override
    public String deleteEmployee(long employeeId) {
        //check if employee with id exist
        Employee existingEmployee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", employeeId));
//        boolean status = employeeRepository.existsById(employeeId);
        employeeRepository.delete(existingEmployee);
        return existingEmployee.getFirstName() + "has been successfully removed.";
    }
}
