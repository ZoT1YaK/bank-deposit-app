import { ComponentFixture, TestBed } from '@angular/core/testing';
import { DepositFormComponent } from './deposit-form.component';

describe('DepositFormComponent', () => {
  let component: DepositFormComponent;
  let fixture: ComponentFixture<DepositFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DepositFormComponent]
    }).compileComponents();

    fixture = TestBed.createComponent(DepositFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });
});
