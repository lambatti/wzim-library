import { Component, OnInit } from '@angular/core';
import { ShowedUserModel } from '../../../model/user.model';
import { UserRepository } from '../../services/user.repository';


@Component({
  selector: 'app-user-data',
  templateUrl: 'userData.component.html',
  styleUrls: ['userData.component.scss']
})
export class UserDataComponent implements OnInit {


  constructor(private readonly _userRepository: UserRepository) {
  }

  userData: ShowedUserModel = {
    firstName: '',
    lastName: '',
    email: '',
    gender: '',
    creationDate: ''
  };

  ngOnInit(): void {
    this._userRepository.showUserData().subscribe((data) => {

      this.userData = data;

    });

  }
}

