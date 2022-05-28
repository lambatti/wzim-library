import {  Routes } from '@angular/router';
import { HomeComponent } from './modules/home/home.component';
import { LoginTemplateComponent } from './modules/loginTemplate/loginTemplate.component';
import { RegisterComponent } from './core/components/register/register.component';
import { LoginComponent } from './core/components/login/login.component';
import { ForgotPasswordComponent } from './core/components/forgotPassword/forgotPassword.component';
import { BookDetailsComponent } from './modules/bookDetails/bookDetails.component';
import { BookCategoriesComponent } from './modules/bookCategories/bookCategories.component';
import { ReadBookComponent } from './modules/readBook/readBook.component';
import { UserTemplateComponent } from './modules/userTemplate/userTemplate.component';
import { UserDataComponent } from './core/components/userData/userData.component';
import { ChangePasswordComponent } from './core/components/changePassword/changePassword.component';
import { ChangeQuestionComponent } from './core/components/changeQuestion/changeQuestion.component';
import { AuthGuard } from './core/guards/authGuard.guard';
import { AuthComponent } from './modules/admin/auth/auth.component';
import { AdminPanelComponent } from './modules/admin/adminPanel/adminPanel.component';
import { WorkersSummaryComponent } from './modules/admin/workersSummary/workersSummary.component';
import { AddWorkerComponent } from './modules/admin/addWorker/addWorker.component';
import { DeleteUserComponent } from './modules/admin/deleteUser/deleteUser.component';
import { AdminAuthGuard } from './core/guards/adminAuth.guard';


export const routes: Routes = [
  { path: 'category/:category/:id/read', component: ReadBookComponent },
  { path: 'category/:category/:id', component: BookDetailsComponent },
  { path: 'category/:category', component: BookCategoriesComponent },
  { path: 'category', component: BookCategoriesComponent },
  {
    path: 'user', component: UserTemplateComponent, canActivate: [AuthGuard], pathMatch: 'prefix', children: [
      { path: 'changePassword', component: ChangePasswordComponent },
      { path: 'changeQuestion', component: ChangeQuestionComponent },
      { path: ':name', component: UserDataComponent }
    ]
  },
  { path: '', component: HomeComponent },
  {
    path: '', component: LoginTemplateComponent, pathMatch: 'prefix', children: [
      { path: 'register', component: RegisterComponent },
      {
        path: 'login', component: LoginComponent
      },
      { path: 'forgotPassword', component: ForgotPasswordComponent },
    ]
  },
  {
    path: 'admin/auth', component: AuthComponent
  },
  {
    path: 'admin',  pathMatch: 'prefix', component: AdminPanelComponent, canActivate: [AdminAuthGuard],  children: [
      { path: 'workersSummary', component: WorkersSummaryComponent},
      { path: 'addWorker', component: AddWorkerComponent },
      { path: 'deleteUser', component: DeleteUserComponent }
      //  { path: 'borrowedBooks', component: WorkersSummaryComponent },

    ]
  },
  { path: '**', redirectTo: '/' }
];
