import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { WelcomeComponent, DialogDeleteProject } from './welcome/welcome.component';
import { LoginComponent } from './login/login.component';
import { ProjectComponent } from './project/project.component';
import { MenuComponent } from './menu/menu.component';
import { FooterComponent } from './footer/footer.component';
import { ErrorComponent } from './error/error.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { HttpIntercepterBasicAuthService } from './service/http/http-intercepter-basic-auth.service';
import { UserComponent } from './user/user.component';
import { ProjectForEmployeeComponent } from './project-for-employee/project-for-employee.component';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { BrowserAnimationsModule, NoopAnimationsModule } from '@angular/platform-browser/animations';
import {MatButtonModule, MatCheckboxModule, MatFormFieldModule, MatInputModule, MatRippleModule, MatTableModule, MatPaginatorModule, MatNativeDateModule, MatDatepickerModule, MatDialogModule, MatSelectModule} from '@angular/material';
import { EmployeesOfTheProjectComponent, DialogAddEmployeeToProject, DialogEditDateOfEmployeeComponent, FilterComponent } from './employees-of-the-project/employees-of-the-project.component';
import { MatSidenavModule } from '@angular/material';
import { EmployeesComponent, FilterForEmployeesComponent} from './employees/employees.component';
import { ProjectsOfTheEmployeeComponent, DialogRemoveUser } from './projects-of-the-employee/projects-of-the-employee.component';
import { ChangePasswordUserComponent } from './change-password-user/change-password-user.component';
import { ProfileUserComponent, DialogUpdateUser } from './profile-user/profile-user.component';

@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
    LoginComponent,
    ProjectComponent,
    MenuComponent,
    FooterComponent,
    ErrorComponent,
    UserComponent,
    ProjectForEmployeeComponent,
    EmployeesOfTheProjectComponent,
    DialogAddEmployeeToProject,
    DialogDeleteProject,
    DialogEditDateOfEmployeeComponent,
    FilterComponent,
    EmployeesComponent,
    DialogRemoveUser,
    ProjectsOfTheEmployeeComponent,
    FilterForEmployeesComponent,
    ChangePasswordUserComponent,
    ProfileUserComponent,
    DialogUpdateUser
  ],
  imports: [
    MatTableModule,
    MatPaginatorModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatRippleModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    AngularFontAwesomeModule,
    BrowserAnimationsModule,
    NoopAnimationsModule,
    MatButtonModule, 
    MatCheckboxModule,
    MatDialogModule,
    MatSidenavModule,
    MatSelectModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: HttpIntercepterBasicAuthService, multi:true}
  ],
  entryComponents: [
    DialogAddEmployeeToProject,
    DialogDeleteProject,
    DialogEditDateOfEmployeeComponent,
    FilterComponent,
    DialogRemoveUser,
    FilterForEmployeesComponent,
    DialogUpdateUser
  ],
  bootstrap: [AppComponent],
})
export class AppModule { }
