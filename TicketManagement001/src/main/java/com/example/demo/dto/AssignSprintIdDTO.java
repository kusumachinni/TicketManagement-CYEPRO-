package com.example.demo.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignSprintIdDTO 
{
	private Integer sprintId;
	private List<Integer> ticketIds;
}

