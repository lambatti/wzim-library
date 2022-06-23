import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Book } from '../../model/book.model';
import { BookRepository } from '../../core/services/book.repository';
import { AuthService } from '../../core/authentication/auth.service';
import { UserRepository } from '../../core/services/user.repository';


@Component({
  selector: 'app-book-details',
  templateUrl: 'bookDetails.component.html',
  styleUrls: ['bookDetails.component.scss']
})
export class BookDetailsComponent implements OnInit{
  bookData: Book = {
    slug: '',
    title: '',
    language: '',
    epochs: [],
    genres: [],
    kinds: [],
    authors: [],
    translators: [],
    cover: '',
  };
  isAuthenticated: boolean;
  isVisibleModal: boolean = false;
  constructor(private readonly _router: Router,
              private readonly _bookRepository: BookRepository,
              private readonly _auth: AuthService,
              private readonly _userRepository: UserRepository
  ) {
  }

  handleOk = () => {
    this.isVisibleModal = false;
  };
  borrowedBooks: any = [];
  isBorrowedBool: boolean = false;
  ngOnInit(): void {
    this.isAuthenticated = this._auth.isAuthenticated();
    this._bookRepository.getBookBySlug(this._router.url.split('/')[3]).subscribe(book => {
      this.bookData = book;
    });
    this._userRepository.borrowedBooks().subscribe(item => {
      this.borrowedBooks = item;
    })
    this.borrowedBooks.forEach((i: any) => {
      if (i.slug === this.bookData.slug) {
        this.isBorrowedBool = true;
      }
    })
  }

  borrow(slug: string) {
    this._bookRepository.borrowBookBySlug(slug).subscribe(() => {
      this.isVisibleModal = true
    }
    );
  }
  prolongation(slug: string) {
    this._bookRepository.prolongationBook(slug).subscribe();
  }



}
