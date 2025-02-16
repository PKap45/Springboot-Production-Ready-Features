package com.springboot.prodreadyfeatures.Client;

import com.springboot.prodreadyfeatures.DTO.EmployeeDto;

import java.util.List;

public interface EmployeeClient {

    List<EmployeeDto> getAllEmployees();

    EmployeeDto getEmployeeById(long employeeId);

    EmployeeDto createNewEmployee(EmployeeDto employeeDto);


}
