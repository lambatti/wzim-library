import { FormControl } from '@angular/forms';

export class CustomFormControl extends FormControl {

  constructor(public label: string, public property: string, value: any, validator: any) {
    super(value, validator);
  }

  getValidationMessages(): string[] {
    let messages: string[] = [];
    if (this.errors) {
      for (let errorName in this.errors) {
        switch (errorName) {
          case 'required':
            messages.push(`To pole jest wymagane.`);
            break;
          case 'minlength':
            messages.push(`Minimalna liczba znaków to: ${this.errors['minlength'].requiredLength}.`);
            break;
          case 'maxlength':
            messages.push(`Maksymalna liczba znaków to: ${this.errors['maxlength'].requiredLength}.`);
            break;
          case 'pattern':
            messages.push(`Wprowadzone dane zawierają niedozwolone znaki.`);
            break;
        }
      }
    }

    return messages;
  }

}
