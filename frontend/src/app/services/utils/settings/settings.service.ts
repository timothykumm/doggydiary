import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SettingsService {

  private languageModels = ['gpt-3.5-turbo-16k', 'gpt-3.5-turbo', 'bard', 'gpt-4'];

  constructor() { }

  public getEmail(): string {
    return localStorage.getItem('email') || '';
  }

  public setEmail(email: string) {
    localStorage.setItem('email', email);
  }
  
  public getOpenai(): string {
    return localStorage.getItem('openai') ?? '';
  }

  public setOpenai(openai: string) {
    localStorage.setItem('openai', openai);
  }

  public getLanguageModel(): string  {
    return localStorage.getItem('languageModel') ?? this.languageModels[0];
  }

  public setLanguageModel(languageModel: string) {
    localStorage.setItem('languageModel', languageModel);
  }

  public getLanguageModelList(): string[] {
    return this.languageModels;
  }

}
