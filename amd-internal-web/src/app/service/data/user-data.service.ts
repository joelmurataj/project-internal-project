import { Injectable } from '@angular/core';
import { User, Rank } from 'src/app/user/user.component';
import { HttpClient } from '@angular/common/http';
import { API_URL } from 'src/app/app.constants';
import { Project } from 'src/app/welcome/welcome.component';
import { Password } from 'src/app/change-password-user/change-password-user.component';

@Injectable({
  providedIn: 'root'
})
export class UserDataService {

  constructor(
    private http: HttpClient
  ) { }

  retrieveUsersOfProject(id) {
    return this.http.get<User[]>(`${API_URL}projectInfo/${id}`);
  }

  retrieveProjectsOfUser(id) {
    return this.http.get<Project[]>(`${API_URL}userInfo/${id}`);
  }

  retrieveProjectsOfEmployee() {
    return this.http.get<Project[]>(`${API_URL}projectsOfUser`);
  }

  updateProjectOfEmployee(userId, projectId) {
    return this.http.put(`${API_URL}projectOfEmployee/${userId}/${projectId}`, projectId);
  }

  retrieveEmployees(project) {
    return this.http.put<User[]>(`${API_URL}employees`,project);
  }

  saveProjectEmployee(projectEmployee) {
    return this.http.put(`${API_URL}projectEmployee`, projectEmployee);
  }

  updateDateOfUserInTheProject(projectId, employee) {
    return this.http.put(`${API_URL}projectEmployee/update/${projectId}`, employee);
  }

  searchByNameOfTheEmployeeAndTheDates(employee,id) {
      return this.http.put<User[]>(`${API_URL}projectEmployee/filter/${id}`,employee);
  }

  retrieveAllEmployees(){
    return this.http.get<User[]>(`${API_URL}allEmployees`);
  }

  filterEmployees(user){
    return this.http.put<User[]>(`${API_URL}allEmployees/filter`,user);
  }

  retrieveAllRanks(){
    return this.http.get<Rank[]>(`${API_URL}allRanks`);
  }

  updateUser(user) {
    return this.http.put(`${API_URL}updateUser`, user);
  }

  saveUser(user) {
    return this.http.post(`${API_URL}addEmployee`, user);
  }

  removeUser(user) {
    return this.http.get<User>(`${API_URL}removeUser/${user.id}`);
  }

  retrieveUser(id){
    return this.http.get<User>(`${API_URL}user/${id}`);
  }

  changePassword(password){
    return this.http.put<Password>(`${API_URL}changePassword`,password);
  }

  retrieveUserLoggedIn(){
    return this.http.get<User>(`${API_URL}userLogged`);
  }
}
