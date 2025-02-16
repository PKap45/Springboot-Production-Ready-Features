package com.springboot.prodreadyfeatures.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Long id;
    private String name;

    private String age;

    private String email;

    private String role;// USER ADMIN

    private double salary;

    private LocalDate dateOfJoining;

    @JsonProperty("isActive")
    private Boolean isActive;



    @Override
    public String toString() {
        return "EmployeeDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", salary=" + salary +
                ", dateOfJoining='" + dateOfJoining + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
