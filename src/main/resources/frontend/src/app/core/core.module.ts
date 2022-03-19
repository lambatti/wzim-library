import { NgModule } from '@angular/core';
import { HeaderComponent } from './components/header/header.component';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzGridModule } from 'ng-zorro-antd/grid';
import { NzTypographyModule } from 'ng-zorro-antd/typography';
import { NzListModule } from 'ng-zorro-antd/list';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { CommonModule } from '@angular/common';
import { CardComponent } from './components/card/card.component';
import { NzCardModule } from 'ng-zorro-antd/card';
import { PopularSectionComponent } from './components/popularSection/popularSection.component';
import { FooterComponent } from './components/footer/footer.component';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { RouterModule } from '@angular/router';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzSelectModule } from 'ng-zorro-antd/select';
import { ForgotPasswordComponent } from './components/forgotPassword/forgotPassword.component';
import { ChangeForgetPasswordComponent } from './components/changeForgetPassword/changeForgetPassword.component';


@NgModule({
  declarations: [
    HeaderComponent,
    CardComponent,
    PopularSectionComponent,
    FooterComponent,
    RegisterComponent,
    LoginComponent,
    ForgotPasswordComponent,
    ChangeForgetPasswordComponent
  ],
  imports: [
    NzLayoutModule,
    NzGridModule,
    NzTypographyModule,
    NzListModule,
    NzButtonModule,
    NzCardModule,
    CommonModule,
    NzIconModule,
    RouterModule,
    NzFormModule,
    NzInputModule,
    NzSelectModule
  ],
  exports: [
    HeaderComponent,
    CardComponent,
    PopularSectionComponent,
    FooterComponent,
    ForgotPasswordComponent,
    ChangeForgetPasswordComponent
  ]
})
export class CoreModule {
}
