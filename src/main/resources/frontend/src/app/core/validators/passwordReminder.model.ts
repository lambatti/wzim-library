import { FormGroup, Validators } from '@angular/forms';
import { CustomFormControl } from './customFormControl.model';


export class PasswordReminder extends FormGroup {
  constructor() {
    super({
      email: new CustomFormControl('Email', 'email', '', Validators.compose([
        Validators.required,
        Validators.email
      ])),
      question: new CustomFormControl('Pytanie weryfikacyjne', 'securityQuestion', '', Validators.required),
      answer: new CustomFormControl('Odpowiedź', 'securityQuestionAnswer', '', Validators.compose([
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(30)
      ])),
      newPassword: new CustomFormControl('Nowe hasło', 'newPassword', '', Validators.compose([
        Validators.required,
        Validators.minLength(7),
        Validators.maxLength(30)
      ])),
      newPasswordConfirmation: new CustomFormControl('Potwierdź nowe hasło', 'passwordConfirmation', '', Validators.compose([
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
