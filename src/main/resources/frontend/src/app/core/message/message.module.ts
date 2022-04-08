import { ErrorHandler, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MessageComponent } from './message.component';
import { MessageService } from './message.service';
import { MessageErrorHandler } from './errorHandler';
import { NzModalModule } from 'ng-zorro-antd/modal';
import { NzResultModule } from 'ng-zorro-antd/result';


@NgModule({
  imports: [BrowserModule, NzModalModule, NzResultModule],
  declarations: [MessageComponent],
  exports: [MessageComponent],
  providers: [MessageService, { provide: ErrorHandler, useClass: MessageErrorHandler }]
})
export class MessageModule {
}
