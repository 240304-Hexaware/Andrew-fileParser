import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './Models/user';
import { Rmetadata } from './Models/rmetadata';
import { Session } from './Models/session';

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
    let urlString = this.url+"getRecordsBySession/"+id;
    return this.http.get<any>(urlString);
  }
  getMetadataBySession(id: any){
    let urlString = this.url+"getMetadataBySession/"+id;
    return this.http.get<Rmetadata>(urlString);
  }
  loadSpecFilter(id:any, specName:string){
    let urlString = this.url+"users/"+id+"/loadSpecFilter/"+specName;
    return this.http.get<Session[]>(urlString);
  }


  getFlatfileNames(id: any){
    let urlString = this.url+"users/"+id+"/getFlatFileNames";
    return this.http.get<string[]>(urlString);
  }
  getSpecfileNames(id: any){
    let urlString = this.url+"users/"+id+"/getSpecFileNames";
    return this.http.get<string[]>(urlString);
  }
}
