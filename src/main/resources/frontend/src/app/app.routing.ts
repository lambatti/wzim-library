import { RouterModule, Routes } from '@angular/router';
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


const routes: Routes = [
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
    path: 'admin', loadChildren: () => import('./modules/admin/admin.module').then(m => m.AdminModule)
  },
  { path: '**', redirectTo: '/' }
];

export const routing = RouterModule.forRoot(routes);
