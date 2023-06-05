import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AuthenticationPostRequest } from 'src/app/models/api/request/authentication/AuthenticationPostRequest';
import { AuthService } from 'src/app/services/api/auth/auth.service';
import { JwtService } from 'src/app/services/utils/jwt/jwt.service';
import { LoginService } from 'src/app/services/utils/login/login.service';

@Component({
  selector: 'ddr-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  loggedIn = false;

  credentials: AuthenticationPostRequest = {
    email: '',
    password: ''
  }

  constructor(private loginService: LoginService) {}

  ngOnInit(): void {
    this.setLoginStatus(this.loginService.isLoggedIn());
  }

  toggleLoginModal(): void {
    const loginModal = document.getElementById("loginModal");
    if (loginModal != null) {
      loginModal.style.display === '' ? loginModal.style.display = 'block' : loginModal.style.display = '';
    }
  }

  async login() : Promise<void> {
    if(await this.loginService.login(this.credentials)) {
      this.toggleLoginModal();
      this.setLoginStatus(true);
      return;
    } 
  }

   logout() : void {
      this.loginService.logout();
      this.setLoginStatus(false);
  }

  setLoginStatus(status: boolean) {
    this.loggedIn = status;
  }

}
