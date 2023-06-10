import { Component, EventEmitter, Input, Output } from '@angular/core';
import { DocumentPostRequest } from 'src/app/models/api/request/document/DocumentPostRequest';
import { DocumentGetResponse } from 'src/app/models/api/response/document/DocumentGetResponse';
import { DocumentService } from 'src/app/services/api/document/document.service';
import { createWorker, PSM, OEM } from 'tesseract.js';

@Component({
  selector: 'ddr-add-document-dialog',
  templateUrl: './add-document-dialog.component.html',
  styleUrls: ['./add-document-dialog.component.css']
})
export class AddDocumentDialogComponent {

  @Input() dogId!: number;
  @Output() createdDocument = new EventEmitter<DocumentGetResponse>;

  progressInPercent = 0;
  extractedText = '';
  file!: File;

  document: DocumentPostRequest = {
    title: '',
    content: '',
    dogId: 0
  }

  constructor(private documentService: DocumentService) {}

  fileChangeEvent(event: any) {
    this.file = event.target.files[0];
    this.extractText();
  }

  worker = createWorker({
    logger: m => this.progressInPercent = (m.progress * 100)
  })

  extractText = async () => {

    await this.worker.load()
    await this.worker.loadLanguage('deu')
    await this.worker.initialize('deu', OEM.LSTM_ONLY)

    await this.worker.setParameters({
      tessedit_pageseg_mode: PSM.SINGLE_BLOCK,
    })

    const { data } = await this.worker.recognize(this.file)

    this.extractedText = data.text
    this.progressInPercent = 0;
  
    //to-do
    this.document.title = "test"
    this.document.dogId = this.dogId;
    this.createDocument();
  }

  async createDocument() {
    const documentId = await this.createDocumentAndNothing();

    if(documentId !== undefined) {
    this.passDocumentToParentComponent(Number(documentId), new Date(), this.document.title, this.document.content);
  }

    //this.toggleAddDogModal();
  }

  async createDocumentAndNothing(): Promise<string> {
    return new Promise<string>((resolve, reject) => {
      this.documentService.createDocument(this.document).subscribe({
        next: (r) => { resolve(r); },
        error: (e) => { reject(e); }
      });
    });
  }

  passDocumentToParentComponent(documentId: number, date: Date, title: string, content: string) {

    const document: DocumentGetResponse = {
      id: documentId,
      date: date,
      title: title,
      content: content
      
    }

    this.createdDocument.emit(document);
  }

}
