import { Component } from '@angular/core';
import { ProjectService } from '../service/project.service';
import { Router } from '@angular/router';
import { EmployeeDTO1 } from '../contracts/EmployeeDTO1';

@Component({
  selector: 'app-all-emp',
  templateUrl: './all-emp.component.html',
  styleUrls: ['./all-emp.component.css']
})
export class AllEmpComponent {
  allEmployeeData:EmployeeDTO1[]=[
    {
      employeeId:0,
      employeeName:'',
      employeeDesignation:'',
      employeePassword:'',
      employeePhoneno:0,
      employeeEmail:'',
  
  }
    
  ];
constructor(private ser:ProjectService,private router:Router){}
ngOnInit(): void {
  this.showAllEmployees();
}
showAllEmployees():void {
  this.ser.getAllEmployees().subscribe(
    (response : EmployeeDTO1[]) => {
      this.allEmployeeData=response;
    },error =>{
      console.log("Some error got raised");
    } 
  );
  }
  navigateToOrgProfile(): void {
    this.router.navigate(['/org']);
  }
}



