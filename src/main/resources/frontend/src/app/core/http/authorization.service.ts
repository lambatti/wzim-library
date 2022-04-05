import { Injectable } from '@angular/core';
import { CustomSendRequest } from './CustomSendRequest';
import { Observable } from 'rxjs';
import { LoginUserModel } from '../../model/loginUser.model';


@Injectable()
export class AuthorizationService extends CustomSendRequest {

  login(user: LoginUserModel): Observable<LoginUserModel> {
    console.log(user);
    return this.sendRequest<LoginUserModel>('POST', '/api/login', user);
  }
}
