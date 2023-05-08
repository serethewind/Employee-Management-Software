package com.springboot.ems.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class EmployeeRequest {

    private String firstName;
    private String lastName;
    private String email;
}
