package com.example.demo.dto;
import java.time.LocalDate;

import lombok.Data;

@Data
public class SprintDTO {
	private Integer sprintId;
	private String sprintName;
	private String sprintGoal;
	private LocalDate sprintStartDate;
	private LocalDate sprintEndDate;
}
