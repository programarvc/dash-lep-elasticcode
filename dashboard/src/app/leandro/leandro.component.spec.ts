import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LeandroComponent } from './leandro.component';

describe('LeandroComponent', () => {
  let component: LeandroComponent;
  let fixture: ComponentFixture<LeandroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LeandroComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LeandroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
