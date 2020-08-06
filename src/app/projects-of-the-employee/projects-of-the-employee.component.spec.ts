import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectsOfTheEmployeeComponent } from './projects-of-the-employee.component';

describe('ProjectsOfTheEmployeeComponent', () => {
  let component: ProjectsOfTheEmployeeComponent;
  let fixture: ComponentFixture<ProjectsOfTheEmployeeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProjectsOfTheEmployeeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProjectsOfTheEmployeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
