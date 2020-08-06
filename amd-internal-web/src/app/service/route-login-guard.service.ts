import { Injectable } from '@angular/core';
import { Router, ActivatedRouteSnapshot, RouterStateSnapshot, CanActivate } from '@angular/router';
import { BasicAuthenticationService, ROLE } from './basic-authentication.service';

@Injectable({
  providedIn: 'root'
})
export class RouteLoginGuardService implements CanActivate {

  constructor(
    private authenticationService: BasicAuthenticationService,
    private router: Router
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (!this.authenticationService.isUserLoggedIn()) {
      return true;
    }

    if (localStorage.getItem(ROLE) === 'manager') {
      this.router.navigate(['amd/welcome'])
    }
    else {
      this.router.navigate(['amd/project'])
    }

    return false;
  }
}
