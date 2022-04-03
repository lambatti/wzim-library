import { Component, Input } from '@angular/core';
import { BookCard } from '../../../model/book.model';


@Component({
  selector: 'app-popular-section',
  templateUrl: 'popularSection.component.html',
  styleUrls: ['popularSection.component.scss']
})
export class PopularSectionComponent {

  @Input() heading: string = '';
  @Input() cardList: BookCard[] = null!;
  @Input() shape: string = null!;

}
