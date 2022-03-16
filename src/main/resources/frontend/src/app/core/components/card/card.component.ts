import { Component, Input } from '@angular/core';


@Component({
  selector: 'app-card',
  templateUrl: 'card.component.html',
  styleUrls: ['card.component.scss']
})
export class CardComponent {
  @Input() title: string = '';
  @Input() description: string = '';
  @Input() coverImg: string = '';



}
