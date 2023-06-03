import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TestbuttonComponent } from './testbutton.component';

describe('TestbuttonComponent', () => {
  let component: TestbuttonComponent;
  let fixture: ComponentFixture<TestbuttonComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TestbuttonComponent]
    });
    fixture = TestBed.createComponent(TestbuttonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
