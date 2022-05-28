import { Component } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  templateUrl: 'adminPanel.component.html',
  styleUrls: ['adminPanel.component.scss']
})
export class AdminPanelComponent {
  isCollapsed: boolean = false;

  constructor(private readonly router: Router) {
  }

  toggleCollapsed() {
    this.isCollapsed = !this.isCollapsed;
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('firstname');
    this.router.navigateByUrl('admin/auth')


  }

}
