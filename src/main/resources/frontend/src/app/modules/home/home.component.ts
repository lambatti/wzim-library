import { Component } from '@angular/core';
import { BookCard } from '../../model/book.model';
import { StaticData } from '../../model/staticData';


@Component({
  selector: 'app-home',
  templateUrl: 'home.component.html',
  styleUrls: ['home.component.scss'],

})
export class HomeComponent {
  public siesta = 'siemka';

  dodaj() {
    console.log('it works');
  }

  get staticData(): BookCard[] {
    return StaticData;
  }

  // TODO do przeniesienia i wywoływanie gdzieś nidziej
  public shapes: string[] = ['../../../../assets/shapes/shape1.svg', '../../../../assets/shapes/shape2.svg', '../../../../assets/shapes/shape3.svg'];

}
