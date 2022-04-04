import { NgModule } from '@angular/core';
import { ComponentsModule } from './components/components.module';
import { ValidatorsModule } from './validators/validators.module';


@NgModule({
  declarations: [],
  imports: [ComponentsModule, ValidatorsModule],
  exports: [ComponentsModule]
})
export class CoreModule {
}
