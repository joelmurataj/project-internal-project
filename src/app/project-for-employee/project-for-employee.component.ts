import { Component, OnInit, ViewChild } from '@angular/core';
import { Project } from '../welcome/welcome.component';
import { UserDataService } from '../service/data/user-data.service';
import { MatPaginator, MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-project-for-employee',
  templateUrl: './project-for-employee.component.html',
  styleUrls: ['./project-for-employee.component.css']
})
export class ProjectForEmployeeComponent implements OnInit {
  projects: Project[]
  displayedColumns: string[]
  dataSource
  constructor(
    private userService: UserDataService
  ) { }

  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit() {
    this.displayedColumns = ['name', 'departamentName', 'startDate', 'finishedDate', 'statusName', 'allocationOfTheEmployee', 'startDateOfEmployee', 'finishedDateOfEmployee'];
    this.userService.retrieveProjectsOfEmployee().subscribe(
      result => {
        this.projects = result
        this.dataSource = new MatTableDataSource<Project>(this.projects);
        this.dataSource.paginator = this.paginator;
      }
    )
  }

}
