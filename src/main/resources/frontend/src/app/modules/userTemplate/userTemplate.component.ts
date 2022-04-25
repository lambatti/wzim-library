import { Component } from '@angular/core';
import { UserBookStatus } from '../../model/book.model';


@Component({
  selector: 'app-user-template',
  templateUrl: 'userTemplate.component.html',
  styleUrls: ['userTemplate.component.scss']
})
export class UserTemplateComponent {




  booksStatus: UserBookStatus = {
    booksRead: 2131,
    borrowedBooks: 321
  };


  firstName: any;
}
