import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Book } from '../../model/book.model';
import { BookRepository } from '../../core/services/book.repository';


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
  constructor(private readonly _router: Router, private readonly _bookRepository: BookRepository) {
  }

  ngOnInit(): void {
    this._bookRepository.getBookBySlug(this._router.url.split('/')[2]).subscribe(book => {
      this.bookData = book;
    });
  }

}
