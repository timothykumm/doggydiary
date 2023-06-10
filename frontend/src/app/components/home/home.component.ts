import { Component, OnInit } from '@angular/core';
import { DogGetResponse } from 'src/app/models/api/response/dog/DogGetResponse';
import { DogService } from 'src/app/services/api/dog/dog.service';
import { LoginService } from 'src/app/services/utils/login/login.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'ddr-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  dogs: DogGetResponse[] = [];

  constructor(private loginService: LoginService, private dogService: DogService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {

    this.loginService.onLoginStatusChange().subscribe((loggedIn: boolean) => {
          this.refreshDogList(loggedIn);
    })

    this.refreshDogList(this.loginService.isLoggedIn());
  }

  async refreshDogList(loggedIn: boolean) {
    if(loggedIn) {
      this.dogs = await this.getAllDogs();
    }else{
      this.dogs = [];
    }
  }

  async getAllDogs(): Promise<DogGetResponse[]> {
    return new Promise<DogGetResponse[]>((resolve, reject) => {
      this.dogService.getAllDogs().subscribe({
        next: (r) => { resolve(r); },
        error: (e) => { reject(e); }
      });
    });
  }

  addDogFromChildComponent(dog: DogGetResponse) {
    this.dogs = [...this.dogs, dog];
  }

  navigateToDog(dog: DogGetResponse) {
    this.router.navigate(['dog'], { queryParams: { id: dog.id } })
  }

}
