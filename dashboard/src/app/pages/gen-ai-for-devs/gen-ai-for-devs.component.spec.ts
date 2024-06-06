import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GenAiForDevsComponent } from './gen-ai-for-devs.component';

describe('GenAiForDevsComponent', () => {
  let component: GenAiForDevsComponent;
  let fixture: ComponentFixture<GenAiForDevsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GenAiForDevsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GenAiForDevsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
