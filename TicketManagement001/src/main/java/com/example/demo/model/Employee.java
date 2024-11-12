package com.example.demo.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer employeeId;
	@NonNull
	private String employeeName;
	@NonNull
	private String employeeDesignation;
	@NonNull
	private String employeePassword;
	@NonNull
	private Long employeePhoneno;
	@NonNull
	private String employeeEmail;
	@NonNull
	private Boolean isEmployeeAvailable=true;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Ticket> tickets;


	public void addTicket(Ticket t) {tickets.add(t);}
	public void removeTicket(Ticket t) {tickets.remove(t);}
}
