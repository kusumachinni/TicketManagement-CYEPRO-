import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from '../contracts/Employee.contract';

@Component({
  selector: 'app-employee-home',
  templateUrl: './employee-home.component.html',
  styleUrls: ['./employee-home.component.css']
})
export class EmployeeHomeComponent implements OnInit {
  sidebarVisible: boolean = false;
  organisationName:string='';
  employee!: Employee ;
  employeeName:string='' ;
  employeeDesignation:string='';
  employeeId:number=0;
  employeeEmail:string='';
  
  constructor(private router:Router){}
  ngOnInit(): void {
    this.employeeName=sessionStorage.getItem('empName') || '';
    this.organisationName=sessionStorage.getItem('orgName')||'';
    this.employeeDesignation=sessionStorage.getItem('empDeg')||'';
    
    this.GoToShowProject()
  }
  openSideBar(){
    this.sidebarVisible=!this.sidebarVisible;
  }
  GoToShowProject(){
    console.log("hi");
    this.router.navigate(['EmployeeHome/showproject']);
    
  }
  GoToManageTicket(){
    this.router.navigate(['EmployeeHome/showTicketdetails']);
    this.sidebarVisible=false;
  }
  GoToEmployeeDetails(){
    this.sidebarVisible=false;
  this.router.navigate(['EmployeeHome/showEmployeedetails']);
  
  }
  GoToLogout(){
    this.router.navigate(['']);
    this.sidebarVisible=false;
  }
  GoToHome(){
    this.router.navigate(['EmployeeHome/showproject']);
    this.sidebarVisible=false;
  }

}
 