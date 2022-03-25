import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './modules/home/home.component';
import { LoginTemplateComponent } from './modules/loginTemplate/loginTemplate.component';
import { RegisterComponent } from './core/components/register/register.component';
import { LoginComponent } from './core/components/login/login.component';
import { ForgotPasswordComponent } from './core/components/forgotPassword/forgotPassword.component';
import { ChangeForgetPasswordComponent } from './core/components/changeForgetPassword/changeForgetPassword.component';
import { BookDetailsComponent } from './modules/bookDetails/bookDetails.component';
import { BookCategoriesComponent } from './modules/bookCategories/bookCategories.component';


const routes: Routes = [
  { path: 'categories/:category/:id', component: BookDetailsComponent },
  { path: 'categories/:category', component: BookCategoriesComponent },
  { path: 'categories', component: BookCategoriesComponent },
  { path: '', component: HomeComponent },
  {
    path: '', component: LoginTemplateComponent, pathMatch: 'prefix', children: [
      { path: 'register', component: RegisterComponent },
      {
        path: 'login', component: LoginComponent
      },
      { path: 'forgotPassword', component: ForgotPasswordComponent },
      { path: 'changeForgotPassword', component: ChangeForgetPasswordComponent }
    ]
  }

];

export const routing = RouterModule.forRoot(routes);
