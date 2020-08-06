import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BasicAuthenticationService} from '../service/basic-authentication.service';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  private token:string
  isManager = false;
  userLoggedIn=false;
  constructor(
    private router :Router,
    private basicAuthenticationService: BasicAuthenticationService
  ) { }

  ngOnInit() {
    if(this.basicAuthenticationService.isUserLoggedIn){
      this.userLoggedIn=true;
    }
  }
  isUserLoggedIn(){
    if(this.basicAuthenticationService.isUserLoggedIn){
      this.userLoggedIn=true;
    }
  }
  
  logout(){
    this.basicAuthenticationService.logout();
    this.router.navigate(['amd/login']);
  }
  
}
