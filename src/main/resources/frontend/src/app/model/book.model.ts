


export interface Book {
  title: string,
  language: string,
  epochs: string,
  genres: string,
  kinds: string,
  authors: string,
  translators: string,
  cover: string
}

export interface BookWithText extends Book{
  text: string
}
export interface BookCard {
  title: string,
  genres: string
}
