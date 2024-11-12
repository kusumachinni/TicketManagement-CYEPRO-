package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sprint {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer sprintId;
	
	private String sprintName;
	
	private String sprintGoal;
	private Integer duration;
	@CreationTimestamp
	private LocalDate sprintStartDate;
	
	@UpdateTimestamp
	private LocalDate sprintEndDate;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Ticket> tickets;
	
	
	public void AddTicket(Ticket t) {
		tickets.add(t);
	}
	public void RemoveTicket(Ticket t) {
		tickets.remove(t);
	}
	
}
