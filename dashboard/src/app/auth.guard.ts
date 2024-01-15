import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { CognitoService } from './cognito.service';


@Injectable({
    providedIn: 'root'
})
export class AuthGuard implements CanActivate {
    constructor(private cognitoService: CognitoService, private router: Router) {}

    async canActivate(): Promise<boolean> {
        const isAuthenticated = await this.cognitoService.isAuthenticated();
        if(isAuthenticated){
            return true;
        } else {
            this.router.navigate(['']);
            return false;
        }
    }
}