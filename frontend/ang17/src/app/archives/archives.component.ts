import { Component, inject } from '@angular/core';
import { RequestService } from '../request.service';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { UserService } from '../user.service';
import { User } from '../Models/user';
import { SessionsComponent } from '../sessions/sessions.component';
import { Session } from '../Models/session';
import { CommonModule } from '@angular/common';
import { DocumentsComponent } from '../documents/documents.component';

@Component({
  selector: 'app-archives',
  standalone: true,
  imports: [CommonModule,ReactiveFormsModule, SessionsComponent, DocumentsComponent],
  templateUrl: './archives.component.html',
  styleUrl: './archives.component.css'
})
export class ArchivesComponent {
  user: User;
  requestService: RequestService = inject(RequestService);
  userChanges: any;
  form: FormData;
  sessions: Session[];
  filteredSessions: Session[];
  documents = [];
  hardcodeConcat: string = "InitVal";

  constructor(private userService: UserService){
    this.form = new FormData();
    this.user = userService.user;
    this.sessions = this.user.sessions;
    this.filteredSessions = this.sessions;
  } 

  ngOnInit(){
    this.user = this.userService.user;
    this.userChanges = this.userService.userChange
      .subscribe((user) => {
        this.user = user;
        console.log("User Changed!" + user);
      });
  }

  parseForm = new FormGroup({
    flatfileInput: new FormControl(''),
    specfileInput: new FormControl('')
  });

  

  parseFile(){
    const body = new FormData();
    console.log(this.parseForm.value.flatfileInput?? '');
    body.append('flatfile', this.parseForm.value.flatfileInput?? '');
    body.append('specfile', this.parseForm.value.specfileInput?? '');
    console.log("We gettin here");
    this.requestService.parseFiles(body, this.user.id)
      .subscribe((data) => {
        //This will return our user with the records property
        console.log(data);
      });
  }

  buttonClicked(){
    this.requestService.parseFiles(this.form, this.user.id)
      .subscribe((data)=>{
        console.log(data);
      })
  }
  //TODO: Using FormData like this might include more than 2 formData entries
  flatfileChosen(event: any){
    this.form.append('flatfile', event.target.files[0]);
  }
  specfileChosen(event: any){
    this.form.append('specfile', event.target.files[0]);
  }

  transferDocs(setDocs: any){
    console.log("Archives Method: TransferDocs?");
    console.log("Emitted:", setDocs);
    console.log("type:", typeof(setDocs));
    /*for(let obj of Object.values(setDocs)){
      this.documents.push(obj);
    }*/
    //let firstEntry = setDocs[0];
    //console.log(firstEntry);
    //console.log(Object.values(setDocs));
    //this.documents = Object.values(setDocs);
    this.documents = setDocs;
    this.hardcodeConcatArray();
    
  }
  hardcodeConcatArray(){
    this.hardcodeConcat = "";
    console.log("this.documents.length:",this.documents.length);
    let i = 0;
    while(this.documents.length != 0 && i < this.documents.length){
      this.hardcodeConcat += JSON.stringify(this.documents[i++]);
    }
  }
}
