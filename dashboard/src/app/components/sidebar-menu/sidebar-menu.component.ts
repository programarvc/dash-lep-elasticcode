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

declare var bootstrap: any; // Declare bootstrap

@Component({
  selector: 'app-sidebar-menu',
  templateUrl: './sidebar-menu.component.html',
  styleUrls: ['./sidebar-menu.component.sass']
})
export class SidebarMenuComponent implements OnInit {
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
  public isEsteiraSelected: boolean = false;

  constructor(
    private cognitoService: CognitoService,
    private userService: UserService,
    private router: Router,
    private timeService: TimeService,
    public sidebarButtonService: SidebarButtonService,
    private esteiraService: EsteiraService
  ) { }

  ngOnInit(): void {
    console.log("esteira selecionada ", this.esteiraSelecionada);
    this.cognitoService.getLoggedInUsername().then((username: any) => {
      this.username = username;

      this.userService.getUsuarioIdPorUsername(this.username).subscribe((userId: any) => {
        this.userId = userId;

        this.userService.getEsteirasPorUsuarioId(this.userId).subscribe((userEsteiras: any) => {
          this.userEsteiras = userEsteiras;
          console.log(this.userEsteiras);
        });
      });
    });
  }

  public signOut() {
    this.cognitoService.signOut()
      .then(() => {
        this.router.navigate(['/login']);
      });
  }

  public async devDash() {
    this.sidebarButtonService.setSelectedButton('botao-dois');
    if (!this.isEsteiraSelected) {
      this.openModal();
    } else {
      this.router.navigate([`/time/${this.esteiraSelecionada.id}`]);
    }
  }

  public async dashboard() {
    this.sidebarButtonService.setSelectedButton('botao-um');
    if (!this.isEsteiraSelected) {
      this.openModal();
    } else {
      this.router.navigate([`/dashboard/${this.esteiraSelecionada.id}`]);
    }
  }

  public async genAi() {
    this.sidebarButtonService.setSelectedButton('botao-tres');
    const username = await this.cognitoService.getLoggedInUsername();
    this.userService.getEsteiraIdAndUsername().subscribe((response) => {
      this.cognitoUser = response;
      const user = this.cognitoUser.find(user => user.username === username);
      if (user) {
        const esteiraId = user.esteiraId || (user.esteira && user.esteira.id);
        if (esteiraId) {
          this.router.navigate(['/genAi-for-devs']);
        }
      }
    });
  }

  public onEsteiraChange(esteira: any): void {
    this.esteiraSelecionada = esteira;
    this.esteiraService.setEsteiraSelecionada(esteira);
    this.isEsteiraSelected = true;
    console.log("esteira está selecionada: ", this.isEsteiraSelected);
    console.log("repasse para componente ", this.esteiraService.esteiraSelecionada$);
    console.log(this.esteiraSelecionada.id);
  }

  private openModal() {
    const modal = new bootstrap.Modal(document.getElementById('selecionarEsteira'));
    modal.show();
  }
}
