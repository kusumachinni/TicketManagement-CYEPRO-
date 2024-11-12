package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AssignEmployeeIdDTO;
import com.example.demo.dto.AssignSprintIdDTO;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.EmployeeLoginDto;
import com.example.demo.dto.OrganisationLoginDto;
import com.example.demo.dto.ProjectCountDTO;
import com.example.demo.dto.ProjectDTO;
import com.example.demo.dto.SprintDTO;
import com.example.demo.dto.TicketDto;
import com.example.demo.model.Employee;
import com.example.demo.model.Project;
import com.example.demo.model.Sprint;
import com.example.demo.repository.IEmployeeRepo;
import com.example.demo.service.OrganisationServiceImp;

@RestController
@RequestMapping("organisation")
@CrossOrigin(origins="http://localhost:4200")
public class OrganisationController {

	@Autowired
	private OrganisationServiceImp ser;
	
	@Autowired
	IEmployeeRepo eRepo;
	
	
	
	
	//organisation login path
	@PostMapping("/ologin")
	public ResponseEntity<OrganisationLoginDto> orgLoginForm (@RequestParam ("organisationName")String organisationName,@RequestParam("organisationPassword") String organisationPassword)
	{
		OrganisationLoginDto loginDto=new OrganisationLoginDto();
		if(ser.organisationLogin(organisationName, organisationPassword)==true)
		{
			loginDto.setOrganisationName(organisationName);
			loginDto.setOrganisationPassword(organisationPassword);
			
			return new ResponseEntity<OrganisationLoginDto>(loginDto,HttpStatus.ACCEPTED);
		}
		else
		{
			return new ResponseEntity<OrganisationLoginDto>(loginDto,HttpStatus.BAD_REQUEST);
		}
	}
	
	//employee login path
	@PostMapping("/elogin")
	public ResponseEntity<EmployeeLoginDto> empLoginForm (@RequestParam("employeeName")String employeeName, @RequestParam("employeePassword")String employeePassword)
	{
		EmployeeLoginDto loginDto = new EmployeeLoginDto();
		if(ser.employeeLogin(employeeName, employeePassword)!=null)
		{
			 loginDto = ser.employeeLogin(employeeName, employeePassword);
			return new ResponseEntity<EmployeeLoginDto>(loginDto,HttpStatus.ACCEPTED);
		}
		else
		{
			System.out.println("i am in control");
			return new ResponseEntity<EmployeeLoginDto>(loginDto,HttpStatus.BAD_REQUEST);
		}
	}
	
	//kusuma
	@PostMapping("/ologin/createProject")
	public String registerProject(@RequestBody ProjectDTO dto)
	{
		return ser.registerProject(dto);
	}
	
	@PostMapping("/ologin/createEmployee")
	public ResponseEntity<String> registerEmployee(@RequestBody EmployeeDTO dto)
	{
		 
		 return new ResponseEntity<>(ser.registerEmployee(dto),HttpStatus.OK);
	}
	
	@PostMapping("/createTicket")
	public String registerTicket(@RequestBody TicketDto dto)
	{
		return ser.registerTicket(dto);
	}
	
	@GetMapping("/ologin/allProjects")
	public List<ProjectDTO> showAllProject()
	{
		return ser.showAllProjects();
	}
	
	@GetMapping("/ologin/allEmployees")
	public List<EmployeeDTO> showAllEmployee()
	{
		return ser.showAllEmployees();
	}
	
	@GetMapping("/ologin/availableEmployees")
	public List<Employee> AvaliableEmployees()
	{
		return ser.findByAvaliable();
	}
	
	@PostMapping("/ologin/assigned")
	public Project assignProject(@RequestBody AssignEmployeeIdDTO id)
	{
		return ser.assignProjectToEmployee(id.getProjectId(),id.getEmployeeIds());
	}
	
	
	@GetMapping("/ologin/forgotPassword")
	public ResponseEntity<String> sendMail(@RequestParam("employeeName")String employeeName,@RequestParam("employeeEmail")String employeeEmail)
	{	
		
		if(ser.sendPassword(employeeName)!=null)
		{
			ser.sendMailToEmployee(ser.sendPassword(employeeName), employeeEmail);
			return new ResponseEntity<String>("succeesful, found employee",HttpStatus.ACCEPTED);
		}
		else
		{
			return new ResponseEntity<String>("not found employee",HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping("/ologin/dto")
	public List<ProjectCountDTO> getAllCount()
	{
		return ser.getAllProjectsWithCount();
	}
	
	
//	@GetMapping("/getTickets/{projectId}")
//	public List<TicketDTO> getTickets(@PathVariable Integer projectId)
//	{
//		return service.getAssignedTickets(projectId);
//	}
	
	@GetMapping("/ologin/getSprints/{projectId}")
	public List<SprintDTO> getSprints(@PathVariable Integer projectId)
	{
		return ser.getAssignedSprints(projectId);
	}
	
	@GetMapping("/ologin/getTickets/{sprintId}")
	public List<TicketDto> getTickets(@PathVariable Integer sprintId)
	{
		return ser.getTicketsBySprints(sprintId);
	}
	
	@PostMapping("/ologin/tassigned")
	public Sprint assignProject(@RequestBody AssignSprintIdDTO id)
	{
		return ser.assignTicketToSprints(id.getSprintId(),id.getTicketIds());
	}
	
	@PostMapping("/ologin/addSprint/{projectId}")
	public String addSprint(@PathVariable Integer projectId,@RequestBody SprintDTO dto)
	{
		return ser.addSprintToProject(projectId, dto);
	}

	@GetMapping("/ologin/getEmployees/{projectId}")
	public List<EmployeeDTO> getEmployees(@PathVariable Integer projectId)
	{
		return ser.getAssignedEmployees(projectId);
	}
	
	
	///////////////////////////////////////
	@GetMapping("/getEmployeeObject/{id}")
	public EmployeeDTO employeeLogin(@PathVariable Integer id) {
		 Employee employee = eRepo.findById(id).get();
		 EmployeeDTO employeeDto = new EmployeeDTO();
		 BeanUtils.copyProperties(employee, employeeDto);
		 return employeeDto;
	}
	
	
}
