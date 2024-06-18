import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CognitoService } from 'src/app/cognito.service';
import { SidebarButtonService } from 'src/services/sidedar-button/sidebar-button.service';

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
import { EsteiraService } from 'src/services/esteira/esteira.service';

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
  public selectedButton: string = '';

  public modalId: string = "";
  public username: string = '';
  public userId: number = 0;
  public userEsteiras: any = [];
  public esteiraSelecionada: any = [];

  constructor(
    private cognitoService: CognitoService,
    private userService: UserService,
    private router: Router,
    private timeService: TimeService,
    public sidebarButtonService: SidebarButtonService,
    private esteiraService: EsteiraService
  ) { }

  ngOnInit(): void {
        //Obtém username do usuário logado
        this.cognitoService.getLoggedInUsername().then((username: any) => {
          this.username = username;
    
          //Obtém id do usuário por username
          this.userService.getUsuarioIdPorUsername(this.username).subscribe((userId: any) => {
            this.userId = userId;
    
            //Obtém esteiras que o usuário está inserido
            this.userService.getEsteirasPorUsuarioId(this.userId).subscribe((userEsteiras: any) => {
              this.userEsteiras = userEsteiras;
              console.log(this.userEsteiras);
            });
          });
        });
  }

  public signOut(){
    this.cognitoService.signOut()
      .then(() => {
        this.router.navigate(['/login']);
      })
  }

  public async devDash() {
    this.sidebarButtonService.setSelectedButton('botao-dois');

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
    this.sidebarButtonService.setSelectedButton('botao-um');

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

  public async genAi() {
    this.sidebarButtonService.setSelectedButton('botao-tres');

    const username = await this.cognitoService.getLoggedInUsername();

    this.userService.getEsteiraIdAndUsername().subscribe((response) => {
      this.cognitoUser = response;

      const user = this.cognitoUser.find(user => user.username === username);

      if(user) {
        const esteiraId = user.esteiraId || (user.esteira && user.esteira.id);
        if(esteiraId) {
          this.router.navigate(['/genAi-for-devs']);
        }
      }
    })
  }

  public async registrarDev() {
    this.sidebarButtonService.setSelectedButton('botao-quatro');

    const username = await this.cognitoService.getLoggedInUsername();

    this.userService.getEsteiraIdAndUsername().subscribe((response) => {
      this.cognitoUser = response;

      const user = this.cognitoUser.find(user => user.username === username);

      if(user) {
        const esteiraId = user.esteiraId || (user.esteira && user.esteira.id);
        if(esteiraId) {
          this.router.navigate(['/registrar-desenvolvedor']);
        }
      }
    })
  }

  public onEsteiraChange(esteira: any): void {
    this.esteiraSelecionada = esteira;
    this.esteiraService.setEsteiraSelecionada(esteira);
    console.log("repasse para componente ", this.esteiraService.esteiraSelecionada$)
    console.log(this.esteiraSelecionada);
  }
}
