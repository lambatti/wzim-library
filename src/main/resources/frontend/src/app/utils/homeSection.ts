import { BookCard } from '../model/book.model';
import { StaticData } from '../model/staticData';

export interface IHomeSection {
  heading: string,
  shape: string,
  bookCard: BookCard[]
}


export const HomeSection: IHomeSection[] = [
  {
    heading: 'bestsellery',
    shape: '../../../../assets/shapes/shape1.svg',
    bookCard: StaticData
  },
  {
    heading: 'naukowe',
    shape: '../../../../assets/shapes/shape2.svg',
    bookCard: StaticData

  },
  {
    heading: 'liryka',
    shape: '../../../../assets/shapes/shape3.svg',
    bookCard: StaticData

  }

];

