import { Component } from '@angular/core';
import { AuthService } from '../../services/api/auth/auth.service';
import { AuthenticationRequest } from '../../models/auth/AuthenticationRequest';
import { JwtService } from '../../services/utils/jwt/jwt.service'

@Component({
  selector: 'ddr-testbutton',
  templateUrl: './testbutton.component.html',
  styleUrls: ['./testbutton.component.css']
})

export class TestbuttonComponent {

   credentials: AuthenticationRequest = {
    email: '',
    password: ''
  }

  constructor(private authService: AuthService, private jwtService: JwtService) {}

  authenticate() : void {
    this.authService.authenticate(this.credentials).subscribe({
      next: (r) => { this.jwtService.saveTokenInCookies(r) },
      error: (e) => { console.log(e) }
    })
  }

}
