import { Component, inject } from '@angular/core';
import { RequestService } from '../request.service';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-archives',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './archives.component.html',
  styleUrl: './archives.component.css'
})
export class ArchivesComponent {
  user = {
    id: "6602f445ae64ab0180fe056c",
    username: "Jyle",
  }
  requestService: RequestService = inject(RequestService);

  parseForm = new FormGroup({
    flatfileInput: new FormControl(''),
    specfileInput: new FormControl('')
  });

  parseFile(){
    const body = new FormData();
    body.append('flatfile', this.parseForm.value.flatfileInput?? '');
    body.append('specfile', this.parseForm.value.specfileInput?? '');
    console.log("We gettin here");
    this.requestService.parseFiles(body, this.user.id)
      .subscribe((data) => {
        //This will return our user with the records property
        console.log(data);
      });
  }




}
