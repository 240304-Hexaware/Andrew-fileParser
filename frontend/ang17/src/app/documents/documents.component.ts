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
  @Input() document!: any;
  stringified: string = "";
  

  constructor(private requestService: RequestService){
    console.log(JSON.stringify(this.document));
    this.stringified = JSON.stringify(this.document);
    //this.stringified = JSON.stringify(Object.values(document));
  }
  ngAfterViewChecked(){
    //console.log("Document Child Component:",document);
  }

  generalizeDoc(){
    //Get Keys to the document to display information
    //let keys = Object.keys(this.document);
    //for(let key in keys){

    //}
    
  }


}
