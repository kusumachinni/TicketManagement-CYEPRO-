import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-org-profile',
  templateUrl: './org-profile.component.html',
  styleUrls: ['./org-profile.component.css']
})
export class OrgProfileComponent 
{
  organizationName: string = '';
  isSideMenuOpen=false;
  constructor(private router: Router) {}

  ngOnInit(): void {
    // Retrieve the organization name from sessionStorage when the component is initialized
    this.organizationName = sessionStorage.getItem('orgName') || '';
  }

  navigate(path: string) {
    this.router.navigate([path]);
  }

// Function to toggle the side menu
 toggleSideMenu(): void {
 this.isSideMenuOpen=!this.isSideMenuOpen;

  // Check if the side menu element exists
  if (this.isSideMenuOpen) {
   console.log("side menu opened");
}
}

logout(): void {
  // Clear session storage or any authentication-related data
  sessionStorage.removeItem('orgName'); // Remove any session data if needed
  sessionStorage.removeItem('orgPassword');  // Example of removing authentication token

  // Optionally, redirect to the login page
  this.router.navigate(['']);  // Adjust the path as necessary

  // Close the side menu (optional)
  this.isSideMenuOpen = false;
}


  
}
