import { Project } from "./Project.contract";

export interface Organisation{
	organisationId:number;
	organisationName:string;
	organisationPassword:string;
	projects:Project[];
}