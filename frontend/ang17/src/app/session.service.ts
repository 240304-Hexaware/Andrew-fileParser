import { Injectable } from '@angular/core';
import { Session } from './Models/session';
import { Observable } from 'rxjs';
import { User } from './Models/user';

@Injectable({
  providedIn: 'root'
})
export class SessionService {
  private _sessions: Session[];
  private sessionObservable: Observable<Session[]>;

  constructor() {
    this._sessions = [];
    this.sessionObservable = new Observable<Session[]>((data)=> {
      data.next(this._sessions);
    });

   }

   changeSessions(sessions: Session[]){
    this._sessions = sessions;
    this.sessionObservable = new Observable<Session[]>( (observer)=>{
      observer.next(sessions);
    });
   }
   addSession(session: Session){
    this.sessions.push(session);
    this.sessionObservable = new Observable<Session[]>((observer) => {
      observer.next(this._sessions);
    })

   }

   public get sessions(){
    return this._sessions;
   }
   public set sessions(sessions: Session[]){
    this._sessions = sessions;
   }

}
