import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Book, BookCard, BookCategory } from '../../model/book.model';
import { environment } from '../../../environments/environment';


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

  getBooksToHomePageCard = (): Observable<BookCard[]> =>
    this._http.get<BookCard[]>(`${environment.url}/books/home`);

  // BORROW BOOK

  // GET ALL BOOK WITH CATEGORY
  getBookGenres = (): Observable<BookCategory[]> =>
    this._http.get<BookCategory[]>(`${environment.url}/books/genres`);
  getBookEpochs = (): Observable<BookCategory[]> =>
    this._http.get<BookCategory[]>(`${environment.url}/books/epochs`);
  // tu trzeba wyswietlać categorie po czym sortujemy

  // FIND ONE BOOK BY TEXT

  // GET BOOK DATA BY SLUG

    getBookBySlug = (slug: string): Observable<Book> =>
      this._http.get<Book>(`${environment.url}/books/${slug}`);

  // GET ONE BOOK TEXT


}
