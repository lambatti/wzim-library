export class PasswordReminderModel {

  constructor(public email?: string,
              public question?: string,
              public answer?: string,
              public newPassword?: string,
              public newPasswordConfirmation?: string) {
  }

}
