import { Ticket } from "./Ticket.contract";

export interface Sprint{
     sprintId:number;
	 sprintName:string;
	 sprintGoal:string;
	 sprintSDate:Date;
	sprintEDate:Date;
	tickets:Ticket[];
	
}