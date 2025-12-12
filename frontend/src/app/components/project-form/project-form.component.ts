import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ProjectService } from '../../services/project.service';
import { ProjectRequest } from '../../models/project.model';

@Component({
  selector: 'app-project-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './project-form.component.html',
  styleUrl: './project-form.component.css'
})
export class ProjectFormComponent {
  projectData: ProjectRequest = {
    title: '',
    description: '',
    fundingGoal: 0
  };
  errorMessage = '';

  constructor(
    private projectService: ProjectService,
    private router: Router
  ) {}

  onSubmit(): void {
    this.errorMessage = '';
    
    this.projectService.createProject(this.projectData).subscribe({
      next: (project) => {
        alert('Project created successfully!');
        this.router.navigate(['/projects', project.id]);
      },
      error: (err) => {
        this.errorMessage = 'Failed to create project. Please try again.';
        console.error('Create project error:', err);
      }
    });
  }
}
