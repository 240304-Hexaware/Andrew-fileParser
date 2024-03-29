import { Component, Input } from '@angular/core';
import { RequestService } from '../request.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-documents',
  standalone: true,
  imports: [],
  templateUrl: './documents.component.html',
  styleUrl: './documents.component.css'
})
export class DocumentsComponent {
  @Input() document!: {};
  stringified: string = "";

  constructor(private requestService: RequestService){
    //console.log(document);
    //this.stringified = JSON.stringify(Object.values(document));
  }
  ngAfterViewChecked(){
    //console.log("Document Child Component:",document);
  }


}
