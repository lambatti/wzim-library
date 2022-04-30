import { FormGroup, Validators } from '@angular/forms';
import { CustomFormControl } from './customFormControl.model';


export class ChangePassword extends FormGroup {

  constructor() {
    super({
      oldPassword: new CustomFormControl('Poprzednie hasło', 'oldPassword', '', Validators.compose([
        Validators.required,
        Validators.minLength(7),
        Validators.minLength(30)
      ])),
      newPassword: new CustomFormControl('Nowe hasło', 'newPassword', '', Validators.compose([
        Validators.required,
        Validators.minLength(7),
        Validators.minLength(30)
      ])),
      passwordConfirmation: new CustomFormControl('Powtórz nowe hasło', 'passwordConfirmation', '', Validators.compose([
        Validators.required,
        Validators.minLength(7),
        Validators.minLength(30)
      ]))
    });
  }
}
