import { Component } from '@angular/core';
import { RegisterFormGroup } from '../../validators/registerFormGroup.model';
import { RegisterUserModel } from '../../../model/registerUser.model';
import { AuthenticationService } from '../../authentication/authentication.service';
import { SecurityQuestionEnum } from '../../enums/securityQuestionEnum';


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

  securityQuestions = [
    {
      key: Object.keys(SecurityQuestionEnum)[0],
      value: SecurityQuestionEnum.ANIMAL
    },
    {
      key: Object.keys(SecurityQuestionEnum)[1],
      value: SecurityQuestionEnum.FRIENDS_NAME
    },
    {
      key: Object.keys(SecurityQuestionEnum)[2],
      value: SecurityQuestionEnum.SANDWICH
    }
  ]


  constructor(private _authentication: AuthenticationService) {
  }

  submitForm() {
    // @ts-ignore
    Object.keys(this.formGroup.controls).forEach(c => this.newUser[c] = this.formGroup.controls[c].value);
    this.formSubmitted = true;
    if (this.formGroup.valid) {
      this._authentication.register(this.newUser);
      this.newUser = new RegisterUserModel();
      this.formGroup.reset();
      this.formSubmitted = false;
    }
  }
}
