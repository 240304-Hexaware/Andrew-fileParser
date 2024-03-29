import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Session } from '../Models/session';
import { RequestService } from '../request.service';
import { User } from '../Models/user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-sessions',
  standalone: true,
  imports: [],
  templateUrl: './sessions.component.html',
  styleUrl: './sessions.component.css'
})
export class SessionsComponent {

  user: User;
  @Input() session!: Session;
  @Output() docsEmitter = new EventEmitter<any>();

  constructor(private requestService: RequestService, userService: UserService){
    this.user = userService.user;
  }

  showDocumentsBySession(){
    const sessionid = this.session.id;
    let docs = this.requestService.getAllRecordsBySession(this.session.id);
    //console.log("Retrieved:",docs);
    docs.subscribe(docs => this.docsEmitter.emit(docs));
    //this.docsEmitter.emit(docs);

  }

}
