package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeLoginDto {
	
	private Integer employeeId;
	
	private String employeeName;
	
	private String employeeDesignation;
	
	private String employeePassword;
 
	private Long employeePhoneno;
	
	private String employeeEmail;
}
