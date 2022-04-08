import { Injectable } from '@angular/core';
import { AuthorizationService } from '../http/authorization.service';
import { LoginUserModel } from '../../model/loginUser.model';
import { Observable, throwError } from 'rxjs';
import { TokenModel } from '../../model/token.model';
import { catchError } from 'rxjs/operators';


@Injectable()
export class AuthenticationService {
  constructor(private _authorization: AuthorizationService) {
  }

  login(user: LoginUserModel): Observable<TokenModel> {
    return this._authorization.login(user).pipe(catchError(() => throwError(`Login lub hasło nie jest poprawne.`)));
  }

}
