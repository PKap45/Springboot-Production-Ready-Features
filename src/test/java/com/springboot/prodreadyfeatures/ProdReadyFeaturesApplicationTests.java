package com.springboot.prodreadyfeatures;

import com.springboot.prodreadyfeatures.Client.EmployeeClient;
import com.springboot.prodreadyfeatures.DTO.EmployeeDto;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class ProdReadyFeaturesApplicationTests {


	@Autowired
	private EmployeeClient employeeClient;
	@Test
	@Order(3)
	void getAllEmployees()
	{
		List<EmployeeDto> employees = employeeClient.getAllEmployees();
		System.out.println(employees);

	}

	@Test
	@Order(2)
	void getEmployeeById()
	{
		EmployeeDto employeeDto = employeeClient.getEmployeeById(2L);
		System.out.println(employeeDto);
	}

	@Test
	@Order(1)
	void createNewEmployee()
	{
		EmployeeDto employeeDto = new EmployeeDto(null,"Amit","30", "amitsharma@example.com", "USER", 120000.0, LocalDate.of(2023, 6,15), true);
		EmployeeDto newEmployee = employeeClient.createNewEmployee(employeeDto);
		System.out.println(newEmployee);
	}

}
