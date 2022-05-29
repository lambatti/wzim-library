import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UsersToPromotionModel } from '../../model/usersToPromotion.model';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';


@Injectable()
export class AdminService {

  constructor(private readonly _http: HttpClient) {
  }



  // GET ALL USERS TO PROMOTE

  getAllWorkers = (): Observable<UsersToPromotionModel[]> => {
   return this._http.get<UsersToPromotionModel[]>(`${environment.url}/user/workers`, AdminService.httpOptions())
  }







  private static httpOptions() {
    return {
      headers: new HttpHeaders({
        Authorization: `${localStorage.getItem('token')}`
      })
    };
  }

}
