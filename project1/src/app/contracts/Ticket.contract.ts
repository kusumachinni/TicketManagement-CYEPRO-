export interface Ticket{
	ticketId:number;
	
	ticketName:string;
   
	ticketDescription:string;
  ticketPriority:string;
   
	ticketAssignTo:string;
  ticketAssignFrom:string;
  ticketProjectName:string;
  ticketStatus:string;
   
	ticketCreatedDate:Date;
   
	ticketEndDate:Date;
   
	isTicketAssign:boolean;
}