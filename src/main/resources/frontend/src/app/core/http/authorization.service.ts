import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginUserModel } from '../../model/loginUser.model';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { TokenModel } from '../../model/token.model';
import { RegisterUserModel } from '../../model/registerUser.model';


@Injectable()
export class AuthorizationService {
  constructor(private http: HttpClient) {
  }

  login(user: LoginUserModel): Observable<TokenModel> {
    return this.http
      .post<TokenModel>(`${environment.url}/login`, user);
  }

  register(newUser: RegisterUserModel): Observable<Object> {
    return this.http.post(`${environment.url}/register`, newUser);
  }

}
