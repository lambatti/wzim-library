import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../authentication/auth.service';


@Injectable()
export class AuthGuard implements CanActivate {


  constructor(private _auth: AuthService, private router: Router) {
  }

  canActivate(): boolean {
    console.log(this._auth.isAuthenticated());
    if (!this._auth.isAuthenticated()) {
      this.router.navigate(['login']);
      return false;
    }
    return true;
  }
}
