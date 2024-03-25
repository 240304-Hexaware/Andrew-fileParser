import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Observable } from 'rxjs';
import { RequestService } from '../request.service';
import { CommonModule } from '@angular/common';
import { User } from '../Models/user';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  
  ping: string = "";
  userSuccessful: User;
  //We do want a service here
  requestService: RequestService = inject(RequestService);
  applyForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('')
  });

  constructor(){
    this.userSuccessful = {
      id: undefined,
      username: "",
      password: ""
    }
  }

  

  submitRegister(){
    console.log("Creating User");
    let user: User = {
      id: undefined,
      username: this.applyForm.value.username?? '',
      password: this.applyForm.value.password?? ''
    }

    console.log("User Created:", user, "\nSending Request...");


    this.requestService.registerUser(user)
      .subscribe((data) => {
        console.log("Returned User:",data);
        this.userSuccessful = data;
      })
    //submit application service(
      //this.applyForm.value.username ?? '',
      //...
    //) 
  }
}
