import { FormGroup, Validators } from '@angular/forms';
import { CustomFormControl } from './customFormControl.model';


export class ChangeQuestion extends FormGroup {

  constructor() {
    super({
      password: new CustomFormControl('Aktualne hasło', 'password', '', Validators.compose([
        Validators.required,
        Validators.minLength(7),
        Validators.maxLength(30)
      ])),
      securityQuestion: new CustomFormControl('Pytanie weryfikacyjne', 'newSecurityQuestion', '', Validators.required),
      securityQuestionAnswer: new CustomFormControl('Odpowiedź', 'newSecurityQuestionAnswer', '', Validators.compose([
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(30)
      ]))
    });
  }

}
