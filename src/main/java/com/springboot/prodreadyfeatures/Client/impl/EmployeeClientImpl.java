package com.springboot.prodreadyfeatures.Client.impl;

import com.springboot.prodreadyfeatures.Advices.ApiResposne;
import com.springboot.prodreadyfeatures.Client.EmployeeClient;
import com.springboot.prodreadyfeatures.DTO.EmployeeDto;
import com.springboot.prodreadyfeatures.Exception.ResourceNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;


    @Override
    public List<EmployeeDto> getAllEmployees() {
        try {
            ApiResposne<List<EmployeeDto>> listofEmployees = restClient.get()
                    .uri("employee/getAllEmp")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            ;
            return listofEmployees.getData();
        }catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDto getEmployeeById(long employeeId) {
        try{
            ApiResposne<EmployeeDto> employee = restClient.get()
                    .uri("employee/{employeeId}",employeeId)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>(){});
            return employee.getData();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @Override
    public EmployeeDto createNewEmployee(EmployeeDto employeeDto) {
        try {
            ApiResposne<EmployeeDto> savedEmployee = restClient.post()
                    .uri("employee/saveEmployee")
                    .body(employeeDto)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req,res)->
                    {
                        System.out.println(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFound("Employee not Created");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });
            return savedEmployee.getData();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }


}
