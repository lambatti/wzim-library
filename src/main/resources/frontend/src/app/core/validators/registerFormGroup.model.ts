import { FormGroup, Validators } from '@angular/forms';
import { CustomFormControl } from './customFormControl.model';


export class RegisterFormGroup extends FormGroup {
  constructor() {
    super({
      firstName: new CustomFormControl('Imię', 'firstName', '', Validators.compose([
        Validators.required,
        Validators.pattern('^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.\'-]+$'),
        Validators.minLength(2),
        Validators.maxLength(20)
      ])),
      lastName: new CustomFormControl('Nazwisko', 'lastName', '', Validators.compose([
        Validators.required,
        Validators.pattern('^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.\'-]+$'),
        Validators.minLength(2),
        Validators.maxLength(40)
      ])),
      email: new CustomFormControl('Email', 'email', '', Validators.compose([
        Validators.required,
        Validators.email
      ])),
      password: new CustomFormControl('Hasło', 'password', '', Validators.compose([
        Validators.required,
        Validators.maxLength(30),
        Validators.minLength(7)
      ])),
      passwordConfirmation: new CustomFormControl('Powtórz hasło', 'passwordConfirmation', '', Validators.compose([
        Validators.required,
        Validators.maxLength(30),
        Validators.minLength(7)
      ])),
      gender: new CustomFormControl('Płeć', 'gender', '', Validators.required),
      securityQuestion: new CustomFormControl('Pytanie weryfikacyjne', 'securityQuestion', '', Validators.required),
      securityQuestionAnswer: new CustomFormControl('Odpowiedź', 'securityQuestionAnswer', '', Validators.compose([
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(30)
      ]))
    });
  }

  // get registerControls(): CustomFormControl[] {
  //   return Object.keys(this.controls).map(k => this.controls[k] as CustomFormControl);
  // }

  getValidationMessages(name: string): string[] {
    return (this.controls[name] as CustomFormControl).getValidationMessages();
  }

  // getFormValidationMessages(): string[] {
  //   let messages: string[] = [];
  //   Object.values(this.controls).forEach(c => messages.push(...(c as CustomFormControl).getValidationMessages()));
  //   return messages;
  // }

}
