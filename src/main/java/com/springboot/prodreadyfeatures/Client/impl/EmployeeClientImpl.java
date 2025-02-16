package com.springboot.prodreadyfeatures.Client.impl;

import com.springboot.prodreadyfeatures.Advices.ApiResposne;
import com.springboot.prodreadyfeatures.Client.EmployeeClient;
import com.springboot.prodreadyfeatures.DTO.EmployeeDto;
import com.springboot.prodreadyfeatures.Exception.ResourceNotFound;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;
    private static final Logger log = LoggerFactory.getLogger(EmployeeClientImpl.class);

    @Override
    public List<EmployeeDto> getAllEmployees() {
        log.trace("Entering getAllEmployees()");

        try {
            log.debug("Making GET request to fetch all employees...");
            ApiResposne<List<EmployeeDto>> listofEmployees = restClient.get()
                    .uri("employee/getAllEmp")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});

            log.debug("Received response with {} employees.", listofEmployees.getData().size());
            log.trace("Exiting getAllEmployees()");
            return listofEmployees.getData();
        } catch (Exception e) {
            log.error("Error occurred while fetching employees: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDto getEmployeeById(long employeeId) {
        log.trace("Entering getEmployeeById() with employeeId: {}", employeeId);

        try {
            log.debug("Making GET request to fetch employee with ID: {}", employeeId);
            ApiResposne<EmployeeDto> employee = restClient.get()
                    .uri("employee/{employeeId}", employeeId)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});

            log.debug("Received employee details: {}", employee.getData());
            log.trace("Exiting getEmployeeById()");
            return employee.getData();
        } catch (Exception e) {
            log.error("Error fetching employee with ID {}: {}", employeeId, e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDto createNewEmployee(EmployeeDto employeeDto) {
        log.trace("Entering createNewEmployee() with employeeDto: {}", employeeDto);

        try {
            log.debug("Making POST request to create a new employee...");
            ApiResposne<EmployeeDto> savedEmployee = restClient.post()
                    .uri("employee/saveEmployee")
                    .body(employeeDto)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        String errorResponse = new String(res.getBody().readAllBytes());
                        log.error("Failed to create employee: {}", errorResponse);
                        throw new ResourceNotFound("Employee not Created");
                    })
                    .body(new ParameterizedTypeReference<>() {});

            log.debug("Employee created successfully with ID: {}", savedEmployee.getData().getId());
            log.trace("Exiting createNewEmployee()");
            return savedEmployee.getData();
        } catch (Exception e) {
            log.error("Error creating employee: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}

