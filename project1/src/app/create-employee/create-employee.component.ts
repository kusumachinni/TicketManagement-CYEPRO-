import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ProjectService } from '../service/project.service';

@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrls: ['./create-employee.component.css']
})
export class CreateEmployeeComponent implements OnInit {
  createEmployeeForm: FormGroup;
  

  constructor(private fb: FormBuilder,private location: Location,private service:ProjectService) {
    this.createEmployeeForm = this.fb.group({
      employeeName: ['', Validators.required],
      employeeDesignation: ['', Validators.required],
      employeePassword: ['', [Validators.required, Validators.minLength(6)]],
      employeePhoneno: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
      employeeEmail: ['', [Validators.required, Validators.email]],
    });
  }

  ngOnInit(): void {
    // No need to initialize the form here anymore.
  }

  onSubmit(): void {
    if (this.createEmployeeForm.valid) {
      // Get the form data
      const formData = this.createEmployeeForm.value;


      this.goBack();
        
      // Call the service to send data to Spring Boot
      this.service.createEmployee(formData).subscribe(
        (response) => {
          console.log('Employee Created:', response);
         
          alert('Employee Created Successfully!');
        },
        (error) => {
          console.log(formData);
        }
      );
    }  else {
      alert('Please fill all required fields correctly.');
    }
  }

  resetForm() {
    this.createEmployeeForm.reset();
  }

  goBack() {
    this.location.back(); // This takes the user back to the previous page
  }
}
