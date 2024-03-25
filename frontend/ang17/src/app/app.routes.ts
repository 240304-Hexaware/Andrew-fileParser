import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ArchivesComponent } from './archives/archives.component';
import { AddFileComponent } from './add-file/add-file.component';
import { RegisterComponent } from './register/register.component';

export const routes: Routes = [
    {path: '', component: HomeComponent},
    {path: 'archives', component: ArchivesComponent},
    {path: 'add-file', component: AddFileComponent},
    {path: 'register', component: RegisterComponent}
];
