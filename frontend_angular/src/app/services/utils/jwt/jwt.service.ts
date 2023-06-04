import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import jwt_decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class JwtService {

  constructor(private cookieService: CookieService) { }

  saveTokenInCookies(bearerToken : string) {
    const expirationDate = new Date();
    this.cookieService.set('jwt', bearerToken, new Date(this.#deocdeToken(bearerToken)['exp'] * 1000) ?? expirationDate.setHours(expirationDate.getHours()+24))
   }
  
  getTokenFromCookies() : string {
    return this.cookieService.get('jwt');
  }

  #deocdeToken(token: string) : any {
   return jwt_decode(token)
  }

}
