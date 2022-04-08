import { Component } from '@angular/core';
import { LoginFormGroup } from '../../validators/loginFormGroup.model';
import { LoginUserModel } from '../../../model/loginUser.model';
import { AuthenticationService } from '../../authentication/authentication.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: 'login.component.html',
  styleUrls: ['login.component.scss']
})
export class LoginComponent {

  constructor(private _authentication: AuthenticationService, private router: Router) {
  }

  passwordVisible: boolean = false;
  formGroup: LoginFormGroup = new LoginFormGroup();
  newUser: LoginUserModel = new LoginUserModel();
  formSubmitted: boolean = false;

  submitForm() {
    // @ts-ignore
    Object.keys(this.formGroup.controls).forEach(c => this.newUser[c] = this.formGroup.controls[c].value);
    this.formSubmitted = true;
    if (this.formGroup.valid) {
      this._authentication
        .login(this.newUser)
        .subscribe( ({token})=> {
          localStorage.setItem('token', token);
          this.router.navigateByUrl('/');
        });
      this.newUser = new LoginUserModel();
      this.formGroup.reset();
      this.formSubmitted = false;
    }
  }
}
