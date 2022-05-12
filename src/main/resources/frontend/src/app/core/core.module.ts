import { NgModule } from '@angular/core';
import { ComponentsModule } from './components/components.module';
import { ValidatorsModule } from './validators/validators.module';
import { MessageModule } from './message/message.module';
import { UserRepository } from './services/user.repository';
import { UserService } from './http/user.service';
import { BookRepository } from './services/book.repository';


@NgModule({
  imports: [ComponentsModule, ValidatorsModule, MessageModule],
  exports: [ComponentsModule, MessageModule],
  providers: [UserRepository, UserService, BookRepository]
})
export class CoreModule {
}
