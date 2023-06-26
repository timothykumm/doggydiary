import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddDogDialogComponent } from './add-dog-dialog.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ImageCropperModule } from 'ngx-image-cropper';
import { FormsModule } from '@angular/forms';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

describe('AddDogDialogComponent', () => {
  let component: AddDogDialogComponent;
  let fixture: ComponentFixture<AddDogDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        ImageCropperModule,
        FormsModule,
        BrowserAnimationsModule,
        MatDatepickerModule,
        MatInputModule,
        MatFormFieldModule,
        MatNativeDateModule],
      declarations: [AddDogDialogComponent]
    });
    fixture = TestBed.createComponent(AddDogDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
