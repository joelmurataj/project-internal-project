import { Component, OnInit } from '@angular/core';
import { UserDataService } from '../service/data/user-data.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  user: User
  ranks: Rank[]
  messsageWrongEmail: string
  constructor(
    private userService: UserDataService,
    private router: Router
  ) { }

  ngOnInit() {
    this.user = new User(0, '', '', '', '', 0, '', 0, new Date, 0, 0, '', 0, new Date, new Date, 1, '', 0, '')
    this.userService.retrieveAllRanks().subscribe(
      data => {
        this.ranks = data
      }
    )
  }
  saveUser() {
    let EMAIL_REGEXP = /^[a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?(\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)*$/i;
    if (this.user.email != "" &&
      (this.user.email.length <= 5 || !EMAIL_REGEXP.test(this.user.email) || !this.user.email.endsWith("amdtia.com"))) {
      this.messsageWrongEmail = "Please provide a valid email"
      setTimeout(function () {
        this.messsageWrongEmail = null
      }.bind(this), 3000);
    } else {
      this.userService.saveUser(this.user).subscribe(
        result => {
          if (result != null) {
            this.router.navigate(['amd/employees'])
          }
          else {
            this.messsageWrongEmail = "This email exists"
            setTimeout(function () {
              this.messsageWrongEmail = null
            }.bind(this), 3000);
          }
        }
      )
    }

  }


}
export class User {
  constructor(
    public id: number,
    public firstName: string,
    public lastName: string,
    public email: string,
    public password: string,
    public departamentId: number,
    public departamentName: string,
    public projectId: number,
    public startDate: Date,
    public superior: number,
    public role: number,
    public roleName: string,
    public pricePerHour: number,
    public startDateInProject: Date,
    public finishedDateInProject: Date,
    public rankId: number,
    public rankName: string,
    public allocation: number,
    public currentProject: string,
  ) {

  }

}
export class Rank {
  constructor(
    public idRank: number,
    public name: string
  ) {

  }
}