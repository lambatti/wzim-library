import { Injectable } from '@angular/core';
import { ShowedUserModel } from '../../model/user.model';
import { UserService } from '../http/user.service';
import { Observable } from 'rxjs';



@Injectable()
export class UserRepository {

  constructor(private readonly _userService: UserService) {
  }


  showUserData(): Observable<ShowedUserModel> {
    return this._userService.getUserData();
  }

}
