import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DocumentGetResponse } from 'src/app/models/api/response/document/DocumentGetResponse';
import { JwtService } from '../../utils/jwt/jwt.service';
import { DocumentPostRequest } from 'src/app/models/api/request/document/DocumentPostRequest';

@Injectable({
  providedIn: 'root'
})
export class DocumentService {

  private url : string = "http://localhost:8081/api/v1/documents";

  constructor(private http: HttpClient, private jwtService: JwtService) { }

  getAllDocuments(dogId: number): Observable<DocumentGetResponse[]> {

    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + this.jwtService.getJwtFromCookies()
      })
    };

    return this.http.get<DocumentGetResponse[]>(this.url + "?dogId=" + dogId, httpOptions)
  }

  createDocument(documentPostRequest: DocumentPostRequest): Observable<string> {

    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + this.jwtService.getJwtFromCookies(),
        'Content-Type': 'application/json'
      })
    };

    return this.http.post(this.url, documentPostRequest, {...httpOptions, responseType: "text"})
  }

  editDocument(documentId: number, documentPostRequest: DocumentPostRequest): Observable<any> {

    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + this.jwtService.getJwtFromCookies(),
        'Content-Type': 'application/json'
      })
    };

  return this.http.put(this.url + "?documentId=" + documentId, documentPostRequest, httpOptions)
  }

}
