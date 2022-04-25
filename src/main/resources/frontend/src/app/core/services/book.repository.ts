import { Injectable } from '@angular/core';
import { BookService } from '../http/book.service';
import { BookCard } from '../../model/book.model';
import { Observable } from 'rxjs';


@Injectable()
export class BookRepository {

  constructor(private _data: BookService) {
  }

  getBooksToCard(): Observable<BookCard[]> {
    return this._data.getAllBooks().pipe();
  }

}
