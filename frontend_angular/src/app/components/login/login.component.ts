import { Component } from '@angular/core';
import { AuthenticationPostRequest } from 'src/app/models/api/request/authentication/AuthenticationPostRequest';
import { AuthenticationPostResponse } from 'src/app/models/api/response/authentication/AuthenticationPostResponse';
import { AuthService } from 'src/app/services/api/auth/auth.service';
import { JwtService } from 'src/app/services/utils/jwt/jwt.service';

@Component({
  selector: 'ddr-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loggedIn = false;

  credentials: AuthenticationPostRequest = {
    email: '',
    password: ''
  }

  authenticationPostResponse: AuthenticationPostResponse = {
    token: ''
  }

  constructor(private authService: AuthService, private jwtService: JwtService) {}

  toggleModal(): void {
    const loginModal = document.getElementById("loginModal");
    if (loginModal != null) {
      loginModal.style.display === '' ? loginModal.style.display = 'block' : loginModal.style.display = '';
    }
  }

  async login() : Promise<void> {
    try { this.authenticationPostResponse.token = await this.authenticate(); }
    catch { }


    if(this.authenticationPostResponse.token.startsWith('ey')) {
      this.jwtService.saveTokenInCookies(this.authenticationPostResponse.token);
      this.loggedIn = true;
      this.toggleModal();
      return;
    } 
  }

   logout() : void {
      this.jwtService.removeTokenInCookies();
      this.loggedIn = false;
  }

  async authenticate(): Promise<string> {
    return new Promise<string>((resolve, reject) => {
      this.authService.authenticate(this.credentials).subscribe({
        next: (r) => { resolve(r); },
        error: (e) => { reject(e); }
      });
    });
  }

}
