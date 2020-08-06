import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { ErrorComponent } from './error/error.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { RouteGuardService } from './service/route-guard.service';
import { ProjectComponent } from './project/project.component';
import { HttpClientModule } from '@angular/common/http'; 
import { ProjectForEmployeeComponent } from './project-for-employee/project-for-employee.component';
import { RouteLoginGuardService } from './service/route-login-guard.service';
import { EmployeesOfTheProjectComponent } from './employees-of-the-project/employees-of-the-project.component';
import { EmployeesComponent } from './employees/employees.component';
import { UserComponent } from './user/user.component';
import { ProjectsOfTheEmployeeComponent } from './projects-of-the-employee/projects-of-the-employee.component';
import { ChangePasswordUserComponent } from './change-password-user/change-password-user.component';
import { ProfileUserComponent } from './profile-user/profile-user.component';

const routes: Routes = [
  { path:'', component: LoginComponent, canActivate:[RouteLoginGuardService]},
  {path:'amd/login', component:LoginComponent, canActivate:[RouteLoginGuardService]},
  {path:'amd/welcome', component:WelcomeComponent, canActivate:[RouteGuardService]},
  {path:'amd/projects/:id', component:ProjectComponent, canActivate:[RouteGuardService]},
  {path:'amd/project', component:ProjectForEmployeeComponent, canActivate:[RouteGuardService]},
  {path:'amd/project/:id', component:EmployeesOfTheProjectComponent, canActivate:[RouteGuardService]},
  {path:'amd/employees', component:EmployeesComponent, canActivate:[RouteGuardService]},
  {path:'amd/addEmployee', component:UserComponent, canActivate:[RouteGuardService]},
  {path:'amd/employee/:id', component:ProjectsOfTheEmployeeComponent, canActivate:[RouteGuardService]},
  {path:'amd/changePassword', component:ChangePasswordUserComponent, canActivate:[RouteGuardService]},
  {path:'amd/profile', component:ProfileUserComponent, canActivate:[RouteGuardService]},
  {path:'**', component:ErrorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes),HttpClientModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
