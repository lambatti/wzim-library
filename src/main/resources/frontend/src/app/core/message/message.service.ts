import { Injectable } from '@angular/core';
import { MessageModel } from '../../model/message.model';


@Injectable()
export class MessageService {
  private handler: (m: MessageModel) => void;

  reportMessage(message: MessageModel): void {
    if (this.handler !== null) {
      this.handler(message);
    }
  }

  registerMessageHandler(handler: (m: MessageModel) => void) {
    this.handler = handler;
  }

}
