import { Component } from '@angular/core';
import { AuthenticationService } from '../../../core/authentication/authentication.service';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { LoginFormGroup } from '../../../core/validators/loginFormGroup.model';
import { LoginUserModel } from '../../../model/loginUser.model';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';


@Component({
  templateUrl: 'auth.component.html'
})
export class AuthComponent {

  constructor(
    private _authentication: AuthenticationService,
    private router: Router,
    private _jwt: JwtHelperService) {
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
          if (this._jwt.decodeToken(response.token).role !== 'ADMIN') {
            throw Error('Brak dostępu do panelu admina')
          } else {
          localStorage.setItem('token', response.token);
          localStorage.setItem('firstname', this._jwt.decodeToken(response.token).name);
          this.router.navigateByUrl('/admin');

          }
        }, catchError((error) => throwError(error)));
      this.newUser = new LoginUserModel();
      this.formGroup.reset();
      this.formSubmitted = false;
    }
  }


}
