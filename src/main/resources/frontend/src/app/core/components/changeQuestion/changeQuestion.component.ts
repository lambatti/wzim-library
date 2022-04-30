import { Component } from '@angular/core';
import { ChangeQuestion } from '../../validators/changeQuestion.model';
import { ChangeQuestionModel } from '../../../model/changeQuestion.model';
import { UserRepository } from '../../services/user.repository';


@Component({
  selector: 'app-change-question',
  templateUrl: 'changeQuestion.component.html'
})
export class ChangeQuestionComponent {

  constructor(public readonly _userRepository: UserRepository) {
  }

  passwordVisible: boolean = false;
  isVisible: boolean = false;

  formGroup: ChangeQuestion = new ChangeQuestion();
  isSubmitted: boolean = false;
  changeQuestionModel: ChangeQuestionModel = new ChangeQuestionModel();

  submitForm(): void {

    if (this.formGroup.valid) {
      this._userRepository.changeQusetion(this.changeQuestionModel).subscribe(() => {
        this.isVisible = true;
      });
      this.isSubmitted = true;

      this.changeQuestionModel = new ChangeQuestionModel();
    }

    this.isSubmitted = false;
  }


  handleOk() {
    this.isVisible = false;
  }
}
