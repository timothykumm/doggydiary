import { Component, EventEmitter, Output } from '@angular/core';

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
