import { Component } from '@angular/core';


@Component({
  selector: 'app-user-template',
  templateUrl: 'userTemplate.component.html',
  styleUrls: ['userTemplate.component.scss']
})
export class UserTemplateComponent {
  readBook: number = 2131;
  borrowedBook: number = 321;
  firstName: any;
}
