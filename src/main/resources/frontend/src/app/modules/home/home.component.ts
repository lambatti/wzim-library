import { Component } from '@angular/core';
import { HomeSection, IHomeSection } from '../../utils/homeSection';


@Component({
  selector: 'app-home',
  templateUrl: 'home.component.html',
  styleUrls: ['home.component.scss']

})
export class HomeComponent {
  public siesta = 'siemka';

  dodaj() {
    console.log('it works');
  }

  get homeSectionData(): IHomeSection[] {
    return HomeSection;
  }
}
