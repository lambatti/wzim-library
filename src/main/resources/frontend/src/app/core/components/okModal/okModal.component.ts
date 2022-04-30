import { Component, Input } from '@angular/core';
import { NzResultStatusType } from 'ng-zorro-antd/result/result.component';


@Component({
  selector: 'app-ok-modal',
  templateUrl: 'okModal.component.html'
})
export class OkModalComponent {
  @Input() isVisible!: boolean;
  @Input() handleOk!: Function;
  @Input() handleCancel!: Function;
  @Input() title!: string;
  @Input() status!: NzResultStatusType;

}
