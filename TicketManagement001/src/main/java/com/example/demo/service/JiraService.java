package com.example.demo.service;


import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.ProjectDTO;
import com.example.demo.dto.TicketDto;
import com.example.demo.model.Employee;
import com.example.demo.model.Project;
import com.example.demo.model.Sprint;
import com.example.demo.model.Ticket;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.OrganisationRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.SprintRepository;
import com.example.demo.repository.TicketRepository;


@Service
public class JiraService {
	@Autowired
	private DtosService dtos;
	@Autowired
	private OrganisationRepository orgrepo;
	@Autowired
	private ProjectRepository projectrepo;
	@Autowired
	private SprintRepository sprintrepo;
	@Autowired
	private EmployeeRepository employeerepo;
	@Autowired
	private TicketRepository ticketrepo;
	
	//Get Organisation Login
//	public OrganisationDTO OrganisationLogin(String orgname,String orgPword) {
//		Organisation organisation = orgrepo.findAll().get(0);
//		if(organisation.getOrganisationName().equals(orgname)) {
//			if(organisation.getOrganisationPassword().equals(orgPword)) {return dtos.convertToOrganisationDto(organisation);}
//			else {return null;}
//		}
//		else {return null;}
//	}
	
//	//Create Project With Sprint
//	public ProjectDTO createProject(ProjectDTO pdto) {
//		Organisation organisation = orgrepo.findAll().get(0);
//		Project toProjectEntity = dtos.convertToProjectEntity(pdto);
//		organisation.addProject(toProjectEntity);
//		orgrepo.save(organisation);
//		return pdto;
//	}
//	
	//Get All Project
	public List<ProjectDTO> getAllProject() {
		return projectrepo.findAll().stream().map(data->dtos.convertToProjectDto(data)).collect(Collectors.toList());
	}
//	
//	//Create Employee
//	public EmployeeDTO createEmployee(EmployeeDTO edto) {
//		Employee toEmployeeEntity = dtos.convertToEmployeeEntity(edto);
//		Employee employee = employeerepo.save(toEmployeeEntity);
//		return dtos.convertToEmployeeDto(employee);
//	}
	
	//Get All Employee
	public List<EmployeeDTO> getAllEmployee(){
		return employeerepo.findAll().stream().map(data->dtos.convertToEmployeeDto(data)).collect(Collectors.toList());
	}
//	
//	//Assign Employee To Project
//	public ProjectDTO AssignEmployeeToProject(Integer pid,List<Integer> eid) {
//		Project project = projectrepo.findById(pid).get();
//		List<Employee> employees = employeerepo.findAllById(eid);
//		for(Employee e:employees) {e.setIsEmployeeAvailable(false);}
//		project.setEmployees(employees);
//		Project p=projectrepo.save(project);
//		return dtos.convertToProjectDto(p);
//	}
	
//	//Delete Project 
//	public Boolean DeleteProject(Integer pid){
//		Project project = projectrepo.findById(pid).get();
//		List<Employee> all = employeerepo.findAll();
//		for(Employee e:all) {
//			if(project.getEmployees().contains(e)) {
//				return false;
//				}
//		}
//		project.setSoftDelete(false);
//		return true;
//	}
//	
//	//Delete Employee
//	public Boolean DeleteEmployee(Integer eid) {
//		Employee employee = employeerepo.findById(eid).get();
//		if(employee.getIsEmployeeAvailable()) {
//			employeerepo.delete(employee); 
//			return true;
//		}
//		else {
//			return false;
//		}
//	}
	
	//Update Project and Sprint by adding sprints to a project
	public ProjectDTO UpdateProject(ProjectDTO pdto) {
		Project convertToProjectEntity = dtos.convertToProjectEntity(pdto);
		Project save = projectrepo.save(convertToProjectEntity);
		return dtos.convertToProjectDto(save);
	}
	
//	//Employee Login
//	public EmployeeDTO employeeLogin(String ename,String epword) {
//		List<Employee> all = employeerepo.findAll();
//		
//		for(Employee e:all) {
//			if(e.getEmployeeName().equals(ename)) {
//				if(e.getEmployeePassword().equals(epword)) {
//					return dtos.convertToEmployeeDto(e);
//				}
//			}
//		}
//		return null;
//	}
	
	//Create Ticket
	public TicketDto createTicket(Integer pid,Integer sid,Integer eid,TicketDto tdto) {
		Project project = projectrepo.findById(pid).get();
		Sprint sprint = sprintrepo.findById(sid).get();
		Employee employee = employeerepo.findById(eid).get();
		Ticket ticketEntity = dtos.convertToTicketEntity(tdto);
		ticketEntity.setTicketAssignFrom(employee.getEmployeeName());
		ticketEntity.setTicketProjectName(project.getProjectName());
		ticketEntity.setTicketStatus("ToDO");
		 sprint.AddTicket(ticketEntity);
		 sprintrepo.save(sprint);
		return tdto;
	}
	
	//Show Ticket On basis Of Project
	public List<TicketDto> showAllTicketBasedOnProject(Integer pid){
		ArrayList<Ticket> allTicket=new ArrayList<Ticket>();
		Project project = projectrepo.findById(pid).get();
		for(Sprint sp:project.getSprints()){
			allTicket.addAll(sp.getTickets());
		}
		return allTicket.stream().map(data->dtos.convertToTicketDto(data)).collect(Collectors.toList());
	}
	
	//Show Ticket On basis of Sprint
	public List<TicketDto> showTicketBySprintInEachProject(Integer pid,Integer sid){
		Sprint spr=null;
		Project project = projectrepo.findById(pid).get();
		for(Sprint s:project.getSprints()) {
			if(s.getSprintId()==sid) {
				spr=s;break;// is this take multiple tickets or single ticket 
			}
		}
		if(spr!=null)
		{
			return spr.getTickets().stream().map(data->dtos.convertToTicketDto(data)).collect(Collectors.toList());
		}
		else 
		{
			return null;
		}
	}
	
	//Show ticket On Basis of EmployeeName
	public List<TicketDto> showTicketByEmployeeName(Integer eid){
		Employee employee = employeerepo.findById(eid).get();
		return employee.getTickets().stream().map(data->dtos.convertToTicketDto(data)).collect(Collectors.toList());
	}
	
	//Assign Ticket to Employee
	public TicketDto assignTicketToEmployee(Integer eid,Integer tid,MultipartFile image) throws IOException {
		Employee employee = employeerepo.findById(eid).get();
		 byte[] imageBytes = image.getBytes();
		Ticket ticket = ticketrepo.findById(tid).get();
		ticket.setImageData(imageBytes);
		ticket.setImageType(image.getContentType());
		ticket.setTicketAssignTo(employee.getEmployeeName());
		ticket.setTicketStatus("InProgress");
		ticket.setIsTicketAssigned(false);
		employee.addTicket(ticket);
		employeerepo.save(employee);
		return dtos.convertToTicketDto(ticket);
	}
	
	//get image of ticket	
	public Ticket getTicketImage(Integer ticketId) {
			 return ticketrepo.findById(ticketId)
			            .orElseThrow(() -> new RuntimeException("Ticket not found with id " + ticketId));
	    }
	//Change status to done
	public TicketDto changeStatusTicket(Integer tid) {
		 Ticket ticket = ticketrepo.findById(tid).get();
		 ticket.setTicketStatus("Complete");
		 return dtos.convertToTicketDto(ticketrepo.save(ticket));
	}
	
}
