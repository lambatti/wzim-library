import { Component, Input } from '@angular/core';
import { NzDrawerPlacement } from 'ng-zorro-antd/drawer';


@Component({
  selector: 'app-user-drawer',
  templateUrl: 'userDrawer.component.html',
  styleUrls: ['userDrawer.component.scss']
})
export class UserDrawerComponent {
  visible = false;
  placement: NzDrawerPlacement = 'left';
  @Input() firstName: string = '';

  open(): void {
    this.visible = true;
  }

  close(): void {
    this.visible = false;
  }
}
