import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

// interfaces (DTO)
export interface EmeraldAccount {
  id: number;
  funds: number;
}

export interface Campaign {
  id?: number;
  name: string;
  keywords: string;
  bidAmount: number;
  campaignFund: number;
  status: boolean;
  town: string;
  radius: number;
}

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }

  getAccount(): Observable<EmeraldAccount> {
    return this.http.get<EmeraldAccount>(`${this.baseUrl}/account`);
  }

  getCampaigns(): Observable<Campaign[]> {
    return this.http.get<Campaign[]>(`${this.baseUrl}/campaigns`);
  }

  createCampaign(campaign: Campaign): Observable<Campaign> {
    return this.http.post<Campaign>(`${this.baseUrl}/campaigns`, campaign);
  }

  deleteCampaign(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/campaigns/${id}`);
  }

  updateCampaign(id: number, campaign: Campaign): Observable<Campaign> {
    return this.http.put<Campaign>(`${this.baseUrl}/campaigns/${id}`, campaign);
  }
}
