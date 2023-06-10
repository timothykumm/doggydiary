import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddDocumentDialogComponent } from './add-document-dialog/add-document-dialog.component';
import { DocumentComponent } from './document.component';
import { FormsModule } from '@angular/forms';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


@NgModule({
  declarations: [
    DocumentComponent,
    AddDocumentDialogComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    MatProgressSpinnerModule,
    BrowserAnimationsModule
  ]
})
export class DocumentModule { }
