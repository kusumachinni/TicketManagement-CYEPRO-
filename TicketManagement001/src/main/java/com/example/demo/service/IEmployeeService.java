package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.ProjectDTO;
import com.example.demo.dto.TicketDto;
import com.example.demo.model.Employee;
import com.example.demo.model.Project;


public interface IEmployeeService {

public EmployeeDTO login(String name,String password);
public TicketDto createTicket(int projectId,int sprintId,int EmployeeId,TicketDto dto);
public List<TicketDto> showTickets(int projectId);
public List<TicketDto> showTicketsBySprint(int projectId);
public List<TicketDto> showTicketByEmployeeName(int EmployeeId);
public TicketDto assignTicketToEmployee(Integer eid,Integer tid);
public List<ProjectDTO> getAllProject();
public Project createProject(Project pdto);
public Employee createEmployee(Employee edto);
public String deleteTicket(int id);
}
