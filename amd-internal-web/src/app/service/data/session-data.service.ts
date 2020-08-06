import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { API_URL } from 'src/app/app.constants';
import { User } from 'src/app/user/user.component';


@Injectable({
  providedIn: 'root'
})
export class SessionDataService {

  constructor(
    private http:HttpClient
  ) { }

  retrieveUser(){
    return this.http.get<User>(`${API_URL}userLogged`);
  }
}
