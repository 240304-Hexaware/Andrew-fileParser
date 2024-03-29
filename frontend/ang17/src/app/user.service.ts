import { Injectable } from '@angular/core';
import { User } from './Models/user';
import { Observable, Observer } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private _user: User;
  userChange: Observable<User>;
  private _observer: Observer<User> = {} as Observer<User>;
  //private _observer: Observer<User> = new Observer();
  //private _observer: Observer<User> = {next: this.user} as Observer<User>;

  constructor() { 
    console.log("Service Created");
    this._user = {
      id: "",
      username: "",
      password: "",
      sessions: [],
    }
    console.log("User done");
    //_observer = new Observer();
    this.userChange = new Observable<User>((observer) => {
      console.log("This seems to happen too late");
      this._observer = observer; 
    });//.share();
    console.log("observable setup???");
    // this.userChange$ = new Observable<User>((observer) => {
    //   this._observer = observer.next();
    // });
  }

  changeUser(user: User){
    this._user = user;
    this._observer.next(user);
    //this._observer.next(() => {return user;});
  }

  public get user(){
    return this._user;
  }

  public set user(user: User){
    this._user = user;
  }
}
