import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BasicAuthenticationService } from '../service/basic-authentication.service';
import { SessionDataService } from '../service/data/session-data.service';
import { User } from '../user/user.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username :string
  password:string
  errorMessage ='Invalid Credentials'
  invalidLogin=false
  user:User
  constructor(
    private router:Router,
    private bascicAuthenticationService: BasicAuthenticationService,
    private sessionservice:SessionDataService
  ) { }

  ngOnInit() {
  }
  
  handleBasicLogin(){
    this.bascicAuthenticationService.authenticate(this.username,this.password).subscribe(
      data => {
        this.sessionservice.retrieveUser().subscribe(
          data => {
            this.user=data;
            if(this.user.roleName==='manager'){
              this.router.navigate(['amd/welcome'])
            }
            else{
              this.router.navigate(['amd/project'])
            }
          }
          
        )
        this.invalidLogin=false
        
      },
      error => {
        console.log(error)
        this.invalidLogin=true
      }
    )
    
  }
  
}
