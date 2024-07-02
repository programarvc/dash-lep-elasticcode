import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PromptHistoryModalComponent } from './prompt-history-modal.component';

describe('PromptHistoryModalComponent', () => {
  let component: PromptHistoryModalComponent;
  let fixture: ComponentFixture<PromptHistoryModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PromptHistoryModalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PromptHistoryModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
