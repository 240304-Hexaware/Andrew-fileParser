import { Component, inject } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { User } from './Models/user';
import { RequestService } from './request.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterModule, HomeComponent, ReactiveFormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'ang17';
  user: User;
  requestService: RequestService = inject(RequestService);

  constructor(){
      this.user = {
        id: undefined,
        username: "",
        password: ""
      }
  }
  loginForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('')
  });

  submitLogin(){
    console.log("Login User");
    let inputUser: User = {
      id: undefined,
      username: this.loginForm.value.username?? '',
      password: this.loginForm.value.password?? ''
    }

    this.requestService.loginUser(inputUser)
      .subscribe((data) => {
        this.user = data;
        console.log("WrittenLoginUser:", this.user);
      })

  }

}
