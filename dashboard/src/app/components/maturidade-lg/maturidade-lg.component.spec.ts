import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MaturidadeLgComponent } from './maturidade-lg.component';

describe('MaturidadeLgComponent', () => {
  let component: MaturidadeLgComponent;
  let fixture: ComponentFixture<MaturidadeLgComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MaturidadeLgComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MaturidadeLgComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
