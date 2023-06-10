import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtService } from '../../utils/jwt/jwt.service';
import { Observable } from 'rxjs/internal/Observable';
import { DogGetResponse } from 'src/app/models/api/response/dog/DogGetResponse';
import { DogPostRequest } from 'src/app/models/api/request/dog/DogPostRequest';

@Injectable({
  providedIn: 'root'
})
export class DogService {

  private url : string = "http://localhost:8081/api/v1/dogs";

  constructor(private http: HttpClient, private jwtService: JwtService) { }

  getAllDogs(): Observable<DogGetResponse[]> {

    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + this.jwtService.getAppTokenFromCookies()
      })
    };

    return this.http.get<DogGetResponse[]>(this.url, httpOptions)
  }

  createDog(dogPostRequest: DogPostRequest): Observable<string> {

    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + this.jwtService.getAppTokenFromCookies(),
        'Content-Type': 'application/json'
      })
    };

    return this.http.post(this.url, dogPostRequest, {...httpOptions, responseType: "text"})
  }

  postDogProfilePic(dogId: string, file: File): Observable<string> {

    let data = new FormData();
    data.append("file", file);

    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + this.jwtService.getAppTokenFromCookies()
      })
    };

    return this.http.post(this.url + "/" + dogId + "/profile", data, {...httpOptions, responseType: 'text'})
  }

}
