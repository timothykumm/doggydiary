import { ChangeDetectionStrategy } from '@angular/compiler';
import { Component, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DocumentPostRequest } from 'src/app/models/api/request/document/DocumentPostRequest';
import { DocumentGetResponse } from 'src/app/models/api/response/document/DocumentGetResponse';
import { DocumentService } from 'src/app/services/api/document/document.service';
import { LoginService } from 'src/app/services/utils/login/login.service';

@Component({
  selector: 'ddr-document',
  templateUrl: './document.component.html',
  styleUrls: ['./document.component.css']
})
export class DocumentComponent implements OnInit {

dogId!: number;
//id of the document that is being edited (only one at a time)
documentBeingEdited = 0

documents: DocumentGetResponse[] = [];

constructor(private route: ActivatedRoute, private documentService: DocumentService, private loginService: LoginService) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.dogId = params['id'];
    })

    this.loginService.onLoginStatusChange().subscribe((loggedIn: boolean) => {
      this.refreshDocumentList(loggedIn) 
    })

    this.refreshDocumentList(this.loginService.isLoggedIn());
  }

  async refreshDocumentList(loggedIn: boolean) {
    if(loggedIn && this.dogId) {
      this.documents = await this.getAllDocuments(this.dogId);
    }else{
      this.documents = [];
    }
  }

  async getAllDocuments(dogid: number): Promise<DocumentGetResponse[]> {
    return new Promise<DocumentGetResponse[]>((resolve, reject) => {
      this.documentService.getAllDocuments(dogid).subscribe({
        next: (r) => { resolve(r); },
        error: (e) => { reject(e); }
      });
    });
  }

  async editDocument(documentId: number, document: DocumentPostRequest): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      this.documentService.editDocument(documentId, document).subscribe({
        next: (r) => { resolve(r); },
        error: (e) => { reject(e); }
      });
    });
  }

  addDocumentFromChildComponent(document: DocumentGetResponse) {
    this.documents = [...this.documents, document];
  }

  async toggleEdit(document: DocumentGetResponse, editableSpanTitle: string | null, editableSpanContent: string | null) {
    
    if(document.id === this.documentBeingEdited && editableSpanTitle && editableSpanContent) {
      //send put request to server
      const documentRequest: DocumentPostRequest = {
        title: editableSpanTitle,
        content: editableSpanContent,
        dogId: this.dogId
      }

      this.editDocument(document.id, documentRequest);
      
      this.documentBeingEdited = 0;
      return;
    }

    this.documentBeingEdited = document.id;
  }

  toggleDocument(document: DocumentGetResponse) {
    const mediaText = window.document.getElementById("media-text-" + document.id);
    const mediaBody = window.document.getElementById("media-body-" + document.id);
    const borderButtom = window.document.getElementById("border-bottom-" + document.id);

    if (mediaText != null && mediaBody != null && borderButtom) {
      mediaText.style.display === '' ? mediaText.style.display = 'block' : mediaText.style.display = '';
      mediaBody.style.display === '' ? mediaBody.style.display = 'block' : mediaBody.style.display = '';
      !borderButtom.classList.contains('border-bottom') ? borderButtom.classList.add('border-bottom') : borderButtom.classList.remove('border-bottom');
    }
  }

  isDocumentBeingEdited(document: DocumentGetResponse) {
    return document.id === this.documentBeingEdited;
  }

}
