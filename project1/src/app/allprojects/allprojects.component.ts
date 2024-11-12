import { Component } from '@angular/core';
import { Project1 } from '../contracts/Project1';
import { ProjectService } from '../service/project.service';
import { Router } from '@angular/router';
import { AssignEmployeeIdDTO } from '../contracts/AssignEmployeeIdDTO';
import { SprintDTO } from '../contracts/SprintDTO';
import { TicketDTO } from '../contracts/ticketDTO';
import { Employee1 } from '../contracts/Employee1.contract';

@Component({
  selector: 'app-allprojects',
  templateUrl: './allprojects.component.html',
  styleUrls: ['./allprojects.component.css']
})
export class AllprojectsComponent 
{
  projects: Project1[] = []; // Array to hold all projects
  availableEmployees: Employee1[] = [];
  selectedProjectId: number | null = null;
  isModalOpen = false;
  isEmployeeModalOpen: boolean = false;
  emp:AssignEmployeeIdDTO | any;
  assignedEmployees: Employee1[] = [];
  assignedSprints: SprintDTO[] = [];
  isSprintModalOpen = false; 
  assignedTickets: TicketDTO[] = [];  // Store assigned tickets
  isTicketModalOpen = false;  // To control modal visibility
  sprintTickets: TicketDTO[] = []; // Store tickets for a specific sprint
  weeks: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
  newSprint: SprintDTO | any;
  isSprintModal:boolean=false;
  constructor(private projectService: ProjectService, private router: Router) {}

  ngOnInit(): void {
    this.getAllProjects(); // Fetch projects on component initialization
  }

  // Fetch all projects
  getAllProjects(): void {
    this.projectService.getProjects().subscribe(
      (data: Project1[]) => {
        this.projects = data; // Assign fetched data to the projects array
      },
      (error) => {
        console.error('Error fetching projects:', error);
      }
    );
  }

  // Navigate back to the orgProfile component
  navigateToOrgProfile(): void {
    this.router.navigate(['/org']);
  }

  openAssignModal(projectId: number): void {
    this.selectedProjectId = projectId;
    this.projectService.getAvailableEmployees().subscribe((employees) => {
      this.availableEmployees = employees.map(employee => ({
        ...employee,
        selected: false  // Ensure `selected` is set initially
      }));
      this.isModalOpen = true;
    });
  }



  openAssignModalForSprint(projectId: number) {
    this.isModalOpen = true;
    this.selectedProjectId = projectId;
  }

  openAddSprintModal(projectId: number) {
    this.isSprintModal = true;
    this.selectedProjectId = projectId;
    this.newSprint = { sprintName: '', sprintGoal: '', sprintStartDate: new Date(), sprintEndDate: new Date(), duration: 0 };
  }

  closeModal(): void {
    this.isModalOpen = false;
    this.selectedProjectId = null;
    this.isEmployeeModalOpen= false;
    this.isSprintModalOpen = false;
    this.isTicketModalOpen = false; 
    this.isSprintModal = false;
  }

  applyAssignment(): void {
    const selectedEmployeeIds = this.availableEmployees
      .filter(employee => employee.selected)
      .map(employee => employee.employeeId);

    if (selectedEmployeeIds.length && this.selectedProjectId !== null) {
      const emp: AssignEmployeeIdDTO = {
        projectId: this.selectedProjectId,
        employeeIds: selectedEmployeeIds
      };
      console.log(emp);
      this.projectService.assignEmployeesToProject(emp).subscribe(
        () => {
          console.log('Employees assigned successfully.');
          this.closeModal();
        },
        error => console.error('Error assigning employees:', error)
      );
    }
  }

  getAssignedEmployees(projectId: number): void {
    console.log(projectId);
    this.projectService.getAssignedEmployees(projectId).subscribe(
      (employees: Employee1[]) => {
        this.assignedEmployees = employees;  // Store the assigned employees
        this.isEmployeeModalOpen = true;  // Open the modal to display the assigned employees
      },
      error => console.error('Error fetching assigned employees:', error)
    );
  }

  getAssignedSprints(projectId: number): void {
    this.projectService.getAssignedSprints(projectId).subscribe(
      (sprints: SprintDTO[]) => {
        this.assignedSprints = sprints;  // Store the fetched sprints
        this.isSprintModalOpen = true;  // Open the modal to display sprints
      },
      error => console.error('Error fetching assigned sprints:', error)
    );
  }

  getTicketsForSprint(sprintId: number): void {
    this.projectService.getTicketsBySprint(sprintId).subscribe(
      (tickets: TicketDTO[]) => {
        this.sprintTickets = tickets;
        this.isTicketModalOpen = true;
      },
      error => console.error('Error fetching tickets for sprint:', error)
    );
  }

  goBackToSprints(): void {
    this.isTicketModalOpen = false;
  }


  updateEndDate() {
    const startDate = new Date(this.newSprint.sprintStartDate);
    const durationWeeks = this.newSprint.duration;

    if (startDate && durationWeeks) {
      const endDate = new Date(startDate);
      endDate.setDate(startDate.getDate() + durationWeeks * 7);
      this.newSprint.sprintEndDate = endDate.toISOString().split('T')[0];
    }
  }

  createSprint() {
    if (this.newSprint.sprintName && this.newSprint.sprintGoal && this.newSprint.sprintStartDate && this.newSprint.duration) {
      if (this.selectedProjectId) {
        // Assign project ID to sprint object and send to backend
        console.log(this.newSprint);
        this.projectService.createSprint(this.selectedProjectId, this.newSprint).subscribe(
          response => {
            console.log('Sprint created:', response);
            this.closeModal(); // Close modal on success
            // Optionally refresh sprints list
          },
          error => {console.error('Error creating sprint:', error)
          this.closeModal();}
        );
      }
    }
    this.getAllProjects();
   console.log("all projects refreshed");
}
}
