import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import jwt_decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class JwtService {

  constructor(private cookieService: CookieService) { }

  saveAppTokenInCookies(authToken: string) {
    const expirationDate = new Date();
    this.cookieService.set('app-jwt', authToken, new Date(this.#deocdeToken(authToken)['exp'] * 1000) ?? expirationDate.setHours(expirationDate.getHours() + 24))
  }


  saveOpenAiTokenInCookies(authToken: string) {
    const expirationDate = new Date();
    this.cookieService.set('openai-jwt', authToken)
  }

  removeAppTokenInCookies() {
    this.cookieService.delete('app-jwt');
  }

  removeOpenAiTokenInCookies() {
    this.cookieService.delete('openai-jwt');
  }

  getAppTokenFromCookies(): string {
    return this.cookieService.get('app-jwt');
  }

  getOpenAiTokenFromCookies(): string {
    return this.cookieService.get('openai-jwt');
  }

  #deocdeToken(token: string): any {
    return jwt_decode(token)
  }

}
