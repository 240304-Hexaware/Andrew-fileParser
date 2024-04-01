import { Component } from '@angular/core';
import { UserService } from '../user.service';
import { CommonModule } from '@angular/common';
import { SessionsComponent } from '../sessions/sessions.component';
import { Session } from '../Models/session';
import { Rmetadata } from '../Models/rmetadata';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-view-parsed',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, SessionsComponent],
  templateUrl: './view-parsed.component.html',
  styleUrl: './view-parsed.component.css'
})
export class ViewParsedComponent {

  sessions: Session[] = [];
  rmetadata: Rmetadata = {} as Rmetadata;
  documents = [];

  fullJson = "";
  constructor(private userService: UserService){
    this.sessions = userService.user.sessions;
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
      });
  }
}
