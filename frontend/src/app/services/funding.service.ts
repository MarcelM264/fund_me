import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Funding, FundingRequest } from '../models/funding.model';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class FundingService {
  private apiUrl = 'http://localhost:8080/api/fundings';

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) { }

  private getHeaders(): HttpHeaders {
    const token = this.authService.getToken();
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }

  getFundingsByProject(projectId: number): Observable<Funding[]> {
    return this.http.get<Funding[]>(`${this.apiUrl}/project/${projectId}`);
  }

  getFundingsBySupporter(supporterId: number): Observable<Funding[]> {
    return this.http.get<Funding[]>(`${this.apiUrl}/supporter/${supporterId}`);
  }

  createFunding(projectId: number, funding: FundingRequest): Observable<Funding> {
    return this.http.post<Funding>(`${this.apiUrl}/project/${projectId}`, funding, { headers: this.getHeaders() });
  }
}
