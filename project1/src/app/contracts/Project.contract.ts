import { Employee } from "./Employee.contract";
import { Sprint } from "./Sprint.contract";
import { Ticket } from "./Ticket.contract";

export interface Project{
    projectId:number;
 projectName:string;
 projectDescription:string;
 projectCreationDate:Date;
 softDelete:boolean;
 employees:Employee[];
 sprints:Sprint[];
}