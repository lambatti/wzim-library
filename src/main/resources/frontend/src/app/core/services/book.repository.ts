import { Injectable } from '@angular/core';
import { BookService } from '../http/book.service';
import { BookCard, BookCategory, Book } from '../../model/book.model';
import { Observable } from 'rxjs';


@Injectable()
export class BookRepository {


  constructor(private _bookService: BookService) {
  }


  // PROLONGACJA KSIĄŻKI



  getBooksToHomePageCard(): Observable<BookCard[]> {
    return this._bookService.getBooksToHomePageCard();
  }

  getBooksCategory(type: string): Observable<BookCategory[]> {
    if (type === 'genres') {
      return this._bookService.getBookGenres();
    }
    return this._bookService.getBookEpochs();


  }

  getBookBySlug(slug: string): Observable<Book> {
    return this._bookService.getBookBySlug(slug);
  }

  getBooksByCategory = (category: string, genres: boolean): Observable<BookCard[]> => {
    if (genres) {

    return this._bookService.getBooksByGenres(category);
    }
    return this._bookService.getBooksByEpochs(category);
  }
  // BORROW BOOK
  getBestBooks = ():Observable<BookCard[]> =>
      this._bookService.getBestBooks();
  // GET ALL BOOK WITH CATEGORY
  // tu trzeba wyswietlać categorie po czym sortujemy

  // FIND ONE BOOK BY TEXT

  // GET BOOK DATA BY SLUG

  // GET ONE BOOK TEXT


}
