import { Component, OnInit } from '@angular/core';
import { UserService } from '../../core/http/user.service';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-read_book',
  templateUrl: 'readBook.component.html',
  styleUrls: ['readBook.component.scss']
})
export class ReadBookComponent implements OnInit{

  constructor(private readonly _repository: UserService, private router: Router, private http: HttpClient) {
  }
  title = '';
  text = 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus ac lectus metus. Aenean blandit luctus mi, sit amet feugiat purus molestie ut. Donec interdum diam diam, ut volutpat nibh dignissim sed. Aliquam erat volutpat. Ut eu justo pretium, semper neque nec, iaculis erat. Aenean pharetra auctor pulvinar. Phasellus rhoncus luctus est, eget pharetra risus pulvinar id. Nulla nec volutpat felis. In consequat nulla velit, sed pulvinar eros malesuada et. Quisque lectus nibh, malesuada non mi ac, lacinia accumsan elit. Suspendisse mollis mollis erat, non viverra erat porta sit amet. Vivamus ultrices a felis nec sollicitudin.\n' +
    '\n' +
    'Pellentesque tempus, nunc id luctus finibus, sem nibh porttitor augue, ut tristique orci sapien sit amet arcu. Nulla facilisi. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Etiam cursus at purus nec laoreet. Suspendisse ut semper est. Suspendisse potenti. Donec ut hendrerit leo, sed sodales quam. Sed facilisis eros eu urna pulvinar, sit amet bibendum augue gravida. Pellentesque interdum dignissim dapibus.\n' +
    '\n' +
    'Morbi dignissim ac lectus at convallis. Nam at mauris dolor. Sed pulvinar malesuada ex. Curabitur sit amet tortor vel ex hendrerit imperdiet sed in nisi. Vivamus a dui quis nulla congue ornare sed id ipsum. Pellentesque molestie tellus quis turpis viverra volutpat. Nam imperdiet semper bibendum. Nullam vel pellentesque elit. In ante massa, hendrerit ac vehicula in, pulvinar nec neque.\n' +
    '\n' +
    'Sed auctor auctor libero, dapibus facilisis nulla. Pellentesque convallis blandit vulputate. Quisque id diam vitae tortor semper cursus sed eget dui. Donec ultrices quam a arcu rhoncus, porta imperdiet tortor condimentum. Sed nibh mauris, egestas vitae urna porta, lobortis consectetur purus. Nunc sapien erat, facilisis placerat tristique ultricies, efficitur et magna. Proin porta fringilla blandit. Donec quis leo erat. Vivamus tincidunt mauris eget elit aliquet, a tempus lacus vehicula.\n' +
    '\n' +
    'Donec mi diam, iaculis in rutrum nec, consequat vitae velit. Mauris tristique sapien est, id lobortis tellus porttitor ac. Fusce viverra euismod arcu non lobortis. Sed euismod, elit non porta pellentesque, elit enim mattis eros, at interdum nisl nibh eget neque. Cras eu placerat ante. Nunc lobortis dolor et ex feugiat, non consectetur tellus mattis. Pellentesque sit amet leo molestie, tincidunt massa vel, porta augue. Nulla ut rutrum mi. Praesent id metus auctor, sollicitudin diam sit amet, efficitur justo. Aenean aliquet sagittis dictum. Aenean ornare, sapien id luctus efficitur, elit eros feugiat mi, ac imperdiet ante ipsum et orci.\n' +
    '\n' +
    'Nunc rutrum pulvinar hendrerit. Integer convallis mi sit amet efficitur eleifend. Nullam a fermentum eros. Donec ut auctor ante, sit amet convallis est. Sed pharetra hendrerit iaculis. Maecenas in lacus vitae libero aliquam facilisis. Curabitur ac feugiat dolor, ut elementum lectus. Aliquam fringilla aliquam ex, vitae dignissim justo lobortis non. Ut euismod justo a ex condimentum, ut tincidunt orci vestibulum. Cras tellus augue, eleifend vel lacinia et, interdum eget augue. Curabitur porttitor leo sed nibh congue posuere. Sed sed ipsum sit amet est rhoncus pretium. Nunc a viverra tellus. Nam posuere pretium molestie.\n' +
    '\n' +
    'Morbi faucibus commodo magna, eget hendrerit lectus dignissim a. Nunc volutpat, felis eu feugiat maximus, nunc augue finibus augue, sed commodo justo ipsum et tellus. Nullam id tempus nibh. Suspendisse quam velit, rhoncus at efficitur sed, cursus a sem.';

  darkMode: boolean = Boolean(localStorage.getItem('darkMode')) || false;
  slug: string = this.router.url.split('/')[3];



  ngOnInit(): void {
    this._repository.readBook(this.slug).subscribe(item => {
      this.title = item.title;
      this.http.get<string>(item.txt).subscribe(value => {
        this.text = value;
      })

    })


  }




}



