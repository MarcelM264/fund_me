import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ProjectService } from '../../services/project.service';
import { FundingService } from '../../services/funding.service';
import { AuthService } from '../../services/auth.service';
import { Project } from '../../models/project.model';
import { Funding, FundingRequest } from '../../models/funding.model';

@Component({
  selector: 'app-project-detail',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './project-detail.component.html',
  styleUrl: './project-detail.component.css'
})
export class ProjectDetailComponent implements OnInit {
  project?: Project;
  fundings: Funding[] = [];
  loading = true;
  isLoggedIn = false;
  
  fundingRequest: FundingRequest = {
    amount: 0,
    message: ''
  };

  constructor(
    private route: ActivatedRoute,
    private projectService: ProjectService,
    private fundingService: FundingService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isLoggedIn();
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.loadProject(id);
      this.loadFundings(id);
    }
  }

  loadProject(id: number): void {
    this.projectService.getProjectById(id).subscribe({
      next: (data) => {
        this.project = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error loading project:', err);
        this.loading = false;
      }
    });
  }

  loadFundings(projectId: number): void {
    this.fundingService.getFundingsByProject(projectId).subscribe({
      next: (data) => {
        this.fundings = data;
      },
      error: (err) => {
        console.error('Error loading fundings:', err);
      }
    });
  }

  submitFunding(): void {
    if (!this.project?.id || !this.isLoggedIn) {
      return;
    }

    this.fundingService.createFunding(this.project.id, this.fundingRequest).subscribe({
      next: (data) => {
        alert('Thank you for your support!');
        this.loadProject(this.project!.id!);
        this.loadFundings(this.project!.id!);
        this.fundingRequest = { amount: 0, message: '' };
      },
      error: (err) => {
        alert('Error submitting funding. Please try again.');
        console.error('Funding error:', err);
      }
    });
  }

  getFundingPercentage(): number {
    if (!this.project?.currentFunding || !this.project.fundingGoal) {
      return 0;
    }
    return Math.min((this.project.currentFunding / this.project.fundingGoal) * 100, 100);
  }
}
