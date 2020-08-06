import { Injectable } from '@angular/core';
import { Project, Status, Technology } from 'src/app/welcome/welcome.component';
import { HttpClient } from '@angular/common/http';
import { API_URL } from 'src/app/app.constants';

@Injectable({
  providedIn: 'root'
})
export class ProjectDataService {

  constructor(
    private http:HttpClient
  ) { }

  retrieveAllProject(){
    return this.http.get<Project[]>(`${API_URL}projects`);
  }
  deleteProject(id){
    return this.http.get<Project>(`${API_URL}projects/${id}`);
  }

  retrieveProject(id){
    return this.http.get<Project>(`${API_URL}projects/update/${id}`);
  }

  getProject(id){
    return this.http.get<Project>(`${API_URL}project/${id}`);
  }

  updateProject(id,project){
    return this.http.put(`${API_URL}projects/${id}`,project);
  }

  createProject(id,project){
    return this.http.post(`${API_URL}projects`,project);
  }

  searchByNameOfTheProject(name){
    return this.http.get<Project[]>(`${API_URL}projects/searchByTitle/${name}`);
  }

  retrieveAllStatus(){
    return this.http.get<Status[]>(`${API_URL}allStatus`);
  }

  retrieveAllTechnologies(){
    return this.http.get<Technology[]>(`${API_URL}allTechnologies`);
  }
}
