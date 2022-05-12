import { Injectable } from '@angular/core';
import { BookService } from '../http/book.service';
import { BookCard } from '../../model/book.model';
import { Observable } from 'rxjs';


@Injectable()
export class BookRepository {

  constructor(private _bookService: BookService) {
  }


  // PROLONGACJA KSIĄŻKI

  // GET BOOKS TO HOME PAGE WITH PARAM x3

  getBooksToHomePageCard(kind: string): Observable<BookCard[]> {
    this._bookService.getBooksToHomePageCard(kind).subscribe(x => {
      console.log(x);
    });
    return this._bookService.getBooksToHomePageCard(kind as string);
  }

  // BORROW BOOK

  // GET ALL BOOK WITH CATEGORY
  // tu trzeba wyswietlać categorie po czym sortujemy

  // FIND ONE BOOK BY TEXT

  // GET BOOK DATA BY SLUG

  // GET ONE BOOK TEXT


}
