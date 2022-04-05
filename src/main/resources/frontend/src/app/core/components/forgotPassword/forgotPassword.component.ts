import { Component } from '@angular/core';
import { PasswordReminder } from '../../validators/passwordReminder.model';


@Component({
  selector: 'app-forgotPassword',
  templateUrl: 'forgotPassword.component.html'
})
export class ForgotPasswordComponent {
  passwordVisible: boolean = false;
  formGroup: PasswordReminder = new PasswordReminder();




  submitFrom():void {

  }

}
