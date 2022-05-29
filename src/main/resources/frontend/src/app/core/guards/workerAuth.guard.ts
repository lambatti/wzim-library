import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../authentication/auth.service';


@Injectable()
export class WorkerAuthGuard implements CanActivate {

  constructor(private _auth: AuthService, private router: Router) {
  }

  canActivate(): boolean {
    if (!this._auth.isWorker()) {
      this.router.navigate(['/'])
      return false;
    }
    return true;
  }



}
