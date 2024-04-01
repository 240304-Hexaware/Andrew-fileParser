import { Component } from '@angular/core';
import { User } from '../Models/user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-authorize',
  standalone: true,
  imports: [],
  templateUrl: './authorize.component.html',
  styleUrl: './authorize.component.css'
})
export class AuthorizeComponent {

  user: User;

  constructor(private userService: UserService){
    this.user = userService.user;
  }

  logout(){
    this.userService.user = {
      id: "",
      username: "",
      password: "",
      sessions: [],
    };
  }

  displayUsername(){
    return this.user.username;
  }
  /*
  displayUsername(){
    return this.userService.user.username;
  }
  */
}
