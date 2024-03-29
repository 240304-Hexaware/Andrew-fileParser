import { Component } from '@angular/core';
import { ArchivesComponent } from '../archives/archives.component';
import { CommonModule } from '@angular/common';
import { User } from '../Models/user';
import { UserService } from '../user.service';
import { RequestService } from '../request.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, ArchivesComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  
  user: User;

  
  // user: User = {
  //   id: "6602f445ae64ab0180fe056c",
  //   username: "Jyle",
  //   password: ""
  // };
  

  constructor(private requestService: RequestService,private userService: UserService){
    this.user = userService.user;
    console.log("Home Created");
  }

  putUser(){
    this.user.id = "6602f445ae64ab0180fe056c";
    this.user.username = "Jyle";
  }

 

  /*fun(){
    return this.requestService.authorizeUser(this.user);
  }*/

  
  

 
}
