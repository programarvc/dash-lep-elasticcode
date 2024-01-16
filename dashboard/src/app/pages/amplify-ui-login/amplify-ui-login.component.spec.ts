import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AmplifyUiLoginComponent } from './amplify-ui-login.component';

describe('AmplifyUiLoginComponent', () => {
  let component: AmplifyUiLoginComponent;
  let fixture: ComponentFixture<AmplifyUiLoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AmplifyUiLoginComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AmplifyUiLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
