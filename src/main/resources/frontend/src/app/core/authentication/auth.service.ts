import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';


@Injectable()
export class AuthService {
  constructor(private jwtHelper: JwtHelperService) {
  }

  isAuthenticated(): boolean {
    const token: string = localStorage.getItem('token') as string;
    if (!this.jwtHelper.isTokenExpired(token)) {
      localStorage.setItem('firstname',this.jwtHelper.decodeToken(token).name);
      return !this.jwtHelper.isTokenExpired(token);
    } else {
      localStorage.removeItem('token');
      return false;
    }
  }

  logout(): void {
    localStorage.removeItem('token');
  }
}
