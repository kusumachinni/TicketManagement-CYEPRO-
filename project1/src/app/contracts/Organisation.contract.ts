import { Project } from "./Project.contract";

export interface Organisation{
    orgId:number;
	 orgName:string;
	 orgPword:string;
	projects:Project[];
}