import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-value',
  standalone: true,
  imports: [],
  templateUrl: './value.component.html',
  styleUrl: './value.component.css'
})
export class ValueComponent {

  @Input() col!: any;

}
