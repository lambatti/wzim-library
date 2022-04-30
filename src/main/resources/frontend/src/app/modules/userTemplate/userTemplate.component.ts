import { Component, OnInit } from '@angular/core';
import { UserBookStatus } from '../../model/book.model';
import { UserRepository } from '../../core/services/user.repository';


@Component({
  selector: 'app-user-template',
  templateUrl: 'userTemplate.component.html',
  styleUrls: ['userTemplate.component.scss']
})
export class UserTemplateComponent implements OnInit {

  constructor(private readonly _userRepository: UserRepository) {
  }


  booksStatus: UserBookStatus;

  firstName: any;

  ngOnInit(): void {
    this._userRepository.showUserBooksStatus().subscribe((data) => {
      this.booksStatus = data;
    });

  }
}
