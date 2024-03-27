import { HttpClient, HttpHeaders } from '@angular/common/http';
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

  loginUser(user: User): Observable<User>{
    return this.http.post<User>(this.url+"users/login", user, this.registerOptions);
  }

  parseFiles(body: FormData, id: string){
    let headers = new HttpHeaders();
    headers = headers.append('id', id);
    headers = headers.append('content-type', 'multipart/form-data');
    console.log(id);
    console.log("We get to service");

    return this.http.post<any>(this.url+"file-parser", body, {'headers': headers});
  }
}
