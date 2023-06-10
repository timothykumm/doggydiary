import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddDocumentDialogComponent } from './add-document-dialog/add-document-dialog.component';
import { DocumentComponent } from './document.component';


@NgModule({
  declarations: [
    DocumentComponent,
    AddDocumentDialogComponent
  ],
  imports: [
    CommonModule
  ]
})
export class DocumentModule { }
