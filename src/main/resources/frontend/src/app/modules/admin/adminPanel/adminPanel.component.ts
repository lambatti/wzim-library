import { Component } from '@angular/core';


@Component({
  templateUrl: 'adminPanel.component.html',
  styleUrls: ['adminPanel.component.scss']
})
export class AdminPanelComponent {
  isCollapsed: boolean = false;

  toggleCollapsed() {
    this.isCollapsed = !this.isCollapsed;
  }

}
