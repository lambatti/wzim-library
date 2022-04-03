import { Component } from '@angular/core';
import { ShowedUserModel } from '../../../model/user.model';


@Component({
  templateUrl: 'addWorker.component.html',

})
export class AddWorkerComponent {
  isVisible!: boolean;
  // TODO dane tylko statyczne


  user: ShowedUserModel = {
    firstName: 'Tomasz',
    lastName: 'Comasz',
    email: 'tomaszcomasz@gmail.com',
    gender: 'Mężczyzna',
    creationDate: '10-10-2017'
  };
  validateForm: any;

  showModal(id: number): void {
    console.log(id);
    this.isVisible = true;
  }

  handleOk(): void {
    this.isVisible = false;
  }

  handleCancel(): void {
    this.isVisible = false;
  }

  submitForm() {

  }
}
