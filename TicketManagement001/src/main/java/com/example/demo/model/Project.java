package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Project 
{	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer projectId;
	private String projectName;
	private String projectDescription;
	@CreationTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate projectCreationDate;
	private Boolean softDelete=true; 
	@OneToMany( cascade = CascadeType.ALL)
    private List<Sprint> sprints;
	@OneToMany( cascade = CascadeType.ALL)
	private List<Employee> employees;
	
	
//	public void addSprint(Sprint s) {sprints.add(s);}
//	public void removeSprint(Sprint s) {sprints.remove(s);}
//	
//	public void addEmployee(Employee e) {employees.add(e);}
//	public void removeEmployee(Employee e) {employees.remove(e);}
//	
//	public void addTicket(Ticket t) {tickets.add(t);}
//	public void removeTicket(Ticket t) {tickets.remove(t);}

   }
