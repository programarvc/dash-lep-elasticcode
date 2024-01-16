import { Component, OnInit } from '@angular/core';
import { CognitoService } from 'src/app/cognito.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-amplify-ui-login',
  templateUrl: './amplify-ui-login.component.html',
  styleUrls: ['./amplify-ui-login.component.sass']
})
export class AmplifyUiLoginComponent implements OnInit {

  isAuthenticated = false;

  constructor(private cognitoService: CognitoService, private router: Router) { }

  ngOnInit(): void {
    this.cognitoService.isAuthenticated() 
      .then((success: boolean) => {
        this.isAuthenticated = success;
      });
  }

}
