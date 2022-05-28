import { Component } from '@angular/core';
import { PasswordReminder } from '../../validators/passwordReminder.model';
import { PasswordReminderModel } from '../../../model/passwordReminder.model';
import { AuthenticationService } from '../../authentication/authentication.service';
import { securityQuestions } from '../../enums/securityQuestionEnum';


@Component({
  selector: 'app-forgotPassword',
  templateUrl: 'forgotPassword.component.html'
})
export class ForgotPasswordComponent {

  constructor(private readonly _auth: AuthenticationService) {
  }

  isVisibleModal: boolean = false;

  passwordVisible: boolean = false;
  confirmPasswordVisible: boolean = false;


  formGroup: PasswordReminder = new PasswordReminder();
  modelPasswordReminder: PasswordReminderModel = new PasswordReminderModel();
  formSubmitted: boolean = false;


  get questionVerification() {
    return securityQuestions;
  }

  submitFrom(): void {
    // @ts-ignore
    Object.keys(this.formGroup.controls).forEach(c => this.modelPasswordReminder[c] = this.formGroup.controls[c].value);
    this.formSubmitted = true;
    if (this.formGroup.valid) {
      console.log(this.modelPasswordReminder);
      this._auth.passwordReminder(this.modelPasswordReminder).subscribe(() => {
        this.isVisibleModal = true;
      });
      this.modelPasswordReminder = new PasswordReminderModel();
      this.formGroup.reset();
      this.formSubmitted = false;
    }
  }

  handleOk() {
    this.isVisibleModal = false;
  }
}
