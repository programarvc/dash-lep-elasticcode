import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistroNovoDevComponent } from './registro-novo-dev.component';

describe('RegistroNovoDevComponent', () => {
  let component: RegistroNovoDevComponent;
  let fixture: ComponentFixture<RegistroNovoDevComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistroNovoDevComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegistroNovoDevComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
