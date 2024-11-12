package com.example.demo.model;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer ticketId;
	
	private String ticketName;
	
	private String ticketDescription;

	private String ticketIssueType;

	private String ticketPriority;
	
	private String ticketAssignTo;

	private String ticketAssignFrom;

	private String ticketProjectName;

	private Boolean isTicketAssigned=true;


	private String ticketStatus;

	@CreationTimestamp
	private  LocalDate ticketCreatedDate;
	@UpdateTimestamp
	private  LocalDate ticketUpdatedDate;

	private Boolean softDelete=true;
	@Lob
	private byte[] imageData;

	private String imageType; 




	}
