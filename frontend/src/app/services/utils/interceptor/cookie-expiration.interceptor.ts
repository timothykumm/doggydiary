import { Injectable } from '@angular/core';
import { JwtService } from '../jwt/jwt.service';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpEventType
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class CookieExpirationInterceptor implements HttpInterceptor {

  constructor(private jwtService: JwtService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    if(!this.jwtService.getAppTokenFromCookies().startsWith('ey') && !request.url.includes('authenticate') && !request.url.includes('register')) {
      //jwt missing - probably expired
      return new Observable<HttpEvent<5>>;
    } 

    return next.handle(request);
  }
}
