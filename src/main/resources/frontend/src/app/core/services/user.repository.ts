import { Injectable } from '@angular/core';
import { ShowedUserModel } from '../../model/user.model';
import { UserService } from '../http/user.service';
import { Observable, throwError } from 'rxjs';
import { UserBookStatus } from '../../model/book.model';
import { catchError } from 'rxjs/operators';
import { ChangePasswordModel } from '../../model/changePassword.model';
import { ChangeQuestionModel } from '../../model/changeQuestion.model';


@Injectable()
export class UserRepository {

  constructor(private readonly _userService: UserService) {
  }


  showUserData(): Observable<ShowedUserModel> {
    return this._userService.getUserData();
  }

  showUserBooksStatus(): Observable<UserBookStatus> {
    return this._userService.getUserBooksCount();
  }


  changePassword(changedData: ChangePasswordModel): Observable<Object> {
    return this._userService.changePassword(changedData)
      .pipe(catchError(() => throwError(`Podane dane nie są poprawne`)));
  }

  changeQusetion(changedData: ChangeQuestionModel): Observable<Object> {
    return this._userService
      .changeQusetion(changedData)
      .pipe(catchError(() => throwError(`Podane dane nie są poprawne`)));
  }

}
