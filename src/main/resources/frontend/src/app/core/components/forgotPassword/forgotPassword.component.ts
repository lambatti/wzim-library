import { Component } from '@angular/core';
import { PasswordReminder } from '../../validators/passwordReminder.model';
import { PasswordReminderModel } from '../../../model/passwordReminder.model';
import { AuthenticationService } from '../../authentication/authentication.service';


@Component({
  selector: 'app-forgotPassword',
  templateUrl: 'forgotPassword.component.html'
})
export class ForgotPasswordComponent {

  constructor(private readonly _auth: AuthenticationService) {
  }

  passwordVisible: boolean = false;
  confirmPasswordVisible: boolean = false;


  formGroup: PasswordReminder = new PasswordReminder();
  modelPasswordReminder: PasswordReminderModel = new PasswordReminderModel();
  formSubmitted: boolean = false;

  submitFrom(): void {
    // @ts-ignore
    Object.keys(this.formGroup.controls).forEach(c => this.modelPasswordReminder[c] = this.formGroup.controls[c].value);
    this.formSubmitted = true;

      console.log(this.modelPasswordReminder);

    if (this.formGroup.valid) {
      this._auth.passwordReminder(this.modelPasswordReminder).subscribe();
      this.modelPasswordReminder = new PasswordReminderModel();
      this.formGroup.reset();
      this.formSubmitted = false;
    }

  }

}
