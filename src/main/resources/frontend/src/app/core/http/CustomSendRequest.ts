import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';


export abstract class CustomSendRequest {


  constructor(private http: HttpClient) {
  }

  protected sendRequest<T>(verb: string, url: string, body?: Object): Observable<T> {
    return this.http.request<T>(verb, url, {
      body: body
    });
  }
}
