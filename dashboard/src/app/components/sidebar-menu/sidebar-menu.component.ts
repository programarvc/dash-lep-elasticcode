import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CognitoService } from 'src/app/cognito.service';

import { 
  UserEsteira,
  EsteiraDeDesenvolvimento,
  TiposEnum
} from './../../types/usuario';

import { UserService } from 'src/services/usuario/usuario.service';


@Component({
  selector: 'app-sidebar-menu',
  templateUrl: './sidebar-menu.component.html',
  styleUrls: ['./sidebar-menu.component.sass']
})
export class SidebarMenuComponent {
  public cognitoUser: UserEsteira[] = [];
  public esteiras: EsteiraDeDesenvolvimento[] = [];
  public tipo: TiposEnum[] = [];

  constructor(private cognitoService: CognitoService, private userService: UserService, private router: Router) { }

  public signOut(){
    this.cognitoService.signOut()
      .then(() => {
        this.router.navigate(['/login']);
      })
  }

  public async getEsteiraIdAndUsername(){
    const username = await this.cognitoService.getLoggedInUsername();

    this.userService.getEsteiraIdAndUsername().subscribe((response) => {
      this.cognitoUser = response;

      const user = this.cognitoUser.find(user => user.username === username);

      if(user) {
        console.log(user);
        const esteiraId = user.esteiraId || (user.esteira && user.esteira.id);
        if(esteiraId) {
          this.router.navigate([`/dashboard/${esteiraId}`]);
        }
        
      }
    })
  }
}
