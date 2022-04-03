import { Component } from '@angular/core';
import { ShowedUserModel } from '../../../model/user.model';


@Component({
  selector: 'app-user-data',
  templateUrl: 'userData.component.html',
  styleUrls: ['userData.component.scss']
})
export class UserDataComponent {
  userData: ShowedUserModel = {
    firstName: 'Tomek',
    lastName: 'Tomkowy',
    email: 'tomaszcomasz@gmail.com',
    gender: 'Menszczyna',
    creationDate: '20-12-2022'
  }
}
