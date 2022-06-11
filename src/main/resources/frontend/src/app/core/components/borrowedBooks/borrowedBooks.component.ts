import { Component, OnInit } from '@angular/core';
import { UserRepository } from '../../services/user.repository';
import { BorrowedUserBooksDTO } from '../../../model/book.model';


@Component({
  selector: 'app-borrowedBooks',
  templateUrl: 'borrowedBooks.component.html',
  styleUrls: ['borrowedBooks.component.scss']
})
export class BorrowedBooksComponent implements OnInit{
  borrowedBooksList: BorrowedUserBooksDTO[] = [];
  selectedPage: number = 1;
  productPerPage: number = 3;
  total: number = 0;
  constructor(private readonly _userRepository: UserRepository) {
  }


  ngOnInit(): void {
    this._userRepository.borrowedBooks().subscribe(item => {
      this.borrowedBooksList = item;
      this.total = item.length;
    })
  }
  changePageIndex($event: number) {
    this.selectedPage = $event;
  }

  get books(): BorrowedUserBooksDTO[] {
    let pageIndex = (this.selectedPage-1)*this.productPerPage;
    return this.borrowedBooksList.slice(pageIndex, pageIndex + this.productPerPage);
  }

}
