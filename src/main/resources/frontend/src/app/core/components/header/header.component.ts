import { Component } from '@angular/core';


@Component({
  selector: 'app-header',
  templateUrl: 'header.component.html',
  styleUrls: ['header.component.scss']
})

export class HeaderComponent {

  public isAuth: boolean = false;
  public name: string = 'Tomek';
}
