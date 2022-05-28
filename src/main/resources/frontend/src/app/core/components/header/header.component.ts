import { Component, Input } from '@angular/core';
import { AuthService } from '../../authentication/auth.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-header',
  templateUrl: 'header.component.html',
  styleUrls: ['header.component.scss']
})

export class HeaderComponent {
  @Input() isRegister: boolean = false;
  public name: string = localStorage.getItem('firstname') as string;

  constructor(public _auth: AuthService, private router: Router) {
    console.log(this.name);
  }

  get isAuth(): boolean {
    return this._auth.isAuthenticated();
  }

  logout(): void {
    this._auth.logout();
    this.router.navigateByUrl('/');
  }


}
