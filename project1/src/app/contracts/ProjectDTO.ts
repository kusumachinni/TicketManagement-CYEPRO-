export interface ProjectDTO{
    projectId:number;
    projectName:string;
	projectDescription:string;
	projectSoftDelete:boolean; 
    sprints:any[];
	employees:any[];
	tickets:any[];
}