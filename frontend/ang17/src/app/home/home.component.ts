import { Component } from '@angular/core';
import { ArchivesComponent } from '../archives/archives.component';
import { CommonModule } from '@angular/common';
import { User } from '../Models/user';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, ArchivesComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  
  user: User = {
    id: undefined,
    username: "",
    password: ""
  };

  
  // user: User = {
  //   id: "6602f445ae64ab0180fe056c",
  //   username: "Jyle",
  //   password: ""
  // };
  

  constructor(){}

  putUser(){
    this.user.id = "6602f445ae64ab0180fe056c";
    this.user.username = "Jyle";
  }
  

 
}
