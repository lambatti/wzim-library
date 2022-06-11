import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BorrowActionRequest } from '../../model/borrowOrProlongationActions.model';
import { environment } from '../../../environments/environment';


@Injectable()
export class WorkerService {


  constructor(private readonly _http: HttpClient) {
  }



  borrowRequest(): Observable<BorrowActionRequest[]> {
    return this._http.get<BorrowActionRequest[]>(`${environment.url}/bookBorrowRequests`, WorkerService.httpOptions())
  }

  acceptBorrowRequest(email: string, bookSlug: string): Observable<Object> {
    return this._http.post(`${environment.url}/bookBorrowRequests/accept`, {email,bookSlug}, WorkerService.httpOptions())
  }

  rejectBorrowRequest(email: string, bookSlug: string): Observable<Object> {
    return this._http.post(`${environment.url}/bookBorrowRequests/reject`, {email,bookSlug}, WorkerService.httpOptions())
  }

  prolongationRequest(): Observable<any> {
    return this._http.get<any>(`${environment.url}/bookBorrowProlongationRequests`, WorkerService.httpOptions())
  }

  acceptProlongationRequest(email: string, bookSlug: string): Observable<Object> {
    return this._http.post(`${environment.url}/bookBorrowProlongationRequests/accept`, {email,bookSlug}, WorkerService.httpOptions())
  }

  rejectProlongationRequest(email: string, bookSlug: string): Observable<Object> {
    return this._http.post(`${environment.url}/bookBorrowProlongationRequests/reject`, {email,bookSlug}, WorkerService.httpOptions())
  }



  private static httpOptions() {
    return {
      headers: new HttpHeaders({
        Authorization: `${localStorage.getItem('token')}`
      })
    };
  }

}
