package com.example.demo.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.ProjectDTO;
import com.example.demo.dto.TicketDto;
import com.example.demo.model.Employee;
import com.example.demo.model.Project;
import com.example.demo.model.Sprint;
import com.example.demo.model.Ticket;
import com.example.demo.repository.IEmployeeRepo;
import com.example.demo.repository.IOrganisationRepo;
import com.example.demo.repository.IProjectRepo;
import com.example.demo.repository.ISprintRepo;
import com.example.demo.repository.ITicketRepo;

import jakarta.servlet.http.HttpSession;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
@Autowired
private IOrganisationRepo oRepo;
@Autowired
private IEmployeeRepo eRepo;
@Autowired
private ModelMapper modelMapper;
@Autowired
private ITicketRepo tRepo;
@Autowired
private IProjectRepo pRepo;
@Autowired
private ISprintRepo sRepo;
@Autowired
private HttpSession session;

public EmployeeDTO convertTodto(Employee e)
{
	EmployeeDTO dto=modelMapper.map(e,EmployeeDTO.class);
	return dto;
}

public Ticket convertToTicketEntity(TicketDto dto)
{
	Ticket ticket=modelMapper.map(dto,Ticket.class);
	return ticket;
}

public TicketDto convertToTicketDto(Ticket t)
{
	TicketDto dto=modelMapper.map(t,TicketDto.class);
	return dto;
}

public ProjectDTO convertToProjectDto(Project p)
{
	ProjectDTO dto=modelMapper.map(p,ProjectDTO.class);
	return dto;
}

	@Override
	public EmployeeDTO login(String name, String password) {
		session.setAttribute("name",name);
		session.setAttribute("password",password);
		// TODO Auto-generated method stub
		List<Employee> all = eRepo.findAll();
		EmployeeDTO dto=null;
		for(Employee e:all) {
			if(e.getEmployeeName().equals(name)&&
			e.getEmployeePassword().equals(password)) {
			dto=convertTodto(e);
			}
			
		}
		return dto;
	}

	@Override
	public TicketDto createTicket(int projectId, int sprintId,int EmployeeId,TicketDto dto) {
		// TODO Auto-generated method stub
		Project byId = pRepo.findById(projectId).get();
		Sprint sprint= sRepo.findById(sprintId).get();
		Employee e=eRepo.findById(EmployeeId).get();
		Ticket t=convertToTicketEntity(dto);
		sprint.getTickets().add(t);
		byId.getSprints().add(sprint);
	    pRepo.save(byId);
		return dto;
	}

	@Override
	public List<TicketDto> showTickets(int projectId) {
//		// TODO Auto-generated method stub
//		
//		Project byId = pRepo.findById(projectId).orElse(null);
//        if (byId != null) {
//            return byId.getTickets().stream()
//                       .map(ticket -> modelMapper.map(ticket, TicketDto.class))
//                       .collect(Collectors.toList());
//        }
        return null;
	}

	@Override
	public List<TicketDto> showTicketsBySprint(int projectId) {
		// TODO Auto-generated method stub
		 Project byId = pRepo.findById(projectId).orElse(null);
	        if (byId == null) {
	            return Collections.emptyList();
	        }
	        
	        List<Sprint> sprints = byId.getSprints();
	        return sprints.stream()
	                .flatMap(sprint -> sprint.getTickets().stream())
	                .map(ticket -> modelMapper.map(ticket, TicketDto.class))
	                .collect(Collectors.toList());
	}

	@Override
	public List<TicketDto> showTicketByEmployeeName(int EmployeeId) {
		// TODO Auto-generated method stub
		Employee byId = eRepo.findById(EmployeeId).orElse(null);
        if (byId != null) {
            return byId.getTickets().stream()
                       .map(ticket -> modelMapper.map(ticket, TicketDto.class))
                       .collect(Collectors.toList());
        }
        return Collections.emptyList();
	}

	@Override
	public TicketDto assignTicketToEmployee(Integer eid, Integer tid) {
		// TODO Auto-generated method stub
		Employee employee = eRepo.findById(eid).get();
		Ticket ticket = tRepo.findById(tid).get();
		
		ticket.setIsTicketAssigned(false);
		employee.getTickets().add(ticket);

		eRepo.save(employee);
		return convertToTicketDto(ticket);

	}
	
	public List<ProjectDTO> getAllProject(){
		return pRepo.findAll().stream().map(org->convertToProjectDto(org)).collect(Collectors.toList());
	}
	
//	public EmployeeDto showEmployeeDetails() {
//		String Employeename=(String)session.getAttribute("name");
//		List<Employee> all = eRepo.findAll();
//		for(Employee e:all) {
//			if(e.getEmployeeName().equals(Employeename))
//				return convertTodto(e);
//		}
//		return null;
//	}
//	
//	public List<TicketDto> manageTicket(){
//		String Employeename=(String)session.getAttribute("name");
//		List<Project> all = pRepo.findAll();
//		for(Project p:all) {
//			List<Employee> employees = p.getEmployees();
//			for(Employee e:employees) {
//				if(e.getEmployeeName().equals(Employeename)) {
//					List<Ticket> tickets = e.getTickets();
//					return tickets.stream()
//	                        .map(this::convertToTicketDto)	
//	                        .collect(Collectors.toList());
//				}
//			}
//		}
//		return null;
//	}
	
	public Project createProject(Project pdto) {
		
		Project save = pRepo.save(pdto);
		return save;
	}
	
	public Employee createEmployee(Employee edto) {
		
		Employee save = eRepo.save(edto);
		return save;
	}

	@Override
	public String deleteTicket(int id) {
		// TODO Auto-generated method stub
		Ticket t = tRepo.findById(id).get();
		int c=0;
		List<Sprint> sprints = sRepo.findAll();
		List<Employee> employees = eRepo.findAll();
		if(t.getTicketStatus().equalsIgnoreCase("complete")) {
			t.setSoftDelete(false);
			tRepo.save(t);
		for(Sprint s:sprints ) {
			if(s.getTickets().contains(t)) {
				s.getTickets().remove(t);
				c++;
				sRepo.save(s);
		}
		}
		for(Employee e:employees) {
			if(e.getTickets().contains(t)) {
				e.getTickets().remove(t);
				c++;
				eRepo.save(e);
		}	
	}
}
		if(c>0)return "deleted";
		return " ticket could not be deletd";
}
}

