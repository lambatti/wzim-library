export class ChangePasswordModel {
  constructor(public oldPassword?: string, public newPassword?: string, public passwordConfirmation?: string) {
  }
}
