import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthenticationPostRequest } from '../../../models/api/request/authentication/AuthenticationPostRequest'
import { RegistraionPostRequest } from '../../../models/api/request/authentication/RegistrationPostRequest'

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private url : string = "http://localhost:8081/api/v1/auth";

  constructor(private http: HttpClient) { }

  register(registraionRequest: RegistraionPostRequest): Observable<any> {

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      })
    };

    return this.http.post(this.url + "/register", registraionRequest, {...httpOptions, responseType: 'text' })
  }

  authenticate(authenticationRequest: AuthenticationPostRequest): Observable<any> {

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      })
    };

    return this.http.post(this.url + "/authenticate", authenticationRequest, {...httpOptions, responseType: 'text' })
  }

}
