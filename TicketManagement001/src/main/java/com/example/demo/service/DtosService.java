package com.example.demo.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.OrganisationDto;
import com.example.demo.dto.ProjectDTO;
import com.example.demo.dto.SprintDTO;
import com.example.demo.dto.TicketDto;
import com.example.demo.model.Employee;
import com.example.demo.model.Organisation;
import com.example.demo.model.Project;
import com.example.demo.model.Sprint;
import com.example.demo.model.Ticket;


@Service
public class DtosService {
	@Autowired
	private ModelMapper modelMapper;
	
	// Convert EmployeeEntity to DTO
    public EmployeeDTO convertToEmployeeDto(Employee employee) {
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    // Convert EmployeeDTO to Entity
    public Employee convertToEmployeeEntity(EmployeeDTO employeeDTO) {
        return modelMapper.map(employeeDTO, Employee.class);
    }
    
    // Convert Org Entity to DTO
    public OrganisationDto convertToOrganisationDto(Organisation organisation) {
    	return modelMapper.map(organisation, OrganisationDto.class);
    }
 // Convert Org DTO to Entity
    public Organisation convertToOrganisationEntity(OrganisationDto organisationDTO) {
        return modelMapper.map(organisationDTO, Organisation.class);
    }
    
 // Convert project Entity to DTO
    public ProjectDTO convertToProjectDto(Project project) {
        return modelMapper.map(project, ProjectDTO.class);
    }

    // Convert project DTO to Entity
    public Project convertToProjectEntity(ProjectDTO projectDTO) {
        return modelMapper.map(projectDTO, Project.class);
    }
    
    //Convert Ticket Dto to Entity
    public TicketDto convertToTicketDto(Ticket ticket) {
        return modelMapper.map(ticket, TicketDto.class);
    }
    // Convert project DTO to Entity
    public Ticket convertToTicketEntity(TicketDto ticketdto) {
        return modelMapper.map(ticketdto, Ticket.class);
    }
    
  //Convert Sprint Dto to Entity
    public SprintDTO convertToSprintDto(Sprint sprint) {
        return modelMapper.map(sprint, SprintDTO.class);
    }
 // Convert sprint Entity to dto
    public Sprint convertToSPrintEntity(SprintDTO sprintdto) {
        return modelMapper.map(sprintdto, Sprint.class);
    }
}
