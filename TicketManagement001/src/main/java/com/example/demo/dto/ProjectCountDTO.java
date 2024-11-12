package com.example.demo.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectCountDTO 
{
	public ProjectCountDTO(Integer projectId2, String projectName2, String projectDescription2, Integer employeeCount2,
			Integer ticketCount, Integer sprintCount) {
		// TODO Auto-generated constructor stub
	}
	private Integer projectId;
	private String projectName;
	private String projectDescription;
	private Integer employeeCount;
	//private Integer ticketCount;
	private Integer sprintsCount;
}

