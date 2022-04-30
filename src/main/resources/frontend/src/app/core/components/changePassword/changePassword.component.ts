import { Component } from '@angular/core';
import { ChangePassword } from '../../validators/changePassword.model';
import { ChangePasswordModel } from '../../../model/changePassword.model';
import { UserRepository } from '../../services/user.repository';


@Component({
  selector: 'app-change-password',
  templateUrl: 'changePassword.component.html'
})
export class ChangePasswordComponent {

  constructor(private readonly _userRepository: UserRepository) {
  }

  oldPasswordVisible: boolean = false;
  passwordVisible: boolean = false;
  confirmPasswordVisible: boolean = false;

  formGroup: ChangePassword = new ChangePassword();
  changePasswordModel: ChangePasswordModel = new ChangePasswordModel();
  formSubmitted: boolean = false;

  submitForm(): void {

    // @ts-ignore
    Object.keys(this.formGroup.controls).forEach(c => this.changePasswordModel[c] = this.formGroup.controls[c].value);
    this.formSubmitted = true;

    if (this.formGroup.valid) {
      this._userRepository.changePassword(this.changePasswordModel).subscribe();
    }
  }


}
