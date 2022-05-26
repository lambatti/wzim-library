import { Component } from '@angular/core';
import { LoginFormGroup } from '../../validators/loginFormGroup.model';
import { LoginUserModel } from '../../../model/loginUser.model';
import { AuthenticationService } from '../../authentication/authentication.service';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';


@Component({
  selector: 'app-login',
  templateUrl: 'login.component.html',
  styleUrls: ['login.component.scss']
})
export class LoginComponent {

  constructor(private _authentication: AuthenticationService, private router: Router, private _jwt: JwtHelperService) {
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
        .subscribe( (response)=> {
          localStorage.setItem('token', response.token);
         localStorage.setItem('email', this._jwt.decodeToken(response.token).sub);
          this.router.navigateByUrl('/');
        });
      this.newUser = new LoginUserModel();
      this.formGroup.reset();
      this.formSubmitted = false;
    }
  }
}
