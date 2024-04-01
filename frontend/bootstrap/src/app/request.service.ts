import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './Models/user';

@Injectable({
  providedIn: 'root'
})
export class RequestService {

  url: string = "http://localhost:8080/";

  options: Object = {
    responseType: 'text'
  }

  registerOptions: Object = {
    responseType: 'json',
  }

  constructor(private http: HttpClient) { }

  

  sendPing(): Observable<string>{
    return this.http.get<string>(this.url+"ping", this.options);
  }

  registerUser(username: string, password: string): Observable<User>{
    let user = {'username': username, 'password': password};
    return this.http.post<User>(this.url+"users/register", user, this.registerOptions);
  }

  loginUser(username: string, password: string): Observable<User>{
    let user = {'username': username, 'password': password}
    return this.http.post<User>(this.url+"users/login", user, this.registerOptions);
  }

  authorizeUser(user: User){
    return this.http.post<Boolean>(this.url+"users/authorize", user);
  }

  parseFiles(body: FormData, id: string){
    let headers = new HttpHeaders();
    headers = headers.append('id', id);
    // headers = headers.append('content-type', 'multipart/form-data');
    console.log(id);
    console.log("We get to service");

    return this.http.post<any>(this.url+"file-parser", body, {'headers': headers});
  }

  getAllRecordsBySession(id: any){
    console.log("Recieving id:",id);
    let urlString = this.url+"getRecordsBySession/"+id;
    console.log("Sending Request to:",urlString);
    return this.http.get<any>(urlString);
  }
}
