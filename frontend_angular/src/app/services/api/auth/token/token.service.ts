import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  private bearerToken: string = '';

  constructor() { }

  
  setToken(bearerToken : string) {
    this.bearerToken = bearerToken;
  }
  
  
  getToken() : string {
    return this.bearerToken;
  }
  
}
