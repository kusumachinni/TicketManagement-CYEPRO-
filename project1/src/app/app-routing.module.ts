import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OrgProfileComponent } from './org-profile/org-profile.component';
import { ProjectComponent } from './project/project.component';
import { CreateEmployeeComponent } from './create-employee/create-employee.component';
import { HomeComponent } from './home/home.component';
import { EmployeeHomeComponent } from './employee-home/employee-home.component';
import { ShowprojectsComponent } from './showprojects/showprojects.component';
import { ShowcomponentComponent } from './showcomponent/showcomponent.component';
import { ShowticketComponent } from './showticket/showticket.component';
import { AllprojectsComponent } from './allprojects/allprojects.component';
import { TicketHomeComponent } from './ticket-home/ticket-home.component';
import { AllEmpComponent } from './all-emp/all-emp.component';

const routes: Routes = [
  {path:'',component:HomeComponent},
  {path:'org',component:OrgProfileComponent},
  {path:'create-project',component:ProjectComponent},
  {path:'show-all-employees',component:AllEmpComponent},
  {path:'show-all-projects',component:AllprojectsComponent},
  {path:'create-employee',component:CreateEmployeeComponent},
  {path:'EmployeeHome',component:EmployeeHomeComponent,children:[
    {path:'showproject',component:ShowprojectsComponent},
    {path:'showEmployeedetails',component:ShowcomponentComponent},
    {path:'showTicketdetails',component:ShowticketComponent}
  ]},
  {path:'TicketHome',component:TicketHomeComponent} 
];

@NgModule({
  declarations:[],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }