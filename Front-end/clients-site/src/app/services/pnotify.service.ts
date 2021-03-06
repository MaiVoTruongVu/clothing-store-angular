import { Injectable } from '@angular/core';
import PNotify from 'pnotify/dist/es/PNotify';
import PNotifyButtons from 'pnotify/dist/es/PNotifyButtons';
import { Content } from '@angular/compiler/src/render3/r3_ast';
import PNnotifyConfirm from 'pnotify/dist/es/PNotifyConfirm';

@Injectable({
  providedIn: 'root'
})
export class PnotifyService {
  pnotify;
  constructor() {
    // tslint:disable-next-line: no-unused-expression
    PNotifyButtons; // Initiate the module. Important!
    // tslint:disable-next-line: no-unused-expression
    PNnotifyConfirm;
    PNotify.defaults.styling = 'bootstrap4';
    PNotify.defaults.icons = 'fontawesome4';
    this.pnotify = PNotify;
  }
  showSuccess(content: string) {
    this.pnotify.success({
      text: content
    });
  }
  showFailure(title: string, content: string) {
    this.pnotify.error({
      title: title,
      text: content
    });
  }
  showConfirm(title: string, content: string, callback: (yes: boolean) => void) {
    const notice = PNotify.notice({
      title: title,
      text: content,
      icon: 'fa fa-question-circle',
      stack: {
        'dir1': 'down',
        'modal': true,
        'firstpos1': 30
      },
      modules: {
        Confirm: {
          confirm: true,
        },
        Buttons: {
          closer: false,
          sticker: false
        },
        History: {
          history: false
        }
      }
    });
    notice.on('pnotify.confirm', function() {
      callback(true);
    });
    notice.on('pnotify.cancel', function() {
      callback(false);
    });
  }

}
