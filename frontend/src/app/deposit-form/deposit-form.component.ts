import { Component, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { DepositService } from '../services/deposit.service';
import { DepositRequest } from '../models/deposit-request.model';


@Component({
  selector: 'app-deposit-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './deposit-form.component.html',
  styleUrls: ['./deposit-form.component.scss']
})
export class DepositFormComponent {
  @Output() depositAdded = new EventEmitter<void>();

  depositForm: FormGroup;
  successMessage = '';
  errorMessage = '';

  constructor(private fb: FormBuilder, private depositService: DepositService) {
    this.depositForm = this.fb.group({
      customerId: ['', Validators.required],
      customerName: ['', Validators.required],
      depositAmount: [null, [Validators.required, Validators.min(1000)]],
      currency: ['', Validators.required],
      term: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.depositForm.invalid) {
      this.depositForm.markAllAsTouched();
      return;
    }

    const formValue = this.depositForm.value;

    const request: DepositRequest = {
      customerId: formValue.customerId ?? '',
      customerName: formValue.customerName ?? '',
      depositAmount: formValue.depositAmount ?? 0,
      currency: formValue.currency as 'EUR' | 'USD',
      term: formValue.term as DepositRequest['term']
    };

    this.depositService.create(request).subscribe({
      next: () => {
        this.successMessage = 'Deposit submitted successfully!';
        this.errorMessage = '';
        this.depositForm.reset({
          customerId: '',
          customerName: '',
          depositAmount: null,
          currency: '',
          term: ''
        });

        this.depositAdded.emit();

        setTimeout(() => {
          this.successMessage = '';
        }, 3000);
      },
      error: err => {
        this.errorMessage = err.error || 'Failed to submit deposit.';
        this.successMessage = '';
      }
    });
  }
}
