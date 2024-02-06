import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CognitoService } from 'src/app/cognito.service';

import {
  UserEsteira,
  EsteiraDeDesenvolvimento,
  TiposEnum
} from './../../types/usuario';

import {
  TimeColaborador,
  Colaborador,
  Habilidade,
  Empresa,
} from './../../types/time-types';

import { UserService } from 'src/services/usuario/usuario.service';
import { TimeService } from 'src/services/time/time.service';

@Component({
  selector: 'app-sidebar-menu',
  templateUrl: './sidebar-menu.component.html',
  styleUrls: ['./sidebar-menu.component.sass']
})
export class SidebarMenuComponent {
  public cognitoUser: UserEsteira[] = [];
  public esteiras: EsteiraDeDesenvolvimento[] = [];
  public tipo: TiposEnum[] = [];
  public timesColaborador: TimeColaborador[] = [];
  public colaborador: Colaborador[] = [];
  public habilidades: Habilidade[] = [];
  public empresa: Empresa[] = [];

  constructor(
    private cognitoService: CognitoService,
    private userService: UserService,
    private router: Router,
    private timeService: TimeService
  ) { }

  public signOut(){
    this.cognitoService.signOut()
      .then(() => {
        this.router.navigate(['/login']);
      })
  }

  public async devDash() {
    const username = await this.cognitoService.getLoggedInUsername();

    this.userService.getEsteiraIdAndUsername().subscribe((response) => {
      this.cognitoUser = response;

      const user = this.cognitoUser.find(user => user.username === username);

      if(user) {
        const esteiraId = user.esteiraId || (user.esteira && user.esteira.id);
        if(esteiraId) {
          this.router.navigate([`/time/${esteiraId}`]);
        }
      }
    })
  }

  public async getEsteiraIdAndUsername(){
    const username = await this.cognitoService.getLoggedInUsername();

    this.userService.getEsteiraIdAndUsername().subscribe((response) => {
      this.cognitoUser = response;

      const user = this.cognitoUser.find(user => user.username === username);

      if(user) {
        const esteiraId = user.esteiraId || (user.esteira && user.esteira.id);
        if(esteiraId) {
          this.router.navigate([`/dashboard/${esteiraId}`]);
        }
      }
    })
  }
}
