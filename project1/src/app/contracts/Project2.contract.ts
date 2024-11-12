import { Employee } from "./Employee.contract";
import { Employee2 } from "./Employee2.contract";
import { Sprint } from "./Sprint.contract";
import { Sprint2 } from "./Sprint2.contract";
import { Ticket } from "./Ticket.contract";

export interface Project2{
     projectId:number;
	projectName:string;
	projectDescription:string;
	softDelete:boolean;  
	sprints:Sprint2[];
	employees:Employee2[];
}