import { Component, OnInit, ViewChild, Inject } from '@angular/core';
import { ProjectDataService } from '../service/data/project-data.service';
import { Router } from '@angular/router';
import { MatTableDataSource, MatPaginator, MAT_DIALOG_DATA, MatDialog } from '@angular/material';

export class EmployeeProject {
  constructor(
    public projectId: number,
    public userId: number,
    public startDateEmployee: Date,
    public finishedDateEmployee: Date,
    public activated: boolean,
    public allocation: number
  ) {
    this.projectId = projectId
  }
}
export class Project {
  constructor(
    public id: number,
    public name: string,
    public departamentId: number,
    public departamentName: string,
    public pricePerMonth: number,
    public startDate: Date,
    public finishedDate: Date,
    public projectDetail: string,
    public flag: boolean,
    public maxOfEmployee: number,
    public vacancy: number,
    public statusId: number,
    public statusName: string,
    public allocationOfTheEmployee:number,
    public startDateOfEmployee:Date,
    public finishedDateOfEmployee:Date,
    public technologiesInString:string,
    public technologies: Technology[]
  ) {

  }
}
export class Status{
  constructor(
    public id:number,
    public name:string
  ){
  }
 
}
export class Technology{
  constructor(
    public id:number,
    public technologyName:string,
    public isActivated:boolean
  ){
  }
}
@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {
  projects: Project[]
  messageSuccess: string
  meesageFailed: string
  displayedColumns: string[];
  dataSource;
  nameOfTheProject: string=""
  tableIsEmpty: boolean
  editable: boolean
  projectId:number
  listOfStatus: Status[]

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private projectService: ProjectDataService,
    private router: Router,
    public dialog: MatDialog
  ) { }

  ngOnInit() {

    this.displayedColumns = ['name', 'startDate', 'finishedDate', 'departament', 'vacancy', 'maxOfEmployees', 'status', 'actions'];
    this.refresh()
    this.editable = false
  }

  refresh() {

    this.projectService.retrieveAllProject().subscribe(
      response => {
        this.projects = response;
        if (this.projects.length === 0) {
          this.tableIsEmpty = true;
        }
        this.dataSource = new MatTableDataSource<Project>(this.projects);
        this.dataSource.paginator = this.paginator;
      }
    )
  }
  update(id) {
    this.projectId = id
    this.editable = true
    this.refresh()
    this.projectService.retrieveAllStatus().subscribe(
      data => {
        this.listOfStatus = data
      }
    )
  }
  updateStatusOfProject(project){
    this.projectService.updateProject(project.id,project).subscribe(
      result=>{
        this.editable=false
        this.refresh()
      }
    )
  }

  updateProject(id) {
    this.router.navigate(['amd/projects', id]);
  }

  createProject() {
    this.router.navigate(['amd/projects', 0])
  }

  getInfoOfProject(id) {
    this.router.navigate(['amd/project', id]);
  }

  searchByNameOfTheProject() {
    if(this.tableIsEmpty === true){
      this.tableIsEmpty=false
    }
    if (this.nameOfTheProject === "") {
      this.refresh()
    } else {
      this.projectService.searchByNameOfTheProject(this.nameOfTheProject).subscribe(
        result => {
          if (result.length != 0) {
            this.tableIsEmpty = false
            this.projects = result;
            this.dataSource = new MatTableDataSource<Project>(this.projects);
            this.dataSource.paginator = this.paginator;
          }
          else {
            this.tableIsEmpty = true
          }
        }
      )

    }
  }

  opendialog(name, id) {
    const dialogRef = this.dialog.open(DialogDeleteProject, {
      data: {
        projectName: name,
        projectId: id
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result != null && result != 0) {
        this.projectService.deleteProject(result).subscribe(
          response => {
            if (response != null) {
              this.messageSuccess = "The project was deleted successfuly!"
              setTimeout(function () {
                this.messageSuccess = null
              }.bind(this), 3000);
              this.refresh()
            }
            else {
              this.meesageFailed = "This project is not deleted because it has employees working on it"
              setTimeout(function () {
                this.meesageFailed = null
              }.bind(this), 3000);

            }
          })
      }
    });
  }
}

@Component({
  selector: 'dialog-deleteProject',
  templateUrl: 'dialog-deleteProject.html',
})
export class DialogDeleteProject {

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: WelcomeComponent,
  ) { }

  ngOnInit() {
  }
}