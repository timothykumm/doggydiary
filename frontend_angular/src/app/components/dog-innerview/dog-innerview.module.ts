import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddDocumentDialogComponent } from './add-document-dialog/add-document-dialog.component';
import { AddDogDialogComponent } from '../home/add-dog-dialog/add-dog-dialog.component';
import { DogInnerviewComponent } from './dog-innerview.component';


@NgModule({
  declarations: [
    DogInnerviewComponent,
    AddDocumentDialogComponent
  ],
  imports: [
    CommonModule
  ]
})
export class DogInnerviewModule { }
