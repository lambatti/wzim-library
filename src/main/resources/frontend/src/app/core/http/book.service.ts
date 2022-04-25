import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Book } from '../../model/book.model';


@Injectable()
export class BookService {

  constructor(private _http: HttpClient) {
  }


  getAllBooks(): Observable<Book[]> {
    return this._http.get<Book[]>('https://wolnelektury.pl/api/books');
  }


}
