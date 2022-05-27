import { Component, OnInit } from '@angular/core';
import { NzDrawerPlacement } from 'ng-zorro-antd/drawer';
import { BookRepository } from '../../core/services/book.repository';
import { BookCard, BookCategory } from '../../model/book.model';
import { Router } from '@angular/router';


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
  allBooksByCategory: BookCard[] = []!;
  actualRoute: string = '' || "bestsellery";
  selectedPage: number = 1;
  productPerPage: number = 10;
  total: number = 0;
  constructor(private readonly _bookRepository: BookRepository, private readonly _router: Router) {
  }

  ngOnInit() {
    this._router.navigateByUrl('/category/')
    this._bookRepository.getBooksCategory('genres').subscribe((items: BookCategory[]) => {
      this.genres = items;
    })
    this._bookRepository.getBooksCategory('epochs').subscribe((items: BookCategory[]) => {
      this.epochs = items;
    })
    this._bookRepository.getBestBooks().subscribe(items => {
      this.allBooksByCategory = items;
      this.total = items.length;
      console.log(this.total);
    })
  }

  open(): void {
    this.visible = true;
  }

  close(): void {
    this.visible = false;
  }

  changePageIndex($event: number) {
    this.selectedPage = $event;
  }
  get books(): BookCard[] {
    let pageIndex = (this.selectedPage-1)*this.productPerPage;
    return this.allBooksByCategory.slice(pageIndex,pageIndex+this.productPerPage);
  }

  changeCategory(name: string): void {
    this.actualRoute = name;
    this._router.navigateByUrl(`/category/${name}`);
    this.allBooksByCategory = [];
    console.log(this.genres.some(x => x.name === name));
    if (this.genres.some(x => x.name === name)) {
    this._bookRepository.getBooksByCategory(name,true).subscribe(items => {
      console.log(this.actualRoute);
      this.allBooksByCategory = items;
      this.total = items.length;
    })
    }
    this._bookRepository.getBooksByCategory(name,false).subscribe(items => {
      console.log(this.actualRoute);
      this.allBooksByCategory = items;
      this.total = items.length;
    })
    this.close();
  }

}
