import { Injectable } from '@angular/core';
import { AuthorizationService } from '../http/authorization.service';
import { LoginUserModel } from '../../model/loginUser.model';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { RegisterUserModel } from '../../model/registerUser.model';
import { TokenModel } from '../../model/token.model';


@Injectable()
export class AuthenticationService {
  constructor(private _authorization: AuthorizationService) {
  }

  login(user: LoginUserModel): Observable<TokenModel> {
   return this._authorization.login(user)
     .pipe(catchError(() => throwError(`Login lub hasło nie jest poprawne.`)));
  }


  register(newUser: RegisterUserModel): void {
    this._authorization.register(newUser)
      .pipe(catchError(() => throwError(``)))
      .subscribe();
  }

}
