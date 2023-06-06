import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtService } from '../../utils/jwt/jwt.service';
import { Observable } from 'rxjs/internal/Observable';
import { DogGetResponse } from 'src/app/models/api/response/dog/DogGetResponse';

@Injectable({
  providedIn: 'root'
})
export class DogService {

  private url : string = "http://localhost:8081/api/v1/dogs";

  constructor(private http: HttpClient, private jwtService: JwtService) { }

  getAllDogs(): Observable<DogGetResponse[]> {

    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + this.jwtService.getTokenFromCookies()
      })
    };

    return this.http.get<DogGetResponse[]>(this.url, httpOptions)
  }

}
