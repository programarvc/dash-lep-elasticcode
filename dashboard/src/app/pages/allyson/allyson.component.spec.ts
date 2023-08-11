import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllysonComponent } from './allyson.component';

describe('AllysonComponent', () => {
  let component: AllysonComponent;
  let fixture: ComponentFixture<AllysonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllysonComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AllysonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
