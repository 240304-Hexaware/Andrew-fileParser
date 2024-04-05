import { Component } from '@angular/core';
import { UserService } from '../user.service';
import { CommonModule } from '@angular/common';
import { SessionsComponent } from '../sessions/sessions.component';
import { Session } from '../Models/session';
import { Rmetadata } from '../Models/rmetadata';
import { ReactiveFormsModule } from '@angular/forms';
//import { TableComponent } from '../table/table.component';
import { ValueComponent } from '../value/value.component';
import { User } from '../Models/user';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-view-parsed',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, SessionsComponent, ValueComponent],
  templateUrl: './view-parsed.component.html',
  styleUrl: './view-parsed.component.css'
})
export class ViewParsedComponent {

  //userObservable: Observable<User>;

  sessions: Session[] = [];
  rmetadata: Rmetadata = {} as Rmetadata;
  clickedSession: Session = {} as Session;
  documents = [];
  //_userService: UserService;


  tempId = 0;
  viewCase: number = 0;
  refreshPlease(num: number){
    this.viewCase = num;
  }

  fullJson = "";
  constructor(private userService: UserService){
    this.sessions = userService.user.sessions;
    //this._userService = userService;
    /*this.userObservable = this.userService.userObservable
      .subscribe((user) => {
        this.sessions = userService.user.sessions;
      });*/
  }

  ngOnChange(){
    this.sessions = this.userService.user.sessions;
  }

  toJson(){
    this.fullJson = "";
    //console.log("this.documents.length:",this.documents.length);
    let i = 0;
    while(this.documents.length != 0 && i < this.documents.length){
      this.fullJson += JSON.stringify(this.documents[i++]);
    }
    console.log(this.fullJson);
  }

  transferDocs(emittedObs: any){
    console.log("transferDocs");
    emittedObs
      .subscribe((docsArray: any) => {
        console.log("Recieved docs...", docsArray);
        this.documents = docsArray;
        this.toJson();
        this.tempId = 0;
      });
  }

  displayFlatName():string{
    if(!this.rmetadata.flatfilePath){
      return "";
    }
    let fullPath:string = this.rmetadata.flatfilePath;
    let lastSlash:number = fullPath.lastIndexOf("\\");
    return fullPath.substring(lastSlash+1, fullPath.length);
  }
  displaySpecName():string{
    if(!this.rmetadata.specfilePath){
      console.log("Returned empty String");
      return "";
    }
    let fullPath:string = this.rmetadata.specfilePath;
    let lastSlash:number = fullPath.lastIndexOf("\\");
    return fullPath.substring(lastSlash+1, fullPath.length);
  }

  transferMetadata(emittedObs: any){
    console.log("Transfer Metadata", emittedObs);
    emittedObs
      .subscribe((rmetadata: Rmetadata) => {
        console.log("Recieved metadata");
        //this.clickedSession = rmetadata;
        this.rmetadata = rmetadata;
      })
  }

  /*showFormat(elem:any){
    const dom = window.document.getElementById("formatButton");
    if(!(dom === null)){
      dom.innerHTML = elem.innerHTML;
    }
  }*/



  showFormatJSON(){
    const dom = window.document.getElementById("formatButton");
    if(!(dom === null)){
      dom.innerHTML = "Table";
      this.viewCase = 1;
    }
  }
  showFormatTable(){
    const dom = window.document.getElementById("formatButton");
    if(!(dom === null)){
      dom.innerHTML = "JSON";
      this.viewCase = 0;
    }
  }
  getViewCase(){
    return this.viewCase;
  }
  /*getViewCase() :number
  {
    let dom = (document.getElementById("formatViewSelect")) as  HTMLSelectElement;
    if(dom !== null){
      return parseInt(dom.value);
    }
    return 0;
  }*/
  keyify(){
    return Object.keys(this.documents[0]);
  }
  valuify(entry: any){
    return Object.values(entry);
  }
  tempIdIncr(){
    return this.tempId++;
  }

}
