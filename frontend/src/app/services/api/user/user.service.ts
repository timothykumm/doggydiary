import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtService } from '../../utils/jwt/jwt.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url : string = "http://localhost:8081/api/v1/users";

  constructor(private http: HttpClient, private jwtService: JwtService) { }

  editApikey(apikey: string): Observable<any> {

    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + this.jwtService.getJwtFromCookies(),
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
      })
    };

  return this.http.put(this.url + "?apikey=" + apikey, null, httpOptions)
  }

}
