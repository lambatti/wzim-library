import { Injectable } from '@angular/core';
import { BookService } from '../http/book.service';
import { BookCard } from '../../model/book.model';
import { Observable } from 'rxjs';


@Injectable()
export class BookRepository {

  constructor(private _data: BookService) {
  }

  // TEST URL ---- GET ALL BOOK FROM WOLNELEKTURY.PL
  getBooksToCard(): Observable<BookCard[]> {
    return this._data.getAllBooks().pipe();
  }


  // PROLONGACJA KSIĄŻKI

  // GET BOOKS TO HOME PAGE WITH PARAM x3

  // BORROW BOOK

  // GET ALL BOOK WITH CATEGORY
  // tu trzeba wyswietlać categorie po czym sortujemy

  // FIND ONE BOOK BY TEXT

  // GET BOOK DATA BY SLUG

  // GET ONE BOOK TEXT






}
