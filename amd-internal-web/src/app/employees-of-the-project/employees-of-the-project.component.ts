import { Component, OnInit, ViewChild, Inject } from '@angular/core';
import { Project, EmployeeProject } from '../welcome/welcome.component';
import { User } from '../user/user.component';
import { ProjectDataService } from '../service/data/project-data.service';
import { ActivatedRoute } from '@angular/router';
import { UserDataService } from '../service/data/user-data.service';
import { MatPaginator, MatTableDataSource, MatDialog, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-employees-of-the-project',
  templateUrl: './employees-of-the-project.component.html',
  styleUrls: ['./employees-of-the-project.component.css']
})

export class EmployeesOfTheProjectComponent implements OnInit {
  project: Project
  employeesOfTheProject: User[]
  displayedColumns: string[];
  id: string
  projectId: number
  dataSource;
  employee: User
  projectEmployee: EmployeeProject
  tableIsEmpty: boolean
  messageProblem: string
  projectNotFound: string

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private projectService: ProjectDataService,
    private route: ActivatedRoute,
    private userService: UserDataService,
    public dialog: MatDialog
  ) { }

  ngOnInit() {

    this.refresh()
  }
  refresh() {
    this.displayedColumns = ['firstName', 'lastName', 'email', 'Start date in the project', 'Finish date in the project', 'rank', 'allocation', 'action'];
    this.id = this.route.snapshot.params['id'];

    this.projectId = Number.parseInt(this.id)
    if (!Number.isNaN(this.projectId) && this.projectId <= 2147483647) {
      this.projectService.getProject(this.projectId).subscribe(
        result => {
          if (result != null) {
            this.project = result;
            this.projectEmployee = new EmployeeProject(this.projectId, 2, this.project.startDate, this.project.finishedDate, true, 100)
            this.userService.retrieveUsersOfProject(this.projectId).subscribe(
              data => {
                this.employeesOfTheProject = data
                if (this.employeesOfTheProject.length === 0) {
                  this.tableIsEmpty = true
                }
                this.dataSource = new MatTableDataSource<User>(this.employeesOfTheProject);
                this.dataSource.paginator = this.paginator;
              }
            )
          } else {
            this.projectNotFound = 'The project was not found'
          }
        }
      )
    } else {
      this.projectNotFound = 'The project was not found'
    }

  }
  removeEmployeeFromTheProject(id) {
    this.userService.updateProjectOfEmployee(id, this.projectId).subscribe(
      result => {
        this.refresh()
      }
    )
  }

  opendialogAddEmployeeToProject() {
    const dialogRef = this.dialog.open(DialogAddEmployeeToProject, {
      data: {
        projectId: this.projectId,
        projectEmployee: this.projectEmployee,
        project: this.project
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result != null) {

        if (result.startDate <= this.projectEmployee.startDateEmployee) {
          this.projectEmployee.projectId = this.projectId
          this.projectEmployee.userId = result.id

          this.userService.saveProjectEmployee(this.projectEmployee).subscribe(
            data => {
              if (data != null) {
                this.tableIsEmpty = false
                this.refresh()
              } else {
                this.messageProblem = 'No more spaces'
                setTimeout(function () {
                  this.messageProblem = null
                }.bind(this), 3000);
              }
            }
          )
        } else {
          this.messageProblem = 'This employee was not added, because the start date must be grater than the employee job start date'
          setTimeout(function () {
            this.messageProblem = null
          }.bind(this), 3000);
        }
      }
    });
  }

  opendialogUpdateDateOfEmployee(employee) {
    const dialogRef = this.dialog.open(DialogEditDateOfEmployeeComponent, {
      data: {
        employee: employee,
        project: this.project
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result != null) {
        this.userService.updateDateOfUserInTheProject(this.projectId, result).subscribe(
          data => {
            this.refresh()
          }
        )
      }
    });
  }

  filter() {
    const dialogRef = this.dialog.open(FilterComponent, {
      data: {
        employee: this.employee,
        project: this.project
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result != null) {
        this.employee = result

        this.userService.searchByNameOfTheEmployeeAndTheDates(this.employee, this.projectId).subscribe(
          data => {
            this.employeesOfTheProject = data
            if (this.employeesOfTheProject.length === 0) {
              this.tableIsEmpty = true
            } else {
              this.tableIsEmpty = false
            }
            this.dataSource = new MatTableDataSource<User>(this.employeesOfTheProject);
            this.dataSource.paginator = this.paginator;
          }
        )
      }
    });
  }
}

@Component({
  selector: 'dialog-addEmployeeToPrject',
  templateUrl: 'dialog-addEmployeeToPrject.html',
  styleUrls: ['./dialog-addEmployeeToPrject.css']
})
export class DialogAddEmployeeToProject {
  employees: User[]
  dataSource;
  displayedColumns: string[];
  user:User
  firstname:string=""
  tableIsEmpty:boolean

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: EmployeesOfTheProjectComponent,
    private userService: UserDataService
  ) { }

  ngOnInit() {
    this.user = new User(0, '', '', '', '', 0, '', 0, new Date, 0, 0, '', 0, new Date, new Date, 0, '', 0, '')
    this.refresh()
  }
  refresh(){
    this.displayedColumns = ['firstName', 'lastName', 'email', 'rank', 'startDate', 'finishedDate', 'selection'];
    this.userService.retrieveEmployees(this.data.project).subscribe(
      result => {
        this.employees = result
        this.dataSource = new MatTableDataSource<User>(this.employees);
        this.dataSource.paginator = this.paginator;
      }
    )
  }

  searchByFirstnameOfTheUser(){
    if(this.tableIsEmpty === true){
      this.tableIsEmpty=false
    }
    if (this.firstname === "") {
      this.refresh()
    } else {
      this.user.firstName=this.firstname
      this.userService.filterEmployees(this.user).subscribe(
        result => {
          if (result.length != 0) {
            this.tableIsEmpty = false
            this.employees = result;
            this.dataSource = new MatTableDataSource<User>(this.employees);
            this.dataSource.paginator = this.paginator;
          }
          else {
            this.tableIsEmpty = true
          }
        }
      )

    }
  }
}

@Component({
  selector: 'dialog-edit-date-of-employee.component',
  templateUrl: 'dialog-edit-date-of-employee.component.html',
})

export class DialogEditDateOfEmployeeComponent {

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: EmployeesOfTheProjectComponent,
  ) { }

  ngOnInit() {
  }
}

@Component({
  selector: 'filter.component',
  templateUrl: 'filter.component.html',
})
export class FilterComponent {

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: EmployeesOfTheProjectComponent,
  ) { }

  ngOnInit() {
    this.data.employee = new User(0, "", "", "", "", 0, "", 0, new Date, 0, 0, "", 0, this.data.project.startDate, this.data.project.finishedDate, 0, '', 100, '')
  }
}
