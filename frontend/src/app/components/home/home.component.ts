import { Component, OnInit } from '@angular/core';
import { DogGetResponse } from 'src/app/models/api/response/dog/DogGetResponse';
import { DogService } from 'src/app/services/api/dog/dog.service';
import { LoginService } from 'src/app/services/utils/login/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'ddr-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  dogs: DogGetResponse[] = [];

  constructor(private loginService: LoginService, private dogService: DogService, private router: Router) { }

  ngOnInit(): void {

    this.loginService.onLoginStatusChange().subscribe((loggedIn: boolean) => {
          this.getAllDogs(loggedIn);
    })

    this.getAllDogs(this.loginService.isLoggedIn());
  }

  async getAllDogs(loggedIn: boolean) {
    if(loggedIn) {
      this.dogs = await this.getAllDogsApi();
    }else{
      this.dogs = [];
    }
  }

  addDogFromChildComponent(dog: DogGetResponse) {
    this.dogs = [...this.dogs, dog];
  }

  navigateToDog(dog: DogGetResponse) {
    this.router.navigate(['dog'], { queryParams: { id: dog.id } })
  }

  async getAllDogsApi(): Promise<DogGetResponse[]> {
    return new Promise<DogGetResponse[]>((resolve, reject) => {
      this.dogService.getAllDogs().subscribe({
        next: (r) => { resolve(r); },
        error: (e) => { reject(e); }
      });
    });
  }

}
