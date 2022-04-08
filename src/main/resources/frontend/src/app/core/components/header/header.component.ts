import { Component, Input } from '@angular/core';
import { AuthService } from '../../authentication/auth.service';


@Component({
  selector: 'app-header',
  templateUrl: 'header.component.html',
  styleUrls: ['header.component.scss']
})

export class HeaderComponent {
  @Input() isRegister: boolean = false;
  public name: string = 'Tomek';

  constructor(public _auth: AuthService) {
  }

  get isAuth(): boolean {
    return this._auth.isAuthenticated();
  }


}
