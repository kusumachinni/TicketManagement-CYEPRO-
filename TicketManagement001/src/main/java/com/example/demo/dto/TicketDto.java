package com.example.demo.dto;
import java.time.LocalDate;

import lombok.Data;

@Data
public class TicketDto {
	private Integer ticketId;
	private String ticketName;
	private String ticketDescription;
	private String ticketIssueType;
	private String ticketPriority;
	private String ticketAssignTo;
	private String ticketAssignFrom;
	private String ticketProjectName;
	private String ticketStatus;
	private LocalDate ticketCreatedDate;
	private LocalDate ticketEndDate;
}
