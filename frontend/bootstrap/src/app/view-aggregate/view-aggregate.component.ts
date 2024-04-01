import { Component } from '@angular/core';
import { UserService } from '../user.service';

@Component({
  selector: 'app-view-aggregate',
  standalone: true,
  imports: [],
  templateUrl: './view-aggregate.component.html',
  styleUrl: './view-aggregate.component.css'
})
export class ViewAggregateComponent {

  constructor(private userService: UserService){

  }
}
