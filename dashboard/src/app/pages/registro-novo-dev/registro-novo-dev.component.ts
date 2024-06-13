import { Component, OnInit } from '@angular/core';

import {
  HabilidadeService
} from 'src/services/habilidade/habilidade.service';
import {
  UserService
} from 'src/services/usuario/usuario.service';
import {
  CognitoService
} from 'src/app/cognito.service';
import {
  TimeService
} from 'src/services/time/time.service';
import {
  ColaboradorService
} from 'src/services/colaborador/colaborador.service';

@Component({
  selector: 'app-registro-novo-dev',
  templateUrl: './registro-novo-dev.component.html',
  styleUrls: ['./registro-novo-dev.component.sass']
})
export class RegistroNovoDevComponent implements OnInit {
  public nome: string = '';
  public email: string = '';
  public github: string = '';
  public jira: string = '';
  public bio: string = '';

  public stacksDev: any = [];
  public stackSelecionado: number = 0;

  public tecnologiasDev: any = [];
  public tecnologiaSelecionada: any = [];

  public username: string = '';
  public userId: number = 0;
  public userEsteiras: any = [];
  public esteiraSelecionada: number = 0;

  public timesEsteira: any = [];
  public timeSelecionado: number = 0;

  public habilidade: any = [];

  public devCadastrado: any = {};

  constructor(
    private habilidadeService: HabilidadeService,
    private userService: UserService,
    private cognitoService: CognitoService,
    private timeService: TimeService,
    private colaboradorService: ColaboradorService
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
        });
      });
    });

    //Armazena o retorno do méotdo de getStacks() em um stackDev
    this.habilidadeService.getStacks().subscribe((stacks: any) => {
      this.stacksDev = stacks;
    });

    //Armazena o retorno do méotdo de getTecnologias() em um tecnologiasDev
    this.habilidadeService.getTecnologias().subscribe((tecnologias: any) => {
      this.tecnologiasDev = tecnologias;
    });
  }

  public cadastrarDev(): void {
    this.tecnologiaSelecionada = this.tecnologiasDev
      .filter((tecnologia: any) => tecnologia.selecionada)
      .map((tecnologia: any) => tecnologia.id);

    this.habilidade = [this.stackSelecionado];
    this.habilidade = [...this.habilidade, ...this.tecnologiaSelecionada];
    console.log(this.habilidade);

    const devData = {
      nome: this.nome,
      email: this.email,
      github: this.github,
      jira: this.jira,
      miniBio: this.bio,
      habilidade: this.habilidade,
      esteira: this.esteiraSelecionada,
      time: this.timeSelecionado,
    };

    // Registro do novo colaborador
    this.colaboradorService.postNovoColaborador(devData).subscribe((response: any) => {
      this.devCadastrado = response;

      // Itera sobre a lista de habilidades selecionadas e cria um objeto para cada habilidade
      for (let habilidade of this.habilidade) {
        const habilidadesColaborador = {
          colaborador: { id: this.devCadastrado.id },
          habilidade: { id: habilidade }
        };

        //POST para cadastrar habilidades do novo colaborador
        this.habilidadeService.postHabilidadesNovoColaborador(habilidadesColaborador)
          .subscribe((responseHabilidades: any) => {
          }, (erro: any) => {
            console.log("Erro ao cadastrar habilidade", erro);
          });
      }

      //POST para relacionar novo colaborador com time selecionado
      const devTimeData = {
        time: { id: this.timeSelecionado },
        colaborador: { id: this.devCadastrado.id }
      };

      this.timeService.postNovoColaboradorEsteira(devTimeData).subscribe((responseTime: any) => {
      }, (erro: any) => {
        console.log("Erro ao relacionar colaborador com time", erro);
      });


    }, (erro: any) => {
      console.log('Erro ao cadastrar desenvolvedor', erro);
    });
  }

  public onEsteiraChange(eventy: any): void {
    this.esteiraSelecionada = eventy.target.value;
    this.timeService.getTimesByEsteiraId(this.esteiraSelecionada).subscribe((times: any) => {
      this.timesEsteira = times;
    })
  }
}
