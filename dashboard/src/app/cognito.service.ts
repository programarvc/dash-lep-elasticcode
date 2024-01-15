import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { signIn, signOut, getCurrentUser} from 'aws-amplify/auth';


export interface IUser {
  email: string;
  password: string;
  showPassword: boolean;
  code: string;
  name: string;
}

@Injectable({
  providedIn: 'root'
})
export class CognitoService {

  private authenticationSubject: BehaviorSubject<any>;

  constructor() { 
    this.authenticationSubject = new BehaviorSubject<boolean>(false);
  }

  public async singIn(user: IUser): Promise<any> {

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
}
