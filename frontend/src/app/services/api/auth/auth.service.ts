import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthenticationPostRequest } from '../../../models/api/request/authentication/AuthenticationPostRequest'

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private url : string = "http://localhost:8081/api/v1/auth";

  constructor(private http: HttpClient) { }

  register(authenticationRequest: AuthenticationPostRequest): Observable<any> {

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      })
    };

    return this.http.post(this.url + "/register", authenticationRequest, {...httpOptions, responseType: 'text' })
  }

  login(authenticationRequest: AuthenticationPostRequest): Observable<any> {

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      })
    };

    return this.http.post(this.url + "/authenticate", authenticationRequest, {...httpOptions, responseType: 'text' })
  }

}
