import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthenticationPostRequest } from '../../../models/api/request/authentication/AuthenticationPostRequest'
import { AuthenticationPostResponse } from 'src/app/models/api/response/authentication/AuthenticationPostResponse';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private url : string = "http://localhost:8081/api/v1/auth";

  constructor(private http: HttpClient) { }

  register(authenticationRequest: AuthenticationPostRequest): Observable<AuthenticationPostResponse> {

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      })
    };

    return this.http.post<AuthenticationPostResponse>(this.url + "/register", authenticationRequest, httpOptions)
  }

  login(authenticationRequest: AuthenticationPostRequest): Observable<AuthenticationPostResponse> {

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      })
    };

    return this.http.post<AuthenticationPostResponse>(this.url + "/authenticate", authenticationRequest, httpOptions)
  }

}
