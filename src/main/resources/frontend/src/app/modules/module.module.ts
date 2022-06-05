import { NgModule } from '@angular/core';
import { HomeComponent } from './home/home.component';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { CoreModule } from '../core/core.module';
import { NzTypographyModule } from 'ng-zorro-antd/typography';
import { NzGridModule } from 'ng-zorro-antd/grid';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { CommonModule } from '@angular/common';
import { LoginTemplateComponent } from './loginTemplate/loginTemplate.component';
import { RouterModule } from '@angular/router';
import { BookDetailsComponent } from './bookDetails/bookDetails.component';
import { BookCategoriesComponent } from './bookCategories/bookCategories.component';
import { NzMenuModule } from 'ng-zorro-antd/menu';
import { NzDrawerModule } from 'ng-zorro-antd/drawer';
import { NzDropDownModule } from 'ng-zorro-antd/dropdown';
import { NzPaginationModule } from 'ng-zorro-antd/pagination';
import { ReadBookComponent } from './readBook/readBook.component';
import { NzSwitchModule } from 'ng-zorro-antd/switch';
import { FormsModule } from '@angular/forms';
import { UserTemplateComponent } from './userTemplate/userTemplate.component';
import { WorkerPanelComponent } from './workerPanel/workerPanel.component';
import { NzPageHeaderModule } from 'ng-zorro-antd/page-header';
import { NzDescriptionsModule } from 'ng-zorro-antd/descriptions';
import { NzTagModule } from 'ng-zorro-antd/tag';


@NgModule({
    imports: [
        NzLayoutModule,
        CoreModule,
        NzTypographyModule,
        NzGridModule,
        NzButtonModule,
        NzInputModule,
        NzIconModule,
        CommonModule,
        RouterModule,
        NzMenuModule,
        NzDrawerModule,
        NzDropDownModule,
        NzPaginationModule,
        NzSwitchModule,
        FormsModule,
        NzPageHeaderModule,
        NzDescriptionsModule,
        NzTagModule
    ],
  declarations: [
    HomeComponent,
    LoginTemplateComponent,
    UserTemplateComponent,
    BookDetailsComponent,
    BookCategoriesComponent,
    ReadBookComponent,
    WorkerPanelComponent
  ],
  exports: [
    HomeComponent,
    LoginTemplateComponent,
    UserTemplateComponent,
    BookDetailsComponent,
    BookCategoriesComponent,
    ReadBookComponent,
    WorkerPanelComponent
  ]
})
export class ModuleModule {
}
