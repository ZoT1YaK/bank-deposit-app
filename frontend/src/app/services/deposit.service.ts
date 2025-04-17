import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DepositRequest } from '../models/deposit-request.model';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DepositService {

  // Construct the API URL dynamically using environment variables
  private apiUrl = `${environment.apiUrl}/deposits`;

  constructor(private http: HttpClient) {}

  /**
   * Fetch all deposit requests from the backend.
   * @returns An Observable of an array of DepositRequest objects.
   */
  getAll(): Observable<DepositRequest[]> {
    return this.http.get<DepositRequest[]>(this.apiUrl);
  }

  /**
   * Send a new deposit request to the backend.
   * @param deposit - The deposit data to be submitted.
   * @returns An Observable of the created DepositRequest.
   */
  create(deposit: DepositRequest): Observable<DepositRequest> {
    return this.http.post<DepositRequest>(this.apiUrl, deposit);
  }
}
