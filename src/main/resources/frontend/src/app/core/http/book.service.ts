import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Book, BookCard } from '../../model/book.model';


@Injectable()
export class BookService {

  constructor(private _http: HttpClient) {
  }


  getAllBooks(): Observable<Book[]> {
    return this._http.get<Book[]>('https://wolnelektury.pl/api/books');
  }

  // PROLONGACJA KSIĄŻKI

  prolongationBoook(id: number): Observable<any> {

    return this._http.post(``, id);
  }


  // GET BOOKS TO HOME PAGE WITH PARAM x3

  getBooksToHomePageCard(kind: string): Observable<BookCard[]> {

    return this._http.get<BookCard[]>(`http://localhost:5000/fiveBook/${kind}`);

  }


  // BORROW BOOK

  // GET ALL BOOK WITH CATEGORY
  // tu trzeba wyswietlać categorie po czym sortujemy

  // FIND ONE BOOK BY TEXT

  // GET BOOK DATA BY SLUG

  // GET ONE BOOK TEXT


}
