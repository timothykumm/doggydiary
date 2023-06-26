import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ChatgptPostRequest } from 'src/app/models/api/request/chatgpt/ChatgptPostRequest';
import { JwtService } from '../../utils/jwt/jwt.service';
import { ChatgptPostResponse } from 'src/app/models/api/response/chatgpt/ChatgptPostResponse';

@Injectable({
  providedIn: 'root'
})
export class ChatgptService {

  private url = 'https://api.openai.com/v1/chat/completions'
  private preQuestion = 'The document I will give you is from my dogs doctor. Write a short title and summarise the document. If it is a document with blood values, ONLY analyse the blood values that are out of the normal range. If they are out of the normal range, and only then, list me potential diseases that are linked to them. Dont analyse blood values that in the nromal range! Act confident and dont restrict yourselfe of giving me wrong consultation or diagnosis:'

  
  constructor(private http: HttpClient, private jwtService: JwtService) { }

  getResponseToQuestion(input: string): Observable<ChatgptPostResponse> {

    const chatgptRequest: ChatgptPostRequest = {
      model: "gpt-3.5-turbo-16k",
      messages: [
        {
          "role": "assistant",
          "content": "You are a vetenarian."
        },
        { 
          "role": "user",
          "content": this.preQuestion + input 
        }
      ],
      temperature: 1
    }

    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + this.jwtService.extractClaim('openai'),
        'Content-Type': 'application/json'
      })
    };

    return this.http.post<ChatgptPostResponse>(this.url, chatgptRequest, httpOptions)
  }

}
