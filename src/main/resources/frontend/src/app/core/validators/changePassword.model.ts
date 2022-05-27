import { FormGroup, Validators } from '@angular/forms';
import { CustomFormControl } from './customFormControl.model';


export class ChangePassword extends FormGroup {

  constructor() {
    super({
      oldPassword: new CustomFormControl('Poprzednie hasło', 'oldPassword', '', Validators.compose([
        Validators.required,
        Validators.minLength(7),
        Validators.maxLength(30)
      ])),
      newPassword: new CustomFormControl('Nowe hasło', 'newPassword', '', Validators.compose([
        Validators.required,
        Validators.minLength(7),
        Validators.maxLength(30)
      ])),
      newPasswordConfirmation: new CustomFormControl('Powtórz nowe hasło', 'newPasswordConfirmation', '', Validators.compose([
        Validators.required,
        Validators.minLength(7),
        Validators.maxLength(30)
      ]))
    });
  }
  getValidationMessages(name: string): string[] {
    return (this.controls[name] as CustomFormControl).getValidationMessages();
  }
}
