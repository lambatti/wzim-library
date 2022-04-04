import { Component } from '@angular/core';
import { RegisterFormGroup } from '../../validators/registerFormGroup.model';
import { RegisterUserModel } from '../../../model/registerUser.model';


@Component({
  selector: 'app-register',
  templateUrl: 'register.component.html',
  styleUrls: ['register.component.scss']
})
export class RegisterComponent {
  passwordVisible: boolean = false;
  confirmPasswordVisible: boolean = false;
  formGroup: RegisterFormGroup = new RegisterFormGroup();
  newUser: RegisterUserModel = new RegisterUserModel();
  formSubmitted: boolean = false;

  submitForm() {
    // @ts-ignore
    Object.keys(this.formGroup.controls).forEach(c => this.newUser[c] = this.formGroup.controls[c].value);
    this.formSubmitted = true;
    if (this.formGroup.valid) {
      this.newUser = new RegisterUserModel();
      // wstrzyknięcie metody do rejestracji
      console.log('Submit');
      this.formGroup.reset();
      this.formSubmitted = false;
    }
  }
}
