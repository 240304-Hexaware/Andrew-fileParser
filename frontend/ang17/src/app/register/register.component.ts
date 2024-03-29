import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Observable } from 'rxjs';
import { RequestService } from '../request.service';
import { CommonModule } from '@angular/common';
import { User } from '../Models/user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  
  ping: string = "";
  //We do want a service here
  requestService: RequestService = inject(RequestService);
  applyForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('')
  });

  constructor(private userService: UserService){
  }

  

  submitRegister(){
    console.log("Creating User");
    
    let username: string = this.applyForm.value.username?? '';
    let password: string = this.applyForm.value.password?? '';

    

    console.log("User Created:", username,password, "\nSending Request...");


    this.requestService.registerUser(username, password) //TODO: Any more fields like email increases our argument, bad practice.
      .subscribe((data) => {
        //console.log("this.userSuccessful =", this.userSuccessful);
        this.userService.user = data;
        //TODO: Have Screen enter Home Route
      })
    //submit application service(
      //this.applyForm.value.username ?? '',
      //...
    //) 
  }
}
