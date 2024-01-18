import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MaturidadeSmComponent } from './maturidade-sm.component';

describe('MaturidadeSmComponent', () => {
  let component: MaturidadeSmComponent;
  let fixture: ComponentFixture<MaturidadeSmComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MaturidadeSmComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MaturidadeSmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
