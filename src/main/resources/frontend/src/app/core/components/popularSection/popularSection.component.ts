import { Component, Input } from '@angular/core';


@Component({
  selector: 'app-popular-section',
  templateUrl: 'popularSection.component.html',
  styleUrls: ['popularSection.component.scss']
})
export class PopularSectionComponent {

  @Input() heading: string = '';
}
