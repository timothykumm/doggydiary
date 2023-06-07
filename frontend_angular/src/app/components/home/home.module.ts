import { NgModule } from '@angular/core';
import { HomeComponent } from './home.component';
import { AddDogDialogComponent } from './adddogdialog/adddogdialog.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatNativeDateModule, MatRippleModule } from '@angular/material/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import { ImageCropperModule } from 'ngx-image-cropper';
import { FormsModule } from '@angular/forms';

@NgModule({
    declarations: [
        HomeComponent,
        AddDogDialogComponent
    ],
    imports: [
        CommonModule,
        FormsModule,
        BrowserAnimationsModule,
        MatDatepickerModule,
        MatInputModule,
        MatFormFieldModule,
        MatNativeDateModule,
        ImageCropperModule],

    providers: [],
    bootstrap: [HomeComponent],
    exports: [
        HomeComponent
    ]
  })
  export class HomeModule { }