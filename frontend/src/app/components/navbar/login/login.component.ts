import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AuthenticationPostRequest } from 'src/app/models/api/request/authentication/AuthenticationPostRequest';
import { LoginService } from 'src/app/services/utils/login/login.service';
import { Mode } from '../../../models/api/request/authentication/MODE';

@Component({
  selector: 'ddr-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  loggedIn = false;
  droptownToggled = false;
  userAlreadyHasAccount: boolean = true; 
  @Output() toggleNavbar = new EventEmitter<void>;

  credentials: AuthenticationPostRequest = {
    email: '',
    forename: '',
    surname: '',
    password: '',
    openai: ''
  }

  constructor(private loginService: LoginService) {}

  ngOnInit(): void {
    this.setLoginStatus(this.loginService.isLoggedIn());

    this.loginService.onLoginStatusChange().subscribe((loggedIn: boolean) => {
      //if user deletes account, this needs to be reached from settings menu
      if(loggedIn == false) {
        this.setLoginStatus(loggedIn);
      }
    })
  }

  toggleLoginModal(): void {
    const loginModal = document.getElementById("loginModal");
    if (loginModal != null) {
      loginModal.style.display === '' ? loginModal.style.display = 'block' : loginModal.style.display = '';
    }
  }

  toggleDropdown(): void {
    this.droptownToggled = !this.droptownToggled;
  }

  async loginOrRegister(mode: Mode) : Promise<void> {
    if(await this.loginService.loginOrRegisterAndFetchJwt(this.credentials, mode)) {
      this.toggleLoginModal();
      this.setLoginStatus(true);
      this.toggleNavbarFromParentComponent();
      return;
    } 

    console.log("Could not authenticate")
  }

   logout() : void {
      this.loginService.logout();
      this.toggleNavbarFromParentComponent();
      this.toggleDropdown();
  }

  setLoginStatus(status: boolean) {
    this.loggedIn = status;
    this.loginService.setLoginStatus(status);
  }

  toggleNavbarFromParentComponent() {
    this.toggleNavbar.emit();
  }

}
