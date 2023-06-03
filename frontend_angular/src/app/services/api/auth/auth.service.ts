import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthenticationRequest } from '../../../models/auth/AuthenticationRequest'
import { RegistraionRequest } from '../../../models/auth/RegistrationRequest'

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private url : string = "http://localhost:8081/api/v1/auth";

  constructor(private http: HttpClient) { }

  register(registraionRequest: RegistraionRequest): Observable<any> {

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      })
    };

    return this.http.post(this.url + "/register", registraionRequest, {...httpOptions, responseType: 'text' })
  }

  authenticate(authenticationRequest: AuthenticationRequest): Observable<any> {

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      })
    };

    return this.http.post(this.url + "/authenticate", authenticationRequest, {...httpOptions, responseType: 'text' })
  }

}
