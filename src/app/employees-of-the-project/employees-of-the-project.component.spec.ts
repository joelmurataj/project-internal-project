import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeesOfTheProjectComponent } from './employees-of-the-project.component';

describe('EmployeesOfTheProjectComponent', () => {
  let component: EmployeesOfTheProjectComponent;
  let fixture: ComponentFixture<EmployeesOfTheProjectComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeesOfTheProjectComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeesOfTheProjectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
