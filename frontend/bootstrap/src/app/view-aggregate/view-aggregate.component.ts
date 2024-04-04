import { Component } from '@angular/core';
import { UserService } from '../user.service';
import { Session } from '../Models/session';
import { RequestService } from '../request.service';
import { Observable } from 'rxjs';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-view-aggregate',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './view-aggregate.component.html',
  styleUrl: './view-aggregate.component.css'
})
export class ViewAggregateComponent {

  specFileNames: string[] = [];
  records: any[] = [];
  selectedOption: string;


  constructor(private userService: UserService, private requestService: RequestService){
    this.selectedOption = '0';
    requestService.getSpecfileNames(userService.user.id)
      .subscribe((specNames:string[])=>{
        this.specFileNames = specNames;
        console.log("Loaded specfilenames:", this.specFileNames);
      });
  }

  loadFilter(){
    console.log("loadFilter Hit");
    this.records = [];
    if(this.selectedOption != "0"){
      console.log("LoadFilter Component Method");
      let specName:string = this.selectedOption;
      this.requestService.loadSpecFilter(this.userService.user.id, specName)
        .subscribe((sessions: Session[])=>{
          for(let session of sessions){
            this.requestService.getAllRecordsBySession(session.id)
              .subscribe((records: any[])=>{
                for(let record of records){
                  this.records.push(record); //Do we need to put Object.values(record)???
                }
              });
          }
        });
    }
    
    
  }
  valuify(record: any){
    return Object.values(record);
  }
}
