import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { OrgProfileComponent } from './org-profile/org-profile.component';
import { ProjectComponent } from './project/project.component';
import { HttpClientModule } from '@angular/common/http';
import { CreateEmployeeComponent } from './create-employee/create-employee.component';
import { HomeComponent } from './home/home.component';
import { FormsModule } from '@angular/forms';
import { ShowticketComponent } from './showticket/showticket.component';
import { ShowprojectsComponent } from './showprojects/showprojects.component';
import { ShowcomponentComponent } from './showcomponent/showcomponent.component';
import { EmployeeHomeComponent } from './employee-home/employee-home.component';
import { SidebarModule } from 'primeng/sidebar';
import { AvatarModule } from 'primeng/avatar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import{ChartModule } from 'primeng/chart';
import { AllprojectsComponent } from './allprojects/allprojects.component';
import { TicketHomeComponent } from './ticket-home/ticket-home.component';
import { AllEmpComponent } from './all-emp/all-emp.component';


@NgModule({
  declarations: [
    AppComponent,
    OrgProfileComponent,
    ProjectComponent,
    CreateEmployeeComponent,
    HomeComponent,ShowticketComponent,
    ShowprojectsComponent,
    ShowcomponentComponent,
    EmployeeHomeComponent,
    AllprojectsComponent,
    TicketHomeComponent,
    AllEmpComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    SidebarModule,
    AvatarModule,
    BrowserAnimationsModule,
    ChartModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
