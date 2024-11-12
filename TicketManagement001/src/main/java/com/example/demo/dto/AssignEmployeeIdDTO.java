package com.example.demo.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignEmployeeIdDTO 
{
	private Integer projectId;
	private List<Integer> employeeIds;
}