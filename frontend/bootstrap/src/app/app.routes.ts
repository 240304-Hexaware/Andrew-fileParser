import { Routes } from '@angular/router';
import { ViewParsedComponent } from './view-parsed/view-parsed.component';
import { ViewAggregateComponent } from './view-aggregate/view-aggregate.component';
import { HomeComponent } from './home/home.component';

export const routes: Routes = [
    {path: '', component: HomeComponent},
    {path: 'view-parsed', component: ViewParsedComponent},
    {path: 'view-aggregate', component: ViewAggregateComponent}
];
