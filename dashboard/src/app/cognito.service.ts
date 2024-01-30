import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { signIn, signOut, getCurrentUser} from 'aws-amplify/auth';
import { CognitoUser, AuthenticationDetails, CognitoUserPool } from 'amazon-cognito-identity-js';
import { Router } from '@angular/router';
import { cognito } from 'src/environments/environment';
import { resetPassword } from '@aws-amplify/auth';


export interface IUser {
  email: string;
  password: string;
  showPassword: boolean;
  code: string;
  name: string;
}

const poolData = {
  UserPoolId: cognito.UserPoolId,
  ClientId: cognito.userPoolWebClientId,
};

const userPool = new CognitoUserPool(poolData);

@Injectable({
  providedIn: 'root'
})
export class CognitoService {

  private authenticationSubject: BehaviorSubject<any>;

  constructor(private router: Router) { 
    this.authenticationSubject = new BehaviorSubject<boolean>(false);
  }  

  public async signIn(user: IUser): Promise<any> {

    await this.signOut();

    return signIn({username: user.email, password: user.password})
      .then(() => {
        this.authenticationSubject.next(true);
      })
  }

  public async signOut(): Promise<any> {
    return signOut()
      .then(() => {
        this.authenticationSubject.next(false);
      })
  }

  public isAuthenticated(): Promise<boolean> {
    return getCurrentUser()
      .then(user => {
        if(user){
          this.authenticationSubject.next(true);
          return true;
        } else {
          this.authenticationSubject.next(false);
          return false;
        }
      })
  }

  public getLoggedInUsername(): Promise<string | null> {
    return new Promise((resolve, reject) => {
      const currentUser = userPool.getCurrentUser();

      if(currentUser){
        resolve(currentUser.getUsername());
      } else {
        resolve(null);
      }
    })
  }

}
