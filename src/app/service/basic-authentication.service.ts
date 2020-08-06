import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { map } from 'rxjs/operators';
import { API_URL } from '../app.constants';
import { User } from '../user/user.component';
import { SessionDataService } from './data/session-data.service';

export const TOKEN='token'
export const AUTHENTICATED_USER='authenticationUser'
export const ROLE='role'
export const DEPARTAMENT_ID='departament_name'
@Injectable({
  providedIn: 'root'
})
export class BasicAuthenticationService{
  currentUser:User
  constructor(
    private http: HttpClient,
    private sessionService:SessionDataService
  ) { }

  authenticate(username, password) {

    return this.http.post<any>(`${API_URL}authenticate`,{
      username,
      password
    }).pipe(
      map(
        data => {
          localStorage.setItem(AUTHENTICATED_USER, username)
          localStorage.setItem(TOKEN, `Bearer ${data.token}`)
          this.sessionService.retrieveUser().subscribe(
            data => {
              this.currentUser = data
              localStorage.setItem(ROLE,this.currentUser.roleName)
            }
          )
        
          return data;
        })
    );
  }

  getAuthenticatedUser() {
    return localStorage.getItem(AUTHENTICATED_USER);
  }

  getAuthenticatedToken() {
    if (this.getAuthenticatedUser())
      return localStorage.getItem(TOKEN);
  }

  isUserLoggedIn() {
    //console.log('yes')
    let user = localStorage.getItem(AUTHENTICATED_USER);
    return !(user === null);
  }

  logout() {
    localStorage.removeItem(AUTHENTICATED_USER)
    localStorage.removeItem(TOKEN)
    localStorage.clear()
  }
  isAdmin() {
    let role = localStorage.getItem(ROLE);
    return (role==='manager')
  }
}
