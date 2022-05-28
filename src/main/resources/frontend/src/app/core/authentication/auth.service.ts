import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';


@Injectable()
export class AuthService {
  constructor(private jwtHelper: JwtHelperService) {
  }

  isAuthenticated(): boolean {
    const token: string = localStorage.getItem('token') as string;
    if (!this.jwtHelper.isTokenExpired(token)) {
      return !this.jwtHelper.isTokenExpired(token);
    } else {
      localStorage.removeItem('token');
      return false;
    }
  }
  isAdmin(): boolean {
    const token: string = localStorage.getItem('token') as string;
    if (!this.jwtHelper.isTokenExpired(token) && this.jwtHelper.decodeToken(token).role === 'ADMIN') {
      return !this.jwtHelper.isTokenExpired(token);
    }else  {
    localStorage.removeItem('token');
    return false;

    }


  }



  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('firstname');
  }
}
