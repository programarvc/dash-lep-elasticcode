import { Component, OnInit, Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

import { Amplify} from 'aws-amplify';
import { sessionStorage, Hub } from 'aws-amplify/utils';
import { cognitoUserPoolsTokenProvider } from 'aws-amplify/auth/cognito';
import { getCurrentUser, fetchAuthSession, signIn, type SignInInput } from 'aws-amplify/auth';
import { signOut } from 'aws-amplify/auth';

import { awsAuthConfig } from '../../../environments/environment';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.sass']
})

@Injectable({
  providedIn: 'root'
})

export class LoginComponent implements OnInit {

  constructor(private router: Router) {
    Amplify.configure({
      Auth: {
        Cognito: {
          //  Amazon Cognito User Pool ID
          userPoolId: awsAuthConfig.userPoolId,
          // OPTIONAL - Amazon Cognito Web Client ID (26-char alphanumeric string)
          userPoolClientId: awsAuthConfig.userPoolWebClientId,
          // 'code' is used for Auth.confirmSignUp, 'link' is used for email link verification
          signUpVerificationMethod: 'link', // 'code' | 'link'
          loginWith: {
            // OPTIONAL - Hosted UI configuration
            oauth: {
              domain: 'your_cognito_domain',
              scopes: [
                'phone',
                'email',
                'profile',
                'openid',
                'aws.cognito.signin.user.admin'
              ],
              redirectSignIn: ['http://localhost:4200/dashboard/1'],
              redirectSignOut: ['http://localhost:3000/'],
              responseType: 'code' // or 'token', note that REFRESH token will only be generated when the responseType is code
            }
          }
        }
      }
    });

  const currentConfig = Amplify.getConfig();
  }

  ngOnInit(): void {
    async function currentAuthenticatedUser() {
      try {
        const { username, userId, signInDetails } = await getCurrentUser();
        console.log(`The username: ${username}`);
        console.log(`The userId: ${userId}`);
        console.log(`The signInDetails: ${signInDetails}`);
      } catch (err) {
        console.log(err);
      }
    }
    currentAuthenticatedUser();

    async function handleSignOut() {
      try {
        await signOut();
      } catch (error) {
        console.log('error signing out: ', error);
      }
    }
    handleSignOut();

    async function currentSession() {
      try {
        const { accessToken, idToken } = (await fetchAuthSession()).tokens ?? {};
      } catch (err) {
        console.log(err);
      }
    }
    currentSession();

    async function handleSignIn({ username, password }: SignInInput) {
      try {
        const { isSignedIn, nextStep } = await signIn({ username, password });
      } catch (error) {
        console.log('error signing in', error);
      }
    }

    /*Hub.listen('auth', (data) => {
      const { payload: { event } } = data;
      switch (event) {
        case 'signedIn':
          window.location.href =  'http://localhost:4200/dashboard/1';
      }
    })*/

    cognitoUserPoolsTokenProvider.setKeyValueStorage(sessionStorage);
  }

}
