import { Component } from '@angular/core';
import { RequestsService } from '../service/request.service';
import { Router } from '@angular/router';
import { OrgLoginDTO } from '../contracts/OrgLoginDTO';
import { EmpLoginDTO } from '../contracts/EmpLoginDTO';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  //emp login
  employeeName:string='';
  employeePassword:string='';
  employeeDesignation:string='';
  design:string='';
  empPasswordVisible:boolean=false;
 empLoginMessage:string='';
 empSuccess:boolean=false;
  emp!:EmpLoginDTO;
  chooseEmp:boolean=false;
  isPassFormOpen:boolean=true;

  
  // org log in
    default:boolean=true;
   chooseOrg:boolean=false;
   organisationId:number=0;
   organisationName:string="";
   organisationPassword:string="";
   passwordVisible:boolean=false;
  loginMessage:string="";
  success:boolean=false;
  org  !:OrgLoginDTO ;

  eName:string='';
  eMail:string='';
  showForgotPasswordForm = false;
  message:string='';
  constructor(private ser:RequestsService,private router:Router ) {}
  
  togglePasswordVisibility(): void {
    this.passwordVisible = !this.passwordVisible;
  }
  ToggleButton(){
    this.default=(this.chooseEmp)?false:true;
 
   }
  toggleChooseOrg():void{
    this.chooseOrg=true;
    this.chooseEmp=false;
    this.ToggleButton();
 
    
  }
  toggleChooseEmp():void{
    this.chooseEmp=true;
    this.chooseOrg=false;
    this.ToggleButton();
 
  }
  
  onLogin(): void {
    this.ser.OrgLogin(this.organisationName, this.organisationPassword).subscribe(
      ( response :OrgLoginDTO)=> {
        this.org=response;
        sessionStorage.setItem('orgName',this.organisationName);
        sessionStorage.setItem('orgPassword',this.organisationPassword);
      
        console.log(sessionStorage.getItem('orgName'));

        this.loginMessage = 'Welcome to Profile ' + this.organisationName;
        this.success = true; 
        this.router.navigate(['/org']);
      },
      error => {
     
        this.loginMessage = 'Login failed. Please try again.';
        this.success = false; 
      }
    );
  }
 
  

  //emp login
  empTogglePasswordVisibility(): void {
    this.empPasswordVisible = !this.empPasswordVisible;
  }
  empOnLogin(): void {
    this.ser.EmpLogin(this.employeeName, this.employeePassword).subscribe(
      ( response :EmpLoginDTO)=> {
        this.emp=response;
        this.design = this.emp.employeeDesignation; 
     
        sessionStorage.setItem('empName',this.employeeName);
        sessionStorage.setItem('empPassword',this.employeePassword);
        this.empLoginMessage = 'Welcome to Profile ' + this.employeeName;
        this.empSuccess = true; 
        this.router.navigate(['/EmployeeHome']);
        sessionStorage.setItem('empDeg',this.emp.employeeDesignation);
        sessionStorage.setItem('empId',JSON.stringify(this.emp.employeeId));
        sessionStorage.setItem('empPh',JSON.stringify(this.emp.employeePhoneno));
        sessionStorage.setItem('empEmail',this.emp.employeeEmail);
     
      },
      error => {
     
        this.empLoginMessage = 'Login failed. Please try again.';
        this.empSuccess = false; 
      }
    );
  }

  forgotPasswordEvent():void{
    this.message = 'Please, check mail once';this.showForgotPasswordForm = false;
    this.ser.getPassword(this.eName,this.eMail).subscribe((response:string)=>{
      console.log("this is ",response);
      // this.message = 'Please, check mail once';
      // this.showForgotPasswordForm = false;
      console.log(" hi there"+this.message);
    },error=>{
      console.log("something found as error");
      console.log(error);
    }
  );
  }
  toggleForgotPasswordForm() {
    this.isPassFormOpen=false;
    this.showForgotPasswordForm = !this.showForgotPasswordForm;
    this.eName = '';
    this.eMail = '';
    this.message='';
  }

  changeFlag():void{
    this.isPassFormOpen=true;
  }
 
}