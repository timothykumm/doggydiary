import { Injectable } from '@angular/core';
import { JwtService } from '../jwt/jwt.service';
import { AuthenticationPostResponse } from 'src/app/models/api/response/authentication/AuthenticationPostResponse';
import { AuthenticationPostRequest } from 'src/app/models/api/request/authentication/AuthenticationPostRequest';
import { AuthService } from '../../api/auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  authenticationPostResponse: AuthenticationPostResponse = {
    token: ''
  }

  constructor(private authService: AuthService, private jwtService: JwtService) { }

  async login(credentials: AuthenticationPostRequest): Promise<boolean> {
    try { this.authenticationPostResponse.token = await this.authenticate(credentials); }
    catch { }

    if (this.authenticationPostResponse.token.startsWith('ey')) {
      this.jwtService.saveTokenInCookies(this.authenticationPostResponse.token);
      return true;
    }
    return false;
  }

  logout(): void {
    this.jwtService.removeTokenInCookies();
  }

  async authenticate(credentials: AuthenticationPostRequest): Promise<string> {
    return new Promise<string>((resolve, reject) => {
      this.authService.authenticate(credentials).subscribe({
        next: (r) => { resolve(r); },
        error: (e) => { reject(e); }
      });
    });
  }

  isLoggedIn(): boolean {
    return this.jwtService.getTokenFromCookies().startsWith('ey');
  }

}
