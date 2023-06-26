import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DocumentComponent } from './document.component';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { AddDocumentDialogComponent } from './add-document-dialog/add-document-dialog.component';

describe('DocumentComponent', () => {
  let component: DocumentComponent;
  let fixture: ComponentFixture<DocumentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        RouterTestingModule],
      declarations: [
        DocumentComponent,
      AddDocumentDialogComponent]
    });
    fixture = TestBed.createComponent(DocumentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
