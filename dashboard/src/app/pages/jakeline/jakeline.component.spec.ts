import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JakelineComponent } from './jakeline.component';

describe('JakelineComponent', () => {
  let component: JakelineComponent;
  let fixture: ComponentFixture<JakelineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JakelineComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(JakelineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
