export interface DepositRequest {
  id?: number;
  customerId: string;
  customerName: string;
  depositAmount: number;
  currency: 'EUR' | 'USD';
  term: '1 month' | '3 months' | '6 months' | '1 year' | '2 years' | '3 years';
}
