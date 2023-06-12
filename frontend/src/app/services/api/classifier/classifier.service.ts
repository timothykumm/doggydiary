import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClassifierService {

  private url : string = "http://localhost:5000/upload";

  constructor(private http: HttpClient) { }

  getDogClassification(file: File): Observable<string> {

    const formData = new FormData();
    formData.append('file', file);

    const httpOptions = {
      headers: new HttpHeaders({
       
      })
    };

    return this.http.post(this.url, formData, {...httpOptions, responseType: "text"})
  }

}
