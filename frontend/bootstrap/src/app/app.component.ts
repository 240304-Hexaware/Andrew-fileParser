import { Component } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { AuthorizeComponent } from './authorize/authorize.component';
import { CommonModule } from '@angular/common';
import { UserService } from './user.service';
import { User } from './Models/user';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RequestService } from './request.service';
import { Observable } from 'rxjs';
import { Session } from './Models/session';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, 
    RouterOutlet,
    RouterModule,
    AuthorizeComponent, 
    ReactiveFormsModule
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'bootstrap';
  //_userService: UserService;
  //userObservable: any; //Observable<User> didn't work... hmm

  registerForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('')
  });

  loginForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('')
  });

  parseForm: FormData;

  constructor(
    private userService: UserService, 
    private requestService: RequestService) {
      //this.user = userService.user;
      //this._userService = userService;
      this.parseForm = new FormData();
  }
  /*ngOnInit() {
    this.user = this.userService.user;
    this.userObservable = this.userService.userObservable
      .subscribe((user) => {
        this.user = user;
      });
  }*/

  submitLogin() {
    let username = this.loginForm.value.username ?? '';
    let password = this.loginForm.value.password ?? '';

    this.requestService.loginUser(username, password)
      .subscribe((data) => {
        //console.log(data);
        this.userService.user = data;
        //this.activateLinks();
        //this.user = this.userService.user;
      })
    //TODO: do we need an onchange for Home Component?
  }

  submitRegister(){
    
    let username: string = this.registerForm.value.username?? '';
    let password: string = this.registerForm.value.password?? '';

    //TODO: Any more fields like email increases our argument, bad practice.
    this.requestService.registerUser(username, password) 
      .subscribe((data) => {
        this.userService.user = data;
        //this.activateLinks();
      });
  }

  /*activateLinks(){
    console.log("Changing attribute");
    let ident = 'nav-link-'
    const elements = document.querySelectorAll(".nav-link");
    elements.forEach((element)=>{
      element.setAttribute("class", "nav-link");
      element.setAttribute("aria-disabled", "false");
      switch(element.getAttribute("id")){
        case (ident+'add'):

          element.setAttribute("class", "btn")
          element.setAttribute("data-bs-target", "#addFileModal");
          element.setAttribute("data-bs-toggle", "modal");
          //data-bs-toggle="modal"
          break;
        case ident+'view-parsed':
          element.setAttribute("[routerLink]", "['/view-parsed']");
          //aria-disabled="true" [routerLink]="['/view-parsed']";
          break;
        case ident+'view-aggregate':
          element.setAttribute("[routerLink]", "['/view-aggregate']");
          //aria-disabled="true" [routerLink]="['/view-aggregate']";
          break;
      }
    });
  }*/

  welcomeCheck() : boolean{
    return !(this.userService.user.id == '');
  }

  submitParse(){
    this.requestService.parseFiles(this.parseForm, this.userService.user.id)
      .subscribe((data)=>{
        console.log("Parse Complete");
        this.userService.user = data;
      })
  }

  flatfileChosen(event: any){
    this.parseForm.append('flatfile', event.target.files[0]);
  }
  specfileChosen(event: any){
    this.parseForm.append('specfile', event.target.files[0]);
  }


}
