package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.EmployeeLoginDto;
import com.example.demo.dto.OrganisationLoginDto;
import com.example.demo.dto.ProjectCountDTO;
import com.example.demo.dto.ProjectDTO;
import com.example.demo.dto.SprintDTO;
import com.example.demo.dto.TicketDto;
import com.example.demo.model.Employee;
import com.example.demo.model.Organisation;
import com.example.demo.model.Project;
import com.example.demo.model.Sprint;
import com.example.demo.model.Ticket;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.IProjectRepo;
import com.example.demo.repository.ISprintRepo;
import com.example.demo.repository.ITicketRepo;
import com.example.demo.repository.OrganisationRepository;

@Service
public class OrganisationServiceImp {

	@Autowired
	private OrganisationRepository orepo;
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ITicketRepo ticketRepo;
	
	@Autowired
	private ISprintRepo sprintRepo;
	
	@Autowired
	private IProjectRepo projectRepo;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	//if forget password
	public String sendPassword(String employeeName)
	{
		return employeeRepo.findByName(employeeName).getEmployeePassword();
	}
	
	//mail sending method
	public void sendMailToEmployee(String password,String toMail)
	{
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("dedeepyasepuri@gmail.com");
		simpleMailMessage.setTo(toMail);
		simpleMailMessage.setText(password);
		simpleMailMessage.setSubject("Your Login Password");
		
		javaMailSender.send(simpleMailMessage);
	}

	//entity to dto (organisation login data)
	public OrganisationLoginDto entityToDto(Organisation orgData)
	{
		OrganisationLoginDto loginDto= modelMapper.map(orgData,OrganisationLoginDto.class);
		return loginDto;
	}
	
