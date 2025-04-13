import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DepositRequest } from '../models/deposit-request.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DepositService {

  private apiUrl = '/api/deposits';

  constructor(private http: HttpClient) {}

  getAll(): Observable<DepositRequest[]> {
    return this.http.get<DepositRequest[]>(this.apiUrl);
  }

  create(deposit: DepositRequest): Observable<DepositRequest> {
    return this.http.post<DepositRequest>(this.apiUrl, deposit);
  }
}
