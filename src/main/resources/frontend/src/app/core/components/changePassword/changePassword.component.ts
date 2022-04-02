import { Component } from '@angular/core';


@Component({
  selector: 'app-change-password',
  templateUrl: 'changePassword.component.html'
})
export class ChangePasswordComponent {
  oldPasswordVisible: boolean = false;
  passwordVisible: boolean = false;
  confirmPasswordVisible: boolean = false;
}
