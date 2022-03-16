import { NgModule } from '@angular/core';
import { HomeComponent } from './home/home.component';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { CoreModule } from '../core/core.module';
import { NzTypographyModule } from 'ng-zorro-antd/typography';
import { NzGridModule } from 'ng-zorro-antd/grid';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzIconModule } from 'ng-zorro-antd/icon';


@NgModule({
  declarations: [HomeComponent],
  imports: [
    NzLayoutModule,
    CoreModule,
    NzTypographyModule,
    NzGridModule,
    NzButtonModule,
    NzInputModule,
    NzIconModule
  ],
  exports: [HomeComponent]
})
export class ModuleModule {
}
