export class PasswordReminderModel {

  constructor(public email?: string,
              public securityQuestion?: string,
              public securityQuestionAnswer?: string,
              public newPassword?: string,
              public passwordConfirmation?: string) {
  }

}
