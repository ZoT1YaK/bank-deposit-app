import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DepositService } from '../services/deposit.service';
import { DepositRequest } from '../models/deposit-request.model';

@Component({
  selector: 'app-deposit-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './deposit-list.component.html',
  styleUrls: ['./deposit-list.component.scss']
})
export class DepositListComponent implements OnInit {
  deposits: DepositRequest[] = [];
  errorMessage = '';

  constructor(private depositService: DepositService) {}

  ngOnInit() {
    this.depositService.getAll().subscribe({
      next: (data) => {
        this.deposits = data;
        this.errorMessage = '';
      },
      error: (err) => {
        console.error(err);
        this.errorMessage = 'Failed to load deposits. Please try again later.';
      }
    });
  }
}
