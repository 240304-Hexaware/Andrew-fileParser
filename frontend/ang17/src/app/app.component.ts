import { Component, inject } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { User } from './Models/user';
import { RequestService } from './request.service';
import { UserService } from './user.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterModule, HomeComponent, ReactiveFormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'ang17';
  requestService: RequestService = inject(RequestService);

  constructor(private userService: UserService){
    console.log("App Created");
  }
  loginForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('')
  });

  submitLogin(){
    console.log("Login User");
    
    let username = this.loginForm.value.username?? '';
    let password = this.loginForm.value.password?? '';
    

    this.requestService.loginUser(username, password)
      .subscribe((data) => {
        console.log("WrittenLoginUser:", data);
        //this.userService.user = data;
        this.userService.changeUser(data);
      })
    //TODO: do we need an onchange for HOme Component?

  }

}
