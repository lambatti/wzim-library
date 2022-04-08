import { Component } from '@angular/core';
import { MessageService } from './message.service';
import { MessageModel } from '../../model/message.model';


@Component({
  selector: 'app-message',
  templateUrl: 'message.component.html'
})
export class MessageComponent{
  public lastMessage: MessageModel;
  isVisible = false;
  constructor(_messageService: MessageService) {
    _messageService.registerMessageHandler(m => this.lastMessage = m);
    this.isVisible = true;

  }

  handleOk(): void {
    console.log('Button ok clicked!');
    this.isVisible = false;
  }

  handleCancel(): void {
    console.log('Button cancel clicked!');
    this.isVisible = false;
  }




}
