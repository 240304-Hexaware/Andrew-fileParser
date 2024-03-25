import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './Models/user';

@Injectable({
  providedIn: 'root'
})
export class RequestService {

  url: string = "http://localhost:8080/";

  constructor(private http: HttpClient) { }

  options: Object = {
    responseType: 'text'
  }

  registerOptions: Object = {
    responseType: 'json',
  }

  sendPing(): Observable<string>{
    return this.http.get<string>(this.url+"ping", this.options);
  }

  registerUser(user: User): Observable<User>{
    return this.http.post<User>(this.url+"users/register", user, this.registerOptions);
  }
}
