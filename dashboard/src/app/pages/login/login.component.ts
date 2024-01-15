import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { CognitoService } from 'src/app/cognito.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.sass']
})

export class LoginComponent implements OnInit {

  isAuthenticated: boolean = false;

  constructor (private cognitoService: CognitoService, private router: Router) {
    this.isAuthenticated = false;
  }

  public ngOnInit(): void {
    this.cognitoService.isAuthenticated() 
      .then((success: boolean) => {
        this.isAuthenticated = success;
      });
  }

  public signOut(): void {
    this.cognitoService.signOut()
      .then(() => {
        this.router.navigate(['/login']);
      });
  }
}
