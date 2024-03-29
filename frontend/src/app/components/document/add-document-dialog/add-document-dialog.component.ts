import { Component, EventEmitter, Input, Output } from '@angular/core';
import { DocumentPostRequest } from 'src/app/models/api/request/document/DocumentPostRequest';
import { ChatgptPostResponse } from 'src/app/models/api/response/chatgpt/ChatgptPostResponse';
import { DocumentGetResponse } from 'src/app/models/api/response/document/DocumentGetResponse';
import { ChatgptService } from 'src/app/services/api/chatgpt/chatgpt.service';
import { DocumentService } from 'src/app/services/api/document/document.service';
import { TensorflowService } from 'src/app/services/utils/tensorflow/tensorflow.service';
import { createWorker } from 'tesseract.js';

@Component({
  selector: 'ddr-add-document-dialog',
  templateUrl: './add-document-dialog.component.html',
  styleUrls: ['./add-document-dialog.component.css']
})
export class AddDocumentDialogComponent {

  @Input() dogId!: number;
  @Output() createdDocument = new EventEmitter<DocumentGetResponse>;

  progressInPercent = -1;
  progressText = ''
  file!: File;

  document: DocumentPostRequest = {
    title: '',
    content: '',
    dogId: 0
  }

  constructor(private documentService: DocumentService, private tensorflowService: TensorflowService, private chatgptService: ChatgptService) { }

  fileChangeEvent(event: any) {
    //read file
    this.file = event.target.files[0];
    this.progressInPercent = 0; //so that errors can be displayed to user

    if (!this.file || !(this.file.type.startsWith('image/jpeg') || this.file.type.startsWith('image/png'))) {
      this.progressText = 'File format not supported'
      return;
    }

    //create worker
    const worker = createWorker({
      logger: m => this.progressInPercent = (m.progress * 100)
    })

    this.progressText = 'Extracting Text from Image'

    //extract text and ask chatgpt
    this.tensorflowService.extractText(worker, this.file).then(async text => {

      if (text.length < 10) {
        this.#setProgressText('Extracted text length is too short')
        return;
      }

      this.#setProgressText('Asking ChatGPT')
      
      await this.getChatgptResponseApi(text).then(chatgptResponse => {
        this.filterDocument(chatgptResponse.choices[0].message.content);
        this.createDocument();

        //reset progress display loading symbol and text
        this.progressInPercent = -1
      }).catch(() => this.#setProgressText('Api key is probably invalid.'))

    }).catch(() => this.#setProgressText('Could not extract text from image'))
  }

  async createDocument() {
    const documentId = await this.createDocumentApi();

    if (documentId !== undefined) {
      this.passDocumentToParentComponent(Number(documentId), new Date(), this.document.title, this.document.content);
      this.resetDocument();
    }
  }

  #setProgressText(text: string) {
    this.progressText = text;
  }

  filterDocument(chatgptResponse: string) {
    const splittedResponse = chatgptResponse.split('\n')

    if (splittedResponse.length >= 2) {
      this.document.title = splittedResponse[0].replace('Title:', '');

      for (let i = 1; i < splittedResponse.length; i++) {
        this.document.content += splittedResponse[i].replace('Summary:', '').trimStart() + '\n'
      }

    } else {
      this.document.title = "Error in generating Headline";
      this.document.content = chatgptResponse;
    }

    this.document.dogId = this.dogId;
  }

  resetDocument() {
    this.document.title = '';
    this.document.content = '';
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

  async getChatgptResponseApi(input: string): Promise<ChatgptPostResponse> {
    return new Promise<ChatgptPostResponse>((resolve, reject) => {
      this.chatgptService.getResponseToQuestion(input).subscribe({
        next: (r) => { resolve(r); },
        error: (e) => { reject(e); }
      });
    });
  }

  async createDocumentApi(): Promise<string> {
    return new Promise<string>((resolve, reject) => {
      this.documentService.createDocument(this.document).subscribe({
        next: (r) => { resolve(r); },
        error: (e) => { reject(e); }
      });
    });
  }

}
