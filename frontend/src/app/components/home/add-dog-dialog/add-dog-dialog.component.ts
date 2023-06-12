import { Component, EventEmitter, Output } from '@angular/core';
import { ImageCroppedEvent, LoadedImage } from 'ngx-image-cropper';
import { DogPostRequest } from 'src/app/models/api/request/dog/DogPostRequest';
import { DogGetResponse } from 'src/app/models/api/response/dog/DogGetResponse';
import { ClassifierService } from 'src/app/services/api/classifier/classifier.service';
import { DogService } from 'src/app/services/api/dog/dog.service';
import { ConvertImgService } from 'src/app/services/utils/img/convertimg.service';

@Component({
  selector: 'ddr-add-dog-dialog',
  templateUrl: './add-dog-dialog.component.html',
  styleUrls: ['./add-dog-dialog.component.css']
})

export class AddDogDialogComponent {

  imageChangedEvent: any = '';
  croppedImage: any = '';

  @Output() createdDog = new EventEmitter<DogGetResponse>;

  dog: DogPostRequest = {
    name: '',
    breed: '',
    birthdate: new Date()
  }

constructor(private dogService: DogService, private classifierService: ClassifierService, private convertImgService: ConvertImgService) {}

  toggleAddDogModal(): void {
    const addDogModal = document.getElementById("addDogModal");
    if (addDogModal != null) {
      addDogModal.style.display === '' ? addDogModal.style.display = 'block' : addDogModal.style.display = '';
    }
  }

  fileChangeEvent(event: any): void {
    this.imageChangedEvent = event;
  }
  imageCropped(event: ImageCroppedEvent) {
    this.croppedImage = event.base64;
  }
  imageLoaded(image: LoadedImage) {
    // show cropper
  }
  cropperReady() {
    // cropper ready
  }
  loadImageFailed() {
    // show message
  }

  async createDogAndUploadPic() {
    const dogId = await this.createDog();

    if(dogId !== undefined && this.croppedImage !== '') {
    const file: File = this.convertImgService.convertBase64ToMultipartFile(this.croppedImage);
    const img = await this.uploadProfilePic(dogId, file);
    this.passDogToParentComponent(Number(dogId), this.dog.name, this.dog.breed, this.dog.birthdate, img);
    console.log(img)
  }
    this.toggleAddDogModal();
  }

  async detectDogBreed() {
    if(this.croppedImage !== '') {
      const file: File = this.convertImgService.convertBase64ToMultipartFile(this.croppedImage);
      const classifiedDog = await this.classifyDog(file);
      console.log('Classified Dog: ' + classifiedDog)
      this.dog.breed = classifiedDog;
    }
  }

  async createDog(): Promise<string> {
    return new Promise<string>((resolve, reject) => {
      this.dogService.createDog(this.dog).subscribe({
        next: (r) => { resolve(r); },
        error: (e) => { reject(e); }
      });
    });
  }

  passDogToParentComponent(dogId: number, name: string, breed: string, birthdate: Date, img: string) {

    const dog: DogGetResponse = {
      id: dogId,
      name: name,
      breed: breed,
      birthdate: birthdate,
      img: img
    }

    this.createdDog.emit(dog);
  }

  async uploadProfilePic(dogId: string, file: File): Promise<any> {
    return new Promise<string>((resolve, reject) => {
      this.dogService.postDogProfilePic(dogId, file).subscribe({
        next: (r) => { resolve(r); },
        error: (e) => { reject(e); }
      });
    });
  }

  async classifyDog(file: File): Promise<any> {
    return new Promise<string>((resolve, reject) => {
      this.classifierService.getDogClassification(file).subscribe({
        next: (r) => { resolve(r); },
        error: (e) => { reject(e); }
      });
    });
  }

}
