import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Book } from '../../model/book.model';
import { BookRepository } from '../../core/services/book.repository';
import { AuthService } from '../../core/authentication/auth.service';


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
              private readonly _auth: AuthService) {
  }

  handleOk = () => {
    this.isVisibleModal = false;
  };

  ngOnInit(): void {
    this.isAuthenticated = this._auth.isAuthenticated();
    this._bookRepository.getBookBySlug(this._router.url.split('/')[3]).subscribe(book => {
      this.bookData = book;
    });
  }

  borrow(slug: string) {
    this._bookRepository.borrowBookBySlug(slug).subscribe(() => {
      this.isVisibleModal = true
    }
    );

  }

}
