import { Component } from '@angular/core';
import { AuthenticationRequest } from 'src/app/models/auth/AuthenticationRequest';
import { AuthService } from 'src/app/services/api/auth/auth.service';
import { JwtService } from 'src/app/services/utils/jwt/jwt.service';

@Component({
  selector: 'ddr-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loggedIn = false;

  credentials: AuthenticationRequest = {
    email: '',
    password: ''
  }

  constructor(private authService: AuthService, private jwtService: JwtService) {}

  toggleModal(): void {
    const loginModal = document.getElementById("loginModal");
    if (loginModal != null) {
      loginModal.style.display === '' ? loginModal.style.display = 'block' : loginModal.style.display = '';
    }
  }

  async login() : Promise<void> {
    let authenticationResponse: string = '';
    try { authenticationResponse = await this.authenticate(); }
    catch { }


    if(authenticationResponse.startsWith('ey')) {
      this.jwtService.saveTokenInCookies(authenticationResponse);
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
