import { Component, OnInit } from '@angular/core';
import { IShapes, shapes } from '../../utils/shapes';
import { BookRepository } from '../../core/services/book.repository';
import { BookCard } from '../../model/book.model';


@Component({
  selector: 'app-home',
  templateUrl: 'home.component.html',
  styleUrls: ['home.component.scss']

})
export class HomeComponent implements OnInit {
  constructor(private _bookRepository: BookRepository) {
  }

  shapes: IShapes = shapes;

  epic: BookCard[] = [];
  poetry: BookCard[] = [];
  drama: BookCard[] = [];


  ngOnInit(): void {
    this._bookRepository.getBooksToHomePageCard('Epika').subscribe(books => {
      this.epic = books;
    });
    this._bookRepository.getBooksToHomePageCard('Liryka').subscribe(books => {
      this.poetry = books;
    });
    this._bookRepository.getBooksToHomePageCard('Dramat').subscribe(books => {
      this.drama = books;
    });

  }

  dodaj() {
    console.log('it works');
  }


}
