import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectForEmployeeComponent } from './project-for-employee.component';

describe('ProjectForEmployeeComponent', () => {
  let component: ProjectForEmployeeComponent;
  let fixture: ComponentFixture<ProjectForEmployeeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProjectForEmployeeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProjectForEmployeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
