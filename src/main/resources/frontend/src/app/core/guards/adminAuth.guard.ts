import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../authentication/auth.service';


@Injectable()
export class AdminAuthGuard implements CanActivate {


  constructor(private _auth: AuthService, private router: Router) {
  }
  canActivate(): boolean {
  if (!this._auth.isAdmin()) {
    this.router.navigate(['admin/auth'])
    return false;
  }
    return true;
  }

}
