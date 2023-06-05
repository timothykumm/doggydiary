import { Component } from '@angular/core';
import { DogGetResponse } from 'src/app/models/api/response/dog/DogGetResponse';

@Component({
  selector: 'ddr-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  //test data
  dogs: DogGetResponse[] = [
    {
      id: 1,
      name: "Cookie",
      breed: "Chihuahua",
      age: 5,
      img: "https://th.bing.com/th/id/R.5094323593019233df5977c369103a19?rik=hTA2HPudUs5n%2fw&pid=ImgRaw&r=0"
    },
    {
      id: 55,
      name: "Wuffi",
      breed: "Mops",
      age: 3,
      img: "https://th.bing.com/th/id/R.b0b9778fab7522705e19218f7c6d71af?rik=7BKbSu7d3DCvFQ&riu=http%3a%2f%2f3.bp.blogspot.com%2f-sWdCJqdgrbU%2fT_peu9rUdVI%2fAAAAAAAAESE%2fSpWF93ljOYs%2fs1600%2fBeautiful%2bdog%2bhd%2bWallpapers_1.jpg&ehk=iXTIeNG2nt9WY41dKkMKFAUS%2f%2bUJlX%2fAYigEyTvLQ7s%3d&risl=&pid=ImgRaw&r=0"
    },
    {
      id: 99,
      name: "Chaka",
      breed: "Husky",
      age: 2,
      img: "https://th.bing.com/th/id/R.edbc51064fdaab8691a7cad70a83b0b4?rik=bj5SOEUFx%2f8w6Q&pid=ImgRaw&r=0"
    },
    {
      id: 4,
      name: "Ulrich",
      breed: "Pudel",
      age: 7,
      img: "https://th.bing.com/th/id/OIP.cS39mgDb_fqP1V5fQmrK7QHaGc?pid=ImgDet&rs=1"
    },
    {
      id: 8,
      name: "Mylo",
      breed: "Schaeferhund",
      age: 3,
      img: "https://th.bing.com/th/id/OIP.TGRQhKxmzJwUCFa4xdIJFgHaFj?pid=ImgDet&rs=1"
    },
    {
      id: 20,
      name: "Fine",
      breed: "Labrador",
      age: 5,
      img: "https://th.bing.com/th/id/OIP.AScJKf8kOWJ59HXsCabMjwHaE8?pid=ImgDet&rs=1"
    }
    ]

    selectDog(dog: DogGetResponse): void {
      console.log('Name ' + dog.name);
      console.log('Alter ' + dog.age);
      console.log('Rasse ' + dog.breed);
    }

}
