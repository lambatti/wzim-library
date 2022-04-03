import { Component } from '@angular/core';


@Component({
  selector: 'app-change-forget-password',
  templateUrl: 'changeForgetPassword.component.html'
})
export class ChangeForgetPasswordComponent {
  passwordVisible: boolean = false;
  confirmPasswordVisible: boolean = false;
}
