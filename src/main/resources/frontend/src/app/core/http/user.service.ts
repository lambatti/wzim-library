import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ShowedUserModel } from '../../model/user.model';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { UserBookStatus } from '../../model/book.model';
import { ChangePassword } from '../validators/changePassword.model';
import { ChangePasswordModel } from '../../model/changePassword.model';
import { ChangeQuestionModel } from '../../model/changeQuestion.model';


@Injectable()
export class UserService {

  constructor(private readonly _http: HttpClient) {
  }

  getUserData(): Observable<ShowedUserModel> {
    return this._http.get<ShowedUserModel>(`http://localhost:5000/user`, UserService.httpOptions());
  }

  getUserBooksCount(): Observable<UserBookStatus> {
    return this._http.get<UserBookStatus>(`http://localhost:5000/bookstatus`, UserService.httpOptions());
  }

  // CHANGE PASSWORD FROM PANEL ------ handle error
  changePassword(changedData: ChangePasswordModel): Observable<Object> {
    return this._http.patch<ChangePassword>(`${environment.url}/changePassword`, changedData, UserService.httpOptions());
  }

  // CHANGE VERIFICATION DATA ---- handle error
  changeQusetion(changedData: ChangeQuestionModel): Observable<Object> {
    return this._http.patch<ChangeQuestionModel>(`${environment.url}/changePassword`, changedData, UserService.httpOptions());
  }


  // BORROWED BOOKS USER  ----- basic get method

  // Verification questions

  private static httpOptions() {
    return {
      headers: new HttpHeaders({
        Authorization: `Bearer ${localStorage.getItem('token')}`
      })
    };
  }


}
