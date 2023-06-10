import { Injectable } from '@angular/core';
import { JwtService } from '../jwt/jwt.service';
import { AuthenticationPostResponse } from 'src/app/models/api/response/authentication/AuthenticationPostResponse';
import { AuthenticationPostRequest } from 'src/app/models/api/request/authentication/AuthenticationPostRequest';
import { AuthService } from '../../api/auth/auth.service';
import { Observable, Subject } from 'rxjs';
import { Mode } from 'src/app/models/api/request/authentication/MODE';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private loggedIn = new Subject<boolean>();

  authenticationPostResponse: AuthenticationPostResponse = {
    appToken: '',
    openai: ''
  }

  constructor(private authService: AuthService, private jwtService: JwtService) { }

  async loginOrRegisterAndFetchJwt(credentials: AuthenticationPostRequest, mode: Mode): Promise<boolean> {
    try { this.authenticationPostResponse = await this.authenticate(credentials, mode); }
    catch { }

    if (this.authenticationPostResponse.appToken.startsWith('ey')) {
      this.jwtService.saveAppTokenInCookies(this.authenticationPostResponse.appToken);
      this.jwtService.saveOpenAiTokenInCookies(this.authenticationPostResponse.openai);
      return true;
    }

    return false;
  }

  logout(): void {
    this.jwtService.removeAppTokenInCookies();
    this.jwtService.removeOpenAiTokenInCookies();
  }

  async authenticate(credentials: AuthenticationPostRequest, mode: Mode = Mode.LOGIN): Promise<AuthenticationPostResponse> {
    return new Promise<AuthenticationPostResponse>((resolve, reject) => {
      switch(mode) {
        
        case Mode.LOGIN:
          this.authService.login(credentials).subscribe({
            next: (r) => { resolve(r); },
            error: (e) => { reject(e); }
          })
          break;

          case Mode.REGISTER:
          this.authService.register(credentials).subscribe({
            next: (r) => { resolve(r); },
            error: (e) => { reject(e); }
          })
          break;
      }

    });

  }

  isLoggedIn(): boolean {
    return this.jwtService.getAppTokenFromCookies().startsWith('ey');
  }

  setLoginStatus(status: boolean): void {
    this.loggedIn.next(status);
  }

  onLoginStatusChange(): Observable<boolean> {
    return this.loggedIn.asObservable();
  }

}
