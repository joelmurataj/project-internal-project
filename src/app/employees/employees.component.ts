import { Component, OnInit, ViewChild, Inject } from '@angular/core';
import { User, Rank } from '../user/user.component';
import { UserDataService } from '../service/data/user-data.service';
import { MatPaginator, MatTableDataSource, MatDialog, MAT_DIALOG_DATA } from '@angular/material';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.css']
})
export class EmployeesComponent implements OnInit {
  employees: User[]
  dataSource
  userId: number
  displayedColumns: string[];
  editable: boolean
  ranks: Rank[]
  user: User
  tableIsEmpty: boolean
  constructor(
    private userService: UserDataService,
    private router: Router,
    public dialog: MatDialog
  ) { }

  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit() {
    this.displayedColumns = ['firstName', 'lastName', 'email', 'departament', 'startDate', 'currentProject', 'rank', 'action']
    this.refresh();
    this.editable = false
  }
  refresh() {

    this.userService.retrieveAllEmployees().subscribe(
      result => {
        this.employees = result
        this.dataSource = new MatTableDataSource<User>(this.employees);
        this.dataSource.paginator = this.paginator;
      }
    )
  }
  update(id) {
    this.userId = id
    this.editable = true
    this.refresh()
    this.userService.retrieveAllRanks().subscribe(
      data => {
        this.ranks = data
      }
    )
  }
  updateRankOFemployee(user) {
    this.editable = false
    this.userService.updateUser(user).subscribe(
      result => {
        this.refresh()
      }
    )
  }

  createUser() {
    this.router.navigate(['amd/addEmployee'])
  }

  infoOfEmployee(id) {
    this.router.navigate(['amd/employee', id]);
  }

  filter() {
    const dialogRef = this.dialog.open(FilterForEmployeesComponent, {
      data: {
        user: this.user
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result != null && (result.rankId!=0 || result.firstName!="")) {
        this.userService.filterEmployees(result).subscribe(
          data => {
            this.employees = data
            if (this.employees.length === 0) {
              this.tableIsEmpty = true
            } else {
              this.tableIsEmpty = false
            }
            this.dataSource = new MatTableDataSource<User>(this.employees);
            this.dataSource.paginator = this.paginator;
          }
        )
      }
    });
  }
}
@Component({
  selector: 'filter-for-employees.component',
  templateUrl: 'filter-for-employees.component.html',
})
export class FilterForEmployeesComponent {
  ranks: Rank[]
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: EmployeesComponent,
    private userService: UserDataService
  ) { }

  ngOnInit() {
    this.data.user = new User(0, "", "", "", "", 0, "", 0, new Date, 0, 0, "", 0, new Date, new Date, 0, '', 100, '')
    this.userService.retrieveAllRanks().subscribe(
      data => {
        this.ranks = data
      }
    )
  }
}
