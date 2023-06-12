import { Component, EventEmitter, Input, Output } from '@angular/core';
import { DocumentPostRequest } from 'src/app/models/api/request/document/DocumentPostRequest';
import { ChatgptPostResponse } from 'src/app/models/api/response/chatgpt/ChatgptPostResponse';
import { DocumentGetResponse } from 'src/app/models/api/response/document/DocumentGetResponse';
import { ChatgptService } from 'src/app/services/api/chatgpt/chatgpt.service';
import { DocumentService } from 'src/app/services/api/document/document.service';
import { TensorflowService } from 'src/app/services/utils/tensorflow/tensorflow.service';
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
  file!: File;

  document: DocumentPostRequest = {
    title: '',
    content: '',
    dogId: 0
  }

  constructor(private documentService: DocumentService, private tensorflowService: TensorflowService, private chatgptService: ChatgptService) {}

  fileChangeEvent(event: any) {
    //read file
    this.file = event.target.files[0];
    if(!this.file) return

    //create worker
    const worker = createWorker({
      logger: m => this.progressInPercent = (m.progress * 100)
    })

    //extract text and askgpt
    this.tensorflowService.extractText(worker, this.file).then(async text => {
      const chatgptResponse = await this.getChatgptResponse(text);

      this.document.title = "In Arbeit";
      this.document.content = chatgptResponse.choices[0].message.content;
      this.document.dogId = this.dogId;

      console.log(this.document)
      console.log(chatgptResponse);

      this.createDocument();
      this.progressInPercent = 0;
    });
  }

  async getChatgptResponse(input: string): Promise<ChatgptPostResponse> {
    return new Promise<ChatgptPostResponse>((resolve, reject) => {
      this.chatgptService.getResponseToQuestion(input).subscribe({
        next: (r) => { resolve(r); },
        error: (e) => { reject(e); }
      });
    });
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
