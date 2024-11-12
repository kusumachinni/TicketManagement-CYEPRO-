import { Ticket } from "./Ticket.contract";
import { Ticket2 } from "./Ticket2.contract";

export interface Employee2{


	employeeId:number;

	employeeName:string;

	employeeDesignation:string;

	employeePassword:string;

	employeePhoneno:number;

	employeeEmail:string;

	isEmployeeAvailable:boolean;
	
	tickets:Ticket2[];
}