import { Component, NgModule } from '@angular/core';
import { NgbCollapseModule } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'ddr-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

navbarTitle = "DoggyDiary";
isCollapsed = true;

toggleCollapse(): void {
  this.isCollapsed = !this.isCollapsed;
}

}
