import { NgModule } from '@angular/core';
import { HomeComponent } from './home/home.component';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { CoreModule } from '../core/core.module';
import { NzTypographyModule } from 'ng-zorro-antd/typography';


@NgModule({
  declarations: [HomeComponent],
    imports: [
        NzLayoutModule,
        CoreModule,
        NzTypographyModule
    ],
  exports: [HomeComponent]
})
export class ModuleModule {
}
