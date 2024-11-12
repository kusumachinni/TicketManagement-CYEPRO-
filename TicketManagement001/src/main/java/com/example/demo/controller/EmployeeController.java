package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.ProjectDTO;
import com.example.demo.dto.TicketDto;
import com.example.demo.model.Employee;
import com.example.demo.model.Project;
import com.example.demo.service.IEmployeeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")

public class EmployeeController {
@Autowired
private IEmployeeService service;
@PostMapping("/login")
public ResponseEntity<EmployeeDTO> login(@RequestParam String name, @RequestParam String password) {
	EmployeeDTO employeeDto = service.login(name, password);
    if (employeeDto != null) {
        return ResponseEntity.ok(employeeDto);
    }
    return ResponseEntity.status(401).build(); // Unauthorized if login fails
}

// Endpoint for creating a ticket
@PostMapping("/createticket/{projectId}/{sprintId}/{employeeId}")
public ResponseEntity<TicketDto> createTicket(@PathVariable int projectId, 
                                              @PathVariable int sprintId,
                                              @PathVariable int employeeId,
                                              @RequestBody TicketDto ticketDto) {
    TicketDto createdTicket = service.createTicket(projectId, sprintId, employeeId, ticketDto);
    return ResponseEntity.ok(createdTicket);
}

// Endpoint to show tickets by project ID
@GetMapping("/showticket/{projectId}")
public ResponseEntity<List<TicketDto>> showTickets(@PathVariable int projectId) {
    List<TicketDto> tickets = service.showTickets(projectId);
    return ResponseEntity.ok(tickets);
}

// Endpoint to show tickets by sprint in a project
@GetMapping("/showTicketsBySprint/{projectId}")
public ResponseEntity<List<TicketDto>> showTicketsBySprint(@PathVariable int projectId) {
    List<TicketDto> tickets = service.showTicketsBySprint(projectId);
    return ResponseEntity.ok(tickets);
}

// Endpoint to show tickets by employee ID
@GetMapping("/showTicketByEmployeeName/{employeeId}")
public ResponseEntity<List<TicketDto>> showTicketByEmployeeName(@PathVariable int employeeId) {
    List<TicketDto> tickets = service.showTicketByEmployeeName(employeeId);
    return ResponseEntity.ok(tickets);
}
@PostMapping("/assignTicketToEmployee/{employeeId}/{ticketId}")
public ResponseEntity<TicketDto> assignTicketToEmployee(@PathVariable Integer employeeId, 
                                                        @PathVariable Integer ticketId) {
    TicketDto assignedTicket = service.assignTicketToEmployee(employeeId, ticketId);
    return ResponseEntity.ok(assignedTicket);
}

@GetMapping("/getAllprojects")
public List<ProjectDTO> getAllProjects() {
    return service.getAllProject();
}

@PostMapping("/projects")
public ResponseEntity<Project> createProject(@RequestBody Project project) {
    Project savedProject = service.createProject(project);
    return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
}

@PostMapping("/employees")
public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
    Employee savedEmployee = service.createEmployee(employee);
    return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
}

@DeleteMapping("/deleteTicket/{id}")
public ResponseEntity<String> deleteTicket(@PathVariable int id) {
    String result = service.deleteTicket(id);

    return new ResponseEntity<String>(result,HttpStatus.OK);
}
}
