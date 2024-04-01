import { Injectable } from '@angular/core';
import { User } from './Models/user';
import { Observable, Observer } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private _user: User;
  userObservable: Observable<User> = {} as Observable<User>;

  constructor() { 
    console.log("Service Contructor");
    this._user = {
      id: "",
      username: "",
      password: "",
      sessions: [],
    }
    this.userObservable = new Observable<User>(
      (data)=>{
        data.next(this._user);
      }
    );
  }

  changeUser(user: User){
    this._user = user;
    this.userObservable = new Observable<User>((observer) => {
      observer.next(user);
    });
  }


  public get user(){
    return this._user;
  }

  public set user(user: User){
    this._user = user;
  }
}
