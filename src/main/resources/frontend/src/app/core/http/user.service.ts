import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ShowedUserModel } from '../../model/user.model';
// import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';


@Injectable()
export class UserService {

  constructor(private readonly _http: HttpClient) {
  }

  getUserData(): Observable<ShowedUserModel> {


   return this._http.get<ShowedUserModel>(`http://localhost:5000/user`);
  }



  // private static httpOptions() {
  //   return {
  //     headers: new HttpHeaders({
  //       Authorization: `Bearer ${localStorage.getItem('token')}`
  //     })
  //   };
  // }


}
