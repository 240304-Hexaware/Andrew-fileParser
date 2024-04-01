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
  
  userService: UserService;
  
  constructor(private requestService: RequestService,private useService: UserService){
    this.userService = useService;

    /*userService.userObservable
    .subscribe(
      {next:(user)=>{
        this.user = user;
      },
      error: ()=>{

      },
      complete: ()=>{

      }
      }
    
    );*/
  }

  

  /*authorizeUser(){
    this.requestService.authorizeUser(this.userService.user)
      .subscribe(
        (data) => {
          return data;
        }
      );
    return false;
  }*/

  
  

 
}
