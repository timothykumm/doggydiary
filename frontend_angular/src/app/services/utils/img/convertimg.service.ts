import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConvertImgService {

  constructor() { }

   convertBase64ToMultipartFile(base64Image: string) : File{

    const byteString = atob(base64Image.split(',')[1]);
    const ab = new ArrayBuffer(byteString.length);
    const ia = new Uint8Array(ab);
  
    for (let i = 0; i < byteString.length; i++) {
      ia[i] = byteString.charCodeAt(i);
    }

    const file = new File([ab], 'image.jpg', { type: 'image/jpeg' });

    return file;
  }

}
