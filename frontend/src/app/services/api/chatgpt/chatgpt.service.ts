import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ChatgptPostRequest } from 'src/app/models/api/request/chatgpt/ChatgptPostRequest';
import { JwtService } from '../../utils/jwt/jwt.service';

@Injectable({
  providedIn: 'root'
})
export class ChatgptService {

  private url = 'https://api.openai.com/v1/chat/completions'
  
  constructor(private http: HttpClient, private jwtService: JwtService) { }

  getResponseToQuestion(chatgptRequest: ChatgptPostRequest): Observable<any> {

    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + this.jwtService.getOpenAiTokenFromCookies(),
        'Content-Type': 'application/json'
      })
    };

    return this.http.post(this.url, chatgptRequest, {...httpOptions, responseType: 'text' })
  }

}
