import { Component } from '@angular/core';
import { NzDrawerPlacement } from 'ng-zorro-antd/drawer';


@Component({
  selector: 'app-book-categories',
  templateUrl: 'bookCategories.component.html',
  styleUrls: ['bookCategories.component.scss']
})
export class BookCategoriesComponent {
  visible = false;
  placement: NzDrawerPlacement = 'left';
  open(): void {
    this.visible = true;
  }

  close(): void {
    this.visible = false;
  }
}
