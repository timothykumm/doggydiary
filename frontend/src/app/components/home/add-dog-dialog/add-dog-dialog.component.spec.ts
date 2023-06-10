import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddDogDialogComponent } from './add-dog-dialog.component';

describe('AdddogdialogComponent', () => {
  let component: AddDogDialogComponent;
  let fixture: ComponentFixture<AddDogDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
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
