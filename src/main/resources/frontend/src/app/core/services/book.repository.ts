import { Injectable } from '@angular/core';
import { BookService } from '../http/book.service';
import { BookCard, BookCategory, Book } from '../../model/book.model';
import { Observable, throwError } from 'rxjs';
import { UserService } from '../http/user.service';
import { catchError } from 'rxjs/operators';


@Injectable()
export class BookRepository {


  constructor(private _bookService: BookService, private _userService: UserService) {
  }


  // PROLONGACJA KSIĄŻKI

  prolongationBook(slug: string): Observable<Object> {
    return this._userService
      .prolongationBook(slug)
      .pipe(catchError(()=> throwError(`Nie masz wypożyczonej książki`)));
  }

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
  borrowBookBySlug(slug: string): Observable<Object> {
    return this._userService.borrowBook(slug)
      .pipe(catchError(() => throwError('Książka została już wypożycznona lub czeka na potwierdzenie')))
  }

  getBestBooks = ():Observable<BookCard[]> =>
      this._bookService.getBestBooks();



}
