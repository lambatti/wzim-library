import { Component } from '@angular/core';
import { LoginFormGroup } from '../../validators/loginFormGroup.model';
import { LoginUserModel } from '../../../model/loginUser.model';


@Component({
  selector: 'app-login',
  templateUrl: 'login.component.html',
  styleUrls: ['login.component.scss']
})
export class LoginComponent {
  passwordVisible: boolean = false;

  formGroup: LoginFormGroup = new LoginFormGroup();
  newUser: LoginUserModel = new LoginUserModel();
  formSubmitted: boolean = false;

  submitForm() {
    // @ts-ignore
    Object.keys(this.formGroup.controls).forEach(c => this.newUser[c] = this.formGroup.controls[c].value);
    this.formSubmitted = true;
    if (this.formGroup.valid) {
      this.newUser = new LoginUserModel();
      console.log('Submit');
      this.formGroup.reset();
      this.formSubmitted = false;
    }
  }
}
