import { Component, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DocumentGetResponse } from 'src/app/models/api/response/document/DocumentGetResponse';
import { DocumentService } from 'src/app/services/api/document/document.service';
import { LoginService } from 'src/app/services/utils/login/login.service';

@Component({
  selector: 'ddr-document',
  templateUrl: './document.component.html',
  styleUrls: ['./document.component.css']
})
export class DocumentComponent implements OnInit {

documents: DocumentGetResponse[] = [];

dogId!: number;

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

  addDocumentFromChildComponent(document: DocumentGetResponse) {
    this.documents = [...this.documents, document];
  }

  selectDocument(document: DocumentGetResponse) {
    console.log(document.content)
  }

}
