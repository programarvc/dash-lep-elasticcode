import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { IUser, CognitoService } from '../../cognito.service';


@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.sass']
})
export class SignInComponent {

  loading: boolean = false;
  user: IUser = {
    email: '',
    password: '',
    showPassword: false,
    code: '',
    name: ''
  };

  constructor(private router: Router, private cognitoService: CognitoService) {
    this.loading = false;
    this.user = {} as IUser;
  }

  public signIn(): void {
    this.loading = true;
    this.cognitoService.singIn(this.user)
      .then(() => {
        this.router.navigate(['/dashboard/1']);
      })
      .catch((err) => {
        console.log(err);
        this.loading = false;
      })
  }

}
