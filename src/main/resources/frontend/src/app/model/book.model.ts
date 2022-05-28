export interface Book {
  slug: string,
  title: string,
  language: string,
  epochs: string[],
  genres: string[],
  kinds: string[],
  authors: string[],
  translators: string[],
  cover: string
}

export interface BookWithText extends Book {
  text: string;
}

export interface BookCard {
  title: string;
  kinds: string[];
  cover: string;
  authors: string[];
  slug: string;
}


export interface UserBookStatus {
  borrowedBooks: number,
  readBooks: number,
}


export interface BookCategory {
  name: string
}