	//organisation login service
	public Boolean organisationLogin(String oraganisationName, String organisationPassword)
	{
		if(orepo.findByName(oraganisationName)!=null)
		{
			OrganisationLoginDto loginDto=entityToDto(orepo.findByName(oraganisationName));
			if(oraganisationName.equals(loginDto.getOrganisationName()) && organisationPassword.equals(loginDto.getOrganisationPassword()))
			{
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}
	
	
	//entity to dto (employee login data)
	public EmployeeLoginDto entityToDtoOfEmployee(Employee empData)
	{
		EmployeeLoginDto loginDto= modelMapper.map(empData,EmployeeLoginDto.class);
		return loginDto;
	}
	
	
	//employee login service
	public EmployeeLoginDto employeeLogin(String employeeName, String employeePassword)
	{
		if(employeeRepo.findByName(employeeName)!=null)
		{
			EmployeeLoginDto loginDto=entityToDtoOfEmployee(employeeRepo.findByName(employeeName));
			if(employeeName.equals(loginDto.getEmployeeName()) && employeePassword.equals(loginDto.getEmployeePassword()))
			{
			
				return loginDto;
			}
			else
				return null;
			
		}
		else
		{
			return null;
		}
	}
	///////////////////////////////////////////////////
	//creating the project
		public String registerProject(ProjectDTO dto)
		{
			Project p=new Project();
			BeanUtils.copyProperties(dto, p);
			Integer projectId = projectRepo.save(p).getProjectId();
			return "project is registered with Id"+projectId;
		}
		
		//creating the employee
		public String registerEmployee(EmployeeDTO dto)
		{
			Employee e=new Employee();
			BeanUtils.copyProperties(dto, e);
			Integer employeeId = employeeRepo.save(e).getEmployeeId();
			return "Employee is registered with id"+employeeId;
		}
		
		//creating the ticket
		public String registerTicket(TicketDto dto)
		{
			Ticket t=new Ticket();
			BeanUtils.copyProperties(dto, t);
			Integer ticketId = ticketRepo.save(t).getTicketId();
			return "Ticket is registered with id"+ticketId;
		}
		
		//show all the projects
		public List<ProjectDTO> showAllProjects()
		{
			List<Project> all=projectRepo.findAll();
			List<ProjectDTO> list=new ArrayList<>();
			for(Project each:all)
			{
				ProjectDTO dto=new ProjectDTO();
				BeanUtils.copyProperties(each, dto);
				list.add(dto);
			}
			return list;
		}
		
		//show all the employees
		public List<EmployeeDTO> showAllEmployees()
		{
			List<Employee> all=employeeRepo.findAll();
			List<EmployeeDTO> list=new ArrayList<>();
			for(Employee each:all)
			{
				EmployeeDTO dto=new EmployeeDTO();
				BeanUtils.copyProperties(each, dto);
				list.add(dto);
			}
			return list;
		}
		
		//list of available employees
		public List<Employee> findByAvaliable()
		{
			return employeeRepo.findByAvaliability();
		}
		
		//Assign the project to employees
		public Project assignProjectToEmployee(Integer projectId,List<Integer> ids)
		{
			//List<Employee> all=employeeRepo.findByAvaliability();
			Project project = projectRepo.findById(projectId).orElseThrow();
			
			List<Employee> currentEmp = project.getEmployees();
			List<Employee> newEmp = employeeRepo.findAllById(ids);
			for(Employee e:newEmp)
			{
				e.setIsEmployeeAvailable(false);
			}
			currentEmp.addAll(newEmp);
			project.setEmployees(currentEmp);;
			return projectRepo.save(project);
		}
		
		//count of all the employee,ticket and sprint
		public List<ProjectCountDTO> getAllProjectsWithCount()
		{
			List<Project> all = projectRepo.findAll();
			List<ProjectCountDTO> list=new ArrayList<>();
			for(Project each:all)
			{
				Integer employeeCount = projectRepo.countOfEmployeeWithProject(each.getProjectId());
				//Integer ticketCount = projectRepo.countOfTicketWithProject(each.getProjectId());
				Integer sprintCount = projectRepo.countOfSprintWithProject(each.getProjectId());
				ProjectCountDTO dto=new ProjectCountDTO(each.getProjectId(),each.getProjectName(),each.getProjectDescription(),employeeCount,sprintCount);
				list.add(dto);
			}
			return list;
		}
		
		//getting assigned employees..
		public List<EmployeeDTO> getAssignedEmployees(Integer projectId)
		{
			Project project = projectRepo.findById(projectId).orElseThrow();
			List<Employee> employees = project.getEmployees();
			List<EmployeeDTO> list=new ArrayList<>();
			for(Employee each:employees)
			{
				EmployeeDTO dto=new EmployeeDTO();
				BeanUtils.copyProperties(each, dto);
				list.add(dto);
			}
			return list;
		}
		
//		public List<TicketDTO> getAssignedTickets(Integer projectId)
//		{
//			Project project = projectRepo.findById(projectId).orElseThrow();
//			List<Ticket> tickets = project.getTickets();
//			List<TicketDTO> list=new ArrayList<>();
//			for(Ticket each:tickets)
//			{
//				TicketDTO dto=new TicketDTO();
//				BeanUtils.copyProperties(each, dto);
//				list.add(dto);
//			}
//			return list;
//		}
		
		//get assigned sprints
		public List<SprintDTO> getAssignedSprints(Integer projectId)
		{
			Project project = projectRepo.findById(projectId).orElseThrow();
			List<Sprint> sprints = project.getSprints();
			List<SprintDTO> list=new ArrayList<>();
			for(Sprint each:sprints)
			{
				SprintDTO dto=new SprintDTO();
				BeanUtils.copyProperties(each, dto);
				list.add(dto);
			}
			return list;
		}
		
		public List<TicketDto> getTicketsBySprints(Integer sprintId)
		{
			Sprint sprint = sprintRepo.findById(sprintId).orElseThrow();
			List<Ticket> tickets = sprint.getTickets();
			List<TicketDto> list=new ArrayList<>();
			for(Ticket each:tickets)
			{
				TicketDto dto=new TicketDto();
				BeanUtils.copyProperties(each, dto);
				list.add(dto);
			}
			return list;
		}
		
		public Sprint assignTicketToSprints(Integer sprintId,List<Integer> ids)
		{
			//List<Employee> all=employeeRepo.findByAvaliability();
			Sprint sprint = sprintRepo.findById(sprintId).orElseThrow();
			
			List<Ticket> currentTicket = sprint.getTickets();
			List<Ticket> newTicket = ticketRepo.findAllById(ids);
			currentTicket.addAll(newTicket);
			sprint.setTickets(currentTicket);;
			return sprintRepo.save(sprint);
		}
		
		public String addSprintToProject(Integer projectId, SprintDTO sprint) 
		{
	        Project project = projectRepo.findById(projectId).orElseThrow();
	        List<Sprint> oldSprint = project.getSprints();
	        Sprint s=new Sprint();
	        BeanUtils.copyProperties(sprint,s);
	        oldSprint.add(s);
	        Integer projectId2 = projectRepo.save(project).getProjectId();
	        return "sprint is added to project"+projectId2;
	        
	    }
		
}
