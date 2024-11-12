import { Ticket } from "./Ticket.contract";
import { Ticket2 } from "./Ticket2.contract";

export interface Sprint2{
	sprintId:number;
	sprintName:string;
	sprintGoal:string;
	sprintStartDate:Date;
	sprintEndDate:Date;
	tickets:Ticket2[];
	
}