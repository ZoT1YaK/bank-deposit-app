import { Component, ViewChild  } from '@angular/core';
import { DepositFormComponent } from './deposit-form/deposit-form.component';
import { DepositListComponent } from './deposit-list/deposit-list.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    DepositFormComponent,
    DepositListComponent
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  @ViewChild('depositList') depositListComponent?: DepositListComponent;

  onDepositAdded() {
    this.depositListComponent?.ngOnInit();
  }
}
