import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashProjetoComponent } from './dash-projeto.component';

describe('DashProjetoComponent', () => {
  let component: DashProjetoComponent;
  let fixture: ComponentFixture<DashProjetoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashProjetoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DashProjetoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
