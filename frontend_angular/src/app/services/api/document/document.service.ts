import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DocumentService {

  private url : string = "http://localhost:8081/api/v1/documents";

  constructor(private http: HttpClient) { }



}
