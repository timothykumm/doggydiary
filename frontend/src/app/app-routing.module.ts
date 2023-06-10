import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { DocumentComponent } from './components/document/document.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'dog', component: DocumentComponent },
  //Alle nicht übereinstimmenden URLs auf home umleiten
  { path: '**', redirectTo: '/home' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
