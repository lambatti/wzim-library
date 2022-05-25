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
  kindEpic: string = '';
  kindPoetry: string = '';
  kindDrama: string = '';

  ngOnInit(): void {
    this._bookRepository.getBooksToHomePageCard().subscribe(books => {
      books.forEach((item: BookCard) => {

        let [kind] = item.kinds;

        if (kind === 'Epika') {
          this.epic = [...this.epic,item];
          this.kindEpic = kind;
        }
        if (kind === 'Liryka') {
          this.poetry = [...this.poetry, item];
          this.kindPoetry = kind;
        }
        if (kind === 'Dramat') {
          this.drama = [...this.drama, item]
          this.kindDrama = kind;
        }
      })
    });
  }

  dodaj() {
    console.log('it works');
  }


}
