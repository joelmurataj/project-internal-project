import { Component, OnInit } from '@angular/core';
import { ProjectDataService } from '../service/data/project-data.service';
import { Project, Technology } from '../welcome/welcome.component';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {
  project: Project
  id: string
  projectId: number
  updating = false
  messageProblem: string
  projectNotFound: string
  technologies:Technology[]
  constructor(
    private projectService: ProjectDataService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.projectId = Number.parseInt(this.id)
    if (!Number.isNaN(this.projectId) && this.projectId <= 2147483647) {
      this.project = new Project(this.projectId, '', 0, '', 2, new Date, new Date, "", false, 1, 0, 1, '', 1, new Date, new Date,'', null)
      this.projectService.retrieveAllTechnologies().subscribe(
        data => {
          this.technologies=data
        }
      )
      if (this.projectId != 0) {
        this.updating = true
        this.projectService.retrieveProject(this.projectId).subscribe(
          data => {
            this.project = data
          })
      }
    } else {
      this.projectNotFound = 'The project was not found'
    }
  }

  saveProject() {
    if (this.project.maxOfEmployee > 0 && this.project.startDate <= this.project.finishedDate) {
      this.projectService.updateProject(this.id, this.project).subscribe(
        data => {
          if (data != null) {
            this.router.navigate(['amd/welcome'])
          } else {
            this.messageProblem = 'Conflicts'
            setTimeout(function () {
              this.messageProblem = null
            }.bind(this), 4000);
          }
        }
      )
    } else {
      this.messageProblem = 'Max of employee must be greater than 0 and the start date of project must be greater than finished date'
      setTimeout(function () {
        this.messageProblem = null
      }.bind(this), 4000);
    }
  }
}
