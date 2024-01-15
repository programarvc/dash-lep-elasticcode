import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { IUser, CognitoService } from 'src/app/cognito.service';

@Component({
  selector: 'app-confirm-signup',
  templateUrl: './confirm-signup.component.html',
  styleUrls: ['./confirm-signup.component.sass']
})

export class ConfirmSignupComponent {

  loading: boolean = false;
  isConfirmed: boolean = false;
  user: IUser = {
    email: '',
    password: '',
    showPassword: false,
    code: '',
    name: ''
  };

  constructor(private cognitoService: CognitoService, private router: Router) {
    this.loading = false;
    this.isConfirmed = false;
    this.user = {} as IUser;
  }

  public signUp(): void {
    this.loading = true;
    this.cognitoService.signUp(this.user)
      .then(() => {
        this.loading = false;
        this.isConfirmed = true;
      }).catch(() => {
        this.loading = false;
      });
  }

  public confirmSignUp(): void {
    this.loading =  true;
    this.cognitoService.confirmSignUp(this.user)
    .then(() => {
      this.router.navigate(['/login']);
    }).catch(() => {
      this.loading = false;
    })
  }
}
