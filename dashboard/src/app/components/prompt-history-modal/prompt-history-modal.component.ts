import { Component, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-prompt-history-modal',
  templateUrl: './prompt-history-modal.component.html',
  styleUrls: ['./prompt-history-modal.component.sass']
})
export class PromptHistoryModalComponent {
  @Input() prompts: any;

  constructor(public modal: NgbActiveModal) {}
}
