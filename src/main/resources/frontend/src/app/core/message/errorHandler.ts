import { ErrorHandler, Injectable, NgZone } from '@angular/core';
import { MessageService } from './message.service';
import { MessageModel } from '../../model/message.model';

@Injectable()
export class MessageErrorHandler implements ErrorHandler {

  constructor(private _messageService: MessageService, private ngZone: NgZone) {
  }

  handleError(error: any) {
    let msg = error instanceof Error ? error.message : error.toString();
    this.ngZone.run(() => this._messageService.reportMessage(new MessageModel(msg, true)), 0);
  }

}
