import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginUserModel } from '../../model/loginUser.model';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { TokenModel } from '../../model/token.model';


@Injectable()
export class AuthorizationService {
  constructor(private http: HttpClient) {
  }

  login(user: LoginUserModel): Observable<TokenModel> {
    return this.http
      .post<TokenModel>(`${environment.url}/api/login`, user);
  }

}
