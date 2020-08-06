import { Component, OnInit, ViewChild, Inject } from '@angular/core';
import { User } from '../user/user.component';
import { UserDataService } from '../service/data/user-data.service';
import { MatPaginator, MAT_DIALOG_DATA, MatDialog } from '@angular/material';

@Component({
  selector: 'app-profile-user',
  templateUrl: './profile-user.component.html',
  styleUrls: ['./profile-user.component.css']
})
export class ProfileUserComponent implements OnInit {
  user: User
  userUpdated: User
  messageSuccess: string
  constructor(
    private userService: UserDataService,
    public dialog: MatDialog
  ) { }

  ngOnInit() {
    this.refresh()
  }
  refresh(){
    this.userService.retrieveUserLoggedIn().subscribe(
      result => {
        this.userUpdated = result
        this.user = result
      }
    )
  }
  opendialog() {
    const dialogRef = this.dialog.open(DialogUpdateUser, {
      data: {
        userUpdated: this.userUpdated
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result != null) {
        this.userService.updateUser(result).subscribe(
          data => {
            this.messageSuccess = 'The user is updated successfully'
            this.user = result
            setTimeout(function () {
              this.messageSuccess = null
            }.bind(this), 3000);
          }
        )
      }else{
        this.refresh()
      }
    });
  }

}
@Component({
  selector: 'dialog-update-user.component',
  templateUrl: 'dialog-update-user.component.html',
  styleUrls: ['./dialog-update-user.component.css']
})
export class DialogUpdateUser {

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: ProfileUserComponent,
  ) { }

  ngOnInit() {
  }
}