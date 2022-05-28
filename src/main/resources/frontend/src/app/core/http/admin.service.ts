import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Injectable()
export class AdminService {

  constructor(private readonly _http: HttpClient) {
  }



  // GET ALL USERS TO PROMOTE

  getAllUsers = () => {
   // return this._http.get()
  }







  // private static httpOptions() {
  //   return {
  //     headers: new HttpHeaders({
  //       Authorization: `${localStorage.getItem('token')}`
  //     })
  //   };
  // }

}
