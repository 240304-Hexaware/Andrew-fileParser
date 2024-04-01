import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Session } from '../Models/session';
import { RequestService } from '../request.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-sessions',
  standalone: true,
  imports: [],
  templateUrl: './sessions.component.html',
  styleUrl: './sessions.component.css'
})
export class SessionsComponent {

  @Input() session!: Session;
  @Output() recordsEmitter = new EventEmitter<any>();

  constructor(private requestService: RequestService, 
    private userService: UserService ){

  }
  
  showDocumentsBySession(){
    const sessionid = this.session.id;
    console.log("this sessionid...", sessionid);
    let docs = this.requestService.getAllRecordsBySession(sessionid);
    this.recordsEmitter.emit(docs); //emits observable
  }


}
