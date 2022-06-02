import { Component } from '@angular/core';
import { WorkerService } from '../../core/http/worker.service';


@Component({
  selector: 'app-worker-panel',
  templateUrl: 'workerPanel.component.html'
})
export class WorkerPanelComponent {

  constructor(private readonly _workerService: WorkerService) {
  }
}
