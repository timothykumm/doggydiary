import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import jwt_decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class JwtService {

  constructor(private cookieService: CookieService) { }

  saveJwtInCookies(authToken: string) {
    const expirationDate = new Date();
    this.cookieService.set('app-jwt', authToken, new Date(this.#deocdeToken(authToken)['exp'] * 1000) ?? expirationDate.setHours(expirationDate.getHours() + 24))
  }

  removJwtInCookies() {
    this.cookieService.delete('app-jwt');
  }

  getJwtFromCookies(): string {
    return this.cookieService.get('app-jwt');
  }

  extractClaim(claim: string) {
    return this.#deocdeToken(this.getJwtFromCookies())[claim] ?? undefined;
  }

  #deocdeToken(token: string): any {
    return jwt_decode(token)
  }

}
