import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import {
 EsteiraDeDesenvolvimento,
  TiposEnum,
  Empresa,
  Maturidade,
  MaturidadeByEsteiraId,
} from 'src/app/types/esteira-types';

import { Jornada } from 'src/app/types/jornada-types';

import { EsteiraService } from 'src/services/esteira/esteira.service';
import { EmpresaService } from 'src/services/empresa/empresa.service';
import { MaturidadeService } from 'src/services/maturidade/maturidade.service';
import { JornadaService } from 'src/services/jornada/jornada.service';
import { isInteger } from '@ng-bootstrap/ng-bootstrap/util/util';

@Component({
  selector: 'app-dash-projeto',
  templateUrl:'./dash-projeto.component.html',
  styleUrls: ['./dash-projeto.component.sass']
})

export class DashProjetoComponent implements OnInit {
  public esteiras: EsteiraDeDesenvolvimento[] = [];
  public currentEsteira: EsteiraDeDesenvolvimento = {
    id: 0,
    nome: '',
    tipo: '' as TiposEnum,
    empresa: {
      id: 0,
      nome: '',
    },
  };

  public currentEmpresa: Empresa = {
    id: 0,
    nome: '',
  };

  public currentMaturidade: Maturidade = {

    esteira: {
      id: 0,
      nome: '',
      tipo: '' as TiposEnum,
      empresa: {
        id: 0,
        nome: '',
      },
    },
    data: '',
    numero: 0,
    leadTime: 0,
    frequencyDeployment: 0,
    changeFailureRate: 0,
    timeToRecovery: 0,
  }

  public currentJornada: Jornada = {
    id: 0,
    capacidade_dora: 0,
    metricas_4: 0,
    saude: 0,
  };

  public empresas: Empresa[] = [];
  public maturidade: Maturidade[] = [];
  public tipo: TiposEnum[] = [];
  public maturidadeByEsteiraId: MaturidadeByEsteiraId[] = [];
  public jornada: Jornada[] = [];
  public jornadaGoal: number = 95;
  public rateSaude: number = 70;
  public rate4key: number = 50;
  public rateCapacidadeDora: number = 80;
  public capacidadesRecomendadas: string[] = [
    'Gerenciamento de dados',
    'Desenvolvimento base',
    'Manutenção do código'
  ]
  

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private esteiraService: EsteiraService,
    private empresaService: EmpresaService,
    private maturidadeService: MaturidadeService,
    private jornadaService: JornadaService

  ) { }

  ngOnInit(): void {
    this.getMaturidade();
    this.route.paramMap.subscribe((params) => {
      const id = params.get('esteiraId');
      if (id) {
        this.setCurrent(parseInt(id));
        this.getMaturidadeByEsteiraId(parseInt(id));
        this.getMaturidadeById(parseInt(id));
        /*this.getEsteiraById(parseInt(id));*/
      }
    });
  }

  public async setCurrent(id: number) {
    const maturidade= this.maturidade.find(
      (maturidade) => maturidade.esteira.id === id
    );
    if (maturidade) {
      this.currentMaturidade = maturidade;
      this.currentEsteira = maturidade.esteira;

console.log(this.currentMaturidade);
console.log(this.currentEsteira);
    }
  }

  public async getMaturidade() {
    this.esteiraService.getEsteiras().subscribe((response) => {
      this.esteiras = response;
      const id = this.route.snapshot.paramMap.get('esteiraId');
      if (id) {
        this.setCurrent(parseInt(id));
      } else {
        if (this.esteiras.length > 0) {
          this.setCurrent(this.esteiras[0].id);
          this.router.navigate([`dashboard/${this.esteiras[0].id}`]);
        }
      }
    });
  }

  /*getEsteiras(): void {
    this.esteiraService.getEsteiras().subscribe((esteiras) => {
      this.esteiras = esteiras;
    });
  }*/

  /*getEsteiraById(id: number): void {
    this.esteiraService.getEsteiraById(id).subscribe((esteira) => {
      this.esteiras = esteira;
    });
  }*/

  getMaturidadeByEsteiraId(id: number): void {
    this.maturidadeService.getMaturidadeByEsteiraId(id).subscribe((maturidade) => {
      this.maturidadeByEsteiraId = maturidade;
    });
  }

  getMaturidadeById(id: number): void {
    this.maturidadeService.getMaturidadeById(id).subscribe((data: Maturidade) => {
      this.currentMaturidade = data;
    });
  }

  getJornadaByEsteiraId(id: number) : void {
    this.jornadaService.getJornadaByEsteiraId(id).subscribe((jornada) => {
      this.jornadaByEsteiraId = jornada;
    })
  }

  getCorJornada(jornadaGoal: number, nivel: string): string {
    if (jornadaGoal >= 0 && jornadaGoal <= 25 && nivel === 'Baixa') {
      return '#12C3FF';
    } else if (jornadaGoal >= 26 && jornadaGoal <= 50 && nivel === 'Média') {
      return '#12C3FF';
    } else if (jornadaGoal >= 51 && jornadaGoal <= 75 && nivel === 'Alta') {
      return '#12C3FF';
    } else if (jornadaGoal >= 76 && nivel === 'Elite') {
      return '#12C3FF';
    } else {
      return '#8892A7';
    }
  }

  getNivelSaude(rate: number): string {
    if (rate >= 0 && rate <= 25) {
      return 'Baixo';
    } else if (rate >= 26 && rate <= 75) {
      return 'Médio';
    } else if (rate >= 76 && rate <= 100) {
      return 'Alto';
    } else {
      return '';
    }
  }

  get4Key(rate: number): string {
    if (rate >= 0 && rate <= 25) {
      return 'Baixo';
    } else if (rate >= 26 && rate <= 75) {
      return 'Médio';
    } else if (rate >= 76 && rate <= 100) {
      return 'Alto';
    } else {
      return '';
    }
  }

  getCapacidadeDora(rate: number): string {
    if (rate >= 0 && rate <= 25) {
      return 'Baixo';
    } else if (rate >= 26 && rate <= 75) {
      return 'Médio';
    } else if (rate >= 76 && rate <= 100) {
      return 'Alto';
    } else {
      return '';
    }
  }
}