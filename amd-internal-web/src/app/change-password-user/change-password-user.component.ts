import { Component, OnInit } from '@angular/core';
import { UserDataService } from '../service/data/user-data.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-change-password-user',
  templateUrl: './change-password-user.component.html',
  styleUrls: ['./change-password-user.component.css']
})
export class ChangePasswordUserComponent implements OnInit {
  password: Password
  failMessage: string
  constructor(
    private userService: UserDataService,
    private route: Router
  ) { }

  ngOnInit() {
    this.password = new Password('', '', '')
  }
  changePassword() {
    if (this.password.newPassword === this.password.confirmPassword && this.password.newPassword !== this.password.oldPassword) {
      this.userService.changePassword(this.password).subscribe(
        result => {
          if(result != null){
            this.route.navigate(['amd/welcome'])
          }else{
            this.failMessage='Your old password is wrong'
            setTimeout(function () {
              this.failMessage = null
            }.bind(this), 3000);
          }
        }
      )
    }else{
      this.failMessage='Confirm password is not equal with new password or you dont have any new password'
      setTimeout(function () {
        this.failMessage = null
      }.bind(this), 3000);
    }
  }


}
export class Password {
  constructor(
    public oldPassword: string,
    public newPassword: string,
    public confirmPassword: string
  ) {

  }
}