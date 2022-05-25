import { Component, OnInit } from '@angular/core';
import { NzDrawerPlacement } from 'ng-zorro-antd/drawer';
import { BookRepository } from '../../core/services/book.repository';
import { BookCategory } from '../../model/book.model';


@Component({
  selector: 'app-book-categories',
  templateUrl: 'bookCategories.component.html',
  styleUrls: ['bookCategories.component.scss']
})
export class BookCategoriesComponent implements OnInit {
  visible = false;
  placement: NzDrawerPlacement = 'left';
  genres: BookCategory[] = []!;
  epochs: BookCategory[] = []!;

  constructor(private readonly _bookRepository: BookRepository) {
  }

  ngOnInit() {
    this._bookRepository.getBooksCategory('genres').subscribe((items: BookCategory[]) => {
      this.genres = items;
    })
    this._bookRepository.getBooksCategory('epochs').subscribe((items: BookCategory[]) => {
      this.epochs = items;
    })
  }

  open(): void {
    this.visible = true;
  }

  close(): void {
    this.visible = false;
  }

}
