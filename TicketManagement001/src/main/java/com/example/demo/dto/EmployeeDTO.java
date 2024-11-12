package com.example.demo.dto;

import java.util.List;

import com.example.demo.model.Ticket;

import lombok.Data;

@Data
public class EmployeeDTO {
	private Integer employeeId;
	private String employeeName;
	private String employeePassword;
	private String employeeDesignation;
	private String employeeEmail;
	private Long employeePhoneno;
	private List<Ticket> tickets;
}
