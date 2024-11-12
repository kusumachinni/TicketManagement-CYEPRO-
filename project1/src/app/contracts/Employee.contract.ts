import { Ticket } from "./Ticket.contract";

export interface Employee{
    employeeName:string;
    employeeDesignation:string;
    employeePassword:string;
    employeePhoneno:number;
    employeeEmail:string;
    isEmployeeAvailable:boolean;
    tickets:Ticket[];
	
}