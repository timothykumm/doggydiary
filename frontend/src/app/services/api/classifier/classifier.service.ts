import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ClassificationDogResponse } from 'src/app/models/api/response/classification/ClassificationPostResponse';

@Injectable({
  providedIn: 'root'
})
export class ClassifierService {

  private url : string = "http://localhost:5000/upload";

  constructor(private http: HttpClient) { }

  getDogClassification(file: File): Observable<ClassificationDogResponse> {

    const formData = new FormData();
    formData.append('file', file);

    const httpOptions = {
      headers: new HttpHeaders({
       
      })
    };

    return this.http.post<ClassificationDogResponse>(this.url, formData, httpOptions)
  }

}
