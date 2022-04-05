import { Gender } from '../core/enums/genderEnum';


export class RegisterUserModel {
  constructor(
    public firstName?: string,
    public lastName?: string,
    public email?: string,
    public password?: string,
    public passwordConfirmation?: string,
    public gender?: Gender,
    public securityQuestion?: string,
    public securityQuestionAnswer?: string
  ) {
  }
}
