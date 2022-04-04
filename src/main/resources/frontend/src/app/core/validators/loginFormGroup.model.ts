import { FormGroup, Validators } from '@angular/forms';
import { CustomFormControl } from './customFormControl.model';


export class LoginFormGroup extends FormGroup {
  constructor() {
    super({
      email: new CustomFormControl('Email', 'email', '', Validators.compose([
        Validators.required,
        Validators.email
      ])),
      password: new CustomFormControl('Hasło', 'password', '', Validators.compose([
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
