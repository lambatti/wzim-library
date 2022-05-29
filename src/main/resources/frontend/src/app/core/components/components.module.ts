import { NgModule } from '@angular/core';
import { HeaderComponent } from './header/header.component';
import { CardComponent } from './card/card.component';
import { PopularSectionComponent } from './popularSection/popularSection.component';
import { FooterComponent } from './footer/footer.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { ForgotPasswordComponent } from './forgotPassword/forgotPassword.component';
import { UserDataComponent } from './userData/userData.component';
import { UserDrawerComponent } from './userDrawer/userDrawer.component';
import { ChangePasswordComponent } from './changePassword/changePassword.component';
import { ChangeQuestionComponent } from './changeQuestion/changeQuestion.component';
import { ModalComponent } from './modal/modal.component';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzGridModule } from 'ng-zorro-antd/grid';
import { NzTypographyModule } from 'ng-zorro-antd/typography';
import { NzListModule } from 'ng-zorro-antd/list';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzCardModule } from 'ng-zorro-antd/card';
import { CommonModule } from '@angular/common';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { RouterModule } from '@angular/router';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzSelectModule } from 'ng-zorro-antd/select';
import { NzDrawerModule } from 'ng-zorro-antd/drawer';
import { NzMenuModule } from 'ng-zorro-antd/menu';
import { NzModalModule } from 'ng-zorro-antd/modal';
import { ReactiveFormsModule } from '@angular/forms';
import { MessageModule } from '../message/message.module';
import { NzDropDownModule } from 'ng-zorro-antd/dropdown';
import { OkModalComponent } from './okModal/okModal.component';
import { NzResultModule } from 'ng-zorro-antd/result';


@NgModule({
  declarations: [HeaderComponent,
    CardComponent,
    PopularSectionComponent,
    FooterComponent,
    RegisterComponent,
    LoginComponent,
    ForgotPasswordComponent,
    UserDataComponent,
    UserDrawerComponent,
    ChangePasswordComponent,
    ChangeQuestionComponent,
    OkModalComponent,
    ModalComponent],
  imports: [NzLayoutModule,
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
    NzSelectModule,
    NzDrawerModule,
    NzMenuModule,
    NzModalModule, ReactiveFormsModule, MessageModule, NzDropDownModule, NzResultModule],
    exports: [HeaderComponent,
        CardComponent,
        PopularSectionComponent,
        FooterComponent,
        UserDataComponent,
        UserDrawerComponent,
        ChangePasswordComponent,
        ChangeQuestionComponent,
        ModalComponent, OkModalComponent]
})
export class ComponentsModule {
}
