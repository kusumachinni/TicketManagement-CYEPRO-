export interface Ticket2{
	ticketId:number;

	ticketName:string;

	ticketDescription:string;

	ticketIssueType:string;

	ticketPriority:string;

	ticketAssignTo:string;

	 ticketAssignFrom:string;

	 ticketProjectName:string;

	 isTicketAssigned:boolean;

	 ticketStatus:string;

	ticketCreatedDate:Date;

	ticketUpdatedDate:Date;

	softDelete:boolean;
}