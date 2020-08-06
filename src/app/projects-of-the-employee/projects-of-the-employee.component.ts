import { Component, OnInit, ViewChild, Inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../user/user.component';
import { UserDataService } from '../service/data/user-data.service';
import { Project } from '../welcome/welcome.component';
import { MatTableDataSource, MatPaginator, MAT_DIALOG_DATA, MatDialog } from '@angular/material';

@Component({
  selector: 'app-projects-of-the-employee',
  templateUrl: './projects-of-the-employee.component.html',
  styleUrls: ['./projects-of-the-employee.component.css']
})
export class ProjectsOfTheEmployeeComponent implements OnInit {
  id: string
  userId: number
  employee: User
  displayedColumns: string[]
  projectsOfEmployee: Project[]
  tableIsEmpty: boolean
  dataSource
  messageSuccess: String
  meesageFailed: String
  userNotFound: string

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private route: ActivatedRoute,
    private userService: UserDataService,
    public dialog: MatDialog,
    public router: Router
  ) { }

  ngOnInit() {
    this.refresh()
  }
  refresh() {
    this.displayedColumns = ['name', 'departamentName', 'startDate', 'finishedDate', 'statusName', 'allocationOfTheEmployee', 'startDateOfEmployee', 'finishedDateOfEmployee'];
    this.id = this.route.snapshot.params['id'];
    this.userId = Number.parseInt(this.id)
    if (!Number.isNaN(this.userId) && this.userId <= 2147483647) {
      this.userService.retrieveUser(this.userId).subscribe(
        result => {
          if (result != null) {
            this.employee = result
            this.userService.retrieveProjectsOfUser(this.userId).subscribe(
              data => {
                this.projectsOfEmployee = data
                if (this.projectsOfEmployee.length === 0) {
                  this.tableIsEmpty = true
                }
                this.dataSource = new MatTableDataSource<Project>(this.projectsOfEmployee);
                this.dataSource.paginator = this.paginator;
              }
            )
          }
          else {
            this.userNotFound = 'The user not found'
          }
        }
      )
    } else {
      this.userNotFound = 'The user not found'
    }

    
  }
  opendialog() {
    const dialogRef = this.dialog.open(DialogRemoveUser, {
      data: {
        user: this.employee
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result != null && result != 0) {
        this.userService.removeUser(this.employee).subscribe(
          data => {
            if (data != null) {
              this.router.navigate(['amd/employees'])
              setTimeout(function () {
                this.messageSuccess = null
              }.bind(this), 3000);
              this.refresh()
            }
            else {
              this.meesageFailed = "This user is not deleted because it is part of a project"
              setTimeout(function () {
                this.meesageFailed = null
              }.bind(this), 3000);

            }
          }
        )
      }
    });
  }

}
@Component({
  selector: 'dialog-removeUser',
  templateUrl: 'dialog-removeUser.html',
})
export class DialogRemoveUser {

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: ProjectsOfTheEmployeeComponent,
  ) { }

  ngOnInit() {
  }
}
