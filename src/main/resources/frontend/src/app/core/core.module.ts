import { NgModule } from '@angular/core';
import { ComponentsModule } from './components/components.module';
import { ValidatorsModule } from './validators/validators.module';
import { MessageModule } from './message/message.module';


@NgModule({
  declarations: [],
  imports: [ComponentsModule, ValidatorsModule, MessageModule],
  exports: [ComponentsModule, MessageModule],
})
export class CoreModule {
}
