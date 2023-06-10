import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DogInnerviewComponent } from './dog-innerview.component';

describe('DoginnerviewComponent', () => {
  let component: DogInnerviewComponent;
  let fixture: ComponentFixture<DogInnerviewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DogInnerviewComponent]
    });
    fixture = TestBed.createComponent(DogInnerviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
