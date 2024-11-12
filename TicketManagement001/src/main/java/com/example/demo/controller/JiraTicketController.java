package com.example.demo.controller;



import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.ProjectDTO;
import com.example.demo.dto.TicketDto;
import com.example.demo.model.Ticket;
import com.example.demo.service.JiraService;


@RestController
@RequestMapping("Employee")
@CrossOrigin(origins = "http://localhost:4200")
public class JiraTicketController {
	@Autowired
	private JiraService js;
	
	//Organisation Login
//	@GetMapping("/Organisation/Login/{orgname}/{orgpword}")
//	public OrganisationDTO OrganisationLogin(@PathVariable String orgname,@PathVariable String orgpword) {
//		return js.OrganisationLogin(orgname, orgpword);
//	}
//	
	//Create Project With Sprint
//	@PostMapping("/Project/CreateProject")
//	public ProjectDTO createProject(@RequestBody ProjectDTO pdto) {
//		return js.createProject(pdto);
//	}
	
	//Get All Project 
	@GetMapping("/GetAllProjects")
	public List<ProjectDTO> getAllProject(){
		return js.getAllProject();
	}
	
	//Create Employee 
//	@PostMapping("/Employee/CreateEmployee")
//	public EmployeeDTO createEmployee(@RequestBody EmployeeDTO edto) {
//		return js.createEmployee(edto);
//	}
//	
	//Get All Employee
	@GetMapping("/GetAllProjects/Project/Employee/GetAllEmployee")
	public List<EmployeeDTO> getAllEmployee(){
		return js.getAllEmployee();
	}
	
//	//Assign Employee To Project
//	@PostMapping("/Employee/AssignEmployee/{pid}")
//	public ProjectDTO AssignEmployeeToProject(@PathVariable Integer pid,@RequestBody List<Integer> eid) {
//		return js.AssignEmployeeToProject(pid, eid);
//	}
	
//	//Delete Project 
//	@GetMapping("/Project/SoftDeleteProject/{pid}")
//	public Boolean SoftDeleteProject(@PathVariable Integer pid) {
//		return js.DeleteProject(pid);
//	}
	
//	//Delete Employee
//	@GetMapping("/Employee/DeleteEmployee/{eid}")
//	public Boolean DeleteEmployee(@PathVariable Integer eid) {
//		return js.DeleteEmployee(eid);
//	}
	
	//Update Project means added sprints to project
	@PostMapping("/Project/UpdateProject")
	public ProjectDTO UpdateProject(@RequestBody ProjectDTO pdto){
		return js.UpdateProject(pdto);
	}
	
	//Login Employee
//	@GetMapping("Employee/Login/{ename}/{epword}")
//	public EmployeeDTO employeeLogin(@PathVariable String ename,@PathVariable String epword) {
//		return js.employeeLogin(ename, epword);
//	}
	
	//Create Ticket
	@PostMapping("/Ticket/CreateTicket/{pid}/{sid}/{eid}")
	public TicketDto createTicket(@PathVariable Integer pid,@PathVariable Integer sid,@PathVariable Integer eid,@RequestBody TicketDto tdto) {
		return js.createTicket(pid, sid, eid, tdto);
	}
	
	//Show All Tickets Available in Project
	@GetMapping("/GetAllProjects/Project/ShowAllTicket/{pid}")
	public List<TicketDto> getTicketBasedOnProject(@PathVariable Integer pid){
		return js.showAllTicketBasedOnProject(pid);
	}
	
	//Show Ticket Based On sprint inEach Project
	@GetMapping("/Sprint/Ticket/{pid}/{sid}")
	public List<TicketDto> getAllTicketOnSprint(@PathVariable Integer pid,@PathVariable Integer sid){
		return js.showTicketBySprintInEachProject(pid,sid);
	}
	
	//Assign Ticket
	@PostMapping("/AssignTicket/{eid}/{tid}")
	public TicketDto assignTicketToEmployee(@PathVariable Integer eid,@PathVariable Integer tid,@RequestParam("image") MultipartFile image) throws IOException {
		return this.js.assignTicketToEmployee(eid, tid,image);
	}
	
	@GetMapping("/Ticket/ChangeStatus/{tid}")
	public TicketDto changeStatusTicket(@PathVariable Integer tid) {
		return js.changeStatusTicket(tid);
	}
	
	@GetMapping("/Ticket/Image/{ticketId}")
	public ResponseEntity<byte[]> getTicketImage(@PathVariable Integer ticketId) {
        Ticket ticket = js.getTicketImage(ticketId);

        if (ticket.getImageData() == null || ticket.getImageType() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(ticket.getImageType())); // Set dynamic Content-Type
        return new ResponseEntity<>(ticket.getImageData(), headers, HttpStatus.OK);
    }
}
