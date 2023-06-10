import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { DogInnerviewComponent } from './components/dog-innerview/dog-innerview.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent},
  { path: 'dog', component: DogInnerviewComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
