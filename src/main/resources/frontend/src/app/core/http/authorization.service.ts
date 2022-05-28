import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginUserModel } from '../../model/loginUser.model';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { TokenModel } from '../../model/token.model';
import { RegisterUserModel } from '../../model/registerUser.model';
import { PasswordReminderModel } from '../../model/passwordReminder.model';


@Injectable()
export class AuthorizationService {
  constructor(private _http: HttpClient) {
  }

  login(user: LoginUserModel): Observable<TokenModel> {
    return this._http
      .post<TokenModel>(`${environment.url}/login`, user);
  }

  register(newUser: RegisterUserModel): Observable<Object> {
    console.log(newUser);
    return this._http.post(`${environment.url}/registerUser`, newUser);
  }

  passwordReminder(newAuthenticationData: PasswordReminderModel): Observable<Object> {
      return this._http.patch(`${environment.url}/user/forgottenPassword`, newAuthenticationData);
  }

}
