import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import {
  EsteiraDeDesenvolvimento,
  TiposEnum,
  Empresa,
  Maturidade,
  MaturidadeByEsteiraId,
} from 'src/app/types/esteira-types';

import {
  JornadaDeTransformacao,
  JornadaDeTransformacaoByEsteiraId,
  CapacidadesRecomendadas,
  ItemDeMaturidade,
  TiposMaturidadeEnum,
} from 'src/app/types/jornada-types';

import { EsteiraService } from 'src/services/esteira/esteira.service';
import { EmpresaService } from 'src/services/empresa/empresa.service';
import { MaturidadeService } from 'src/services/maturidade/maturidade.service';
import { JornadaService } from 'src/services/jornada/jornada.service';
import { CapacidadeService } from 'src/services/capacidade/capacidade.service';
import { isInteger } from '@ng-bootstrap/ng-bootstrap/util/util';

@Component({
  selector: 'app-dash-projeto',
  templateUrl: './dash-projeto.component.html',
  styleUrls: ['./dash-projeto.component.sass'],
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
  };

  public currentJornada: JornadaDeTransformacao = {
    id: 0,
    capacidadeDora: 0,
    metricas4: 0,
    saude: 0,
    maturidade: {
      id: 0,
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
    },
    mediaDeJornada: 0,
  };

  public currentCapacidade: CapacidadesRecomendadas = {
    maturidade: {
      id: 0,
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
    },
    itemDeMaturidade: {
      id: 0,
      tipoMaturidade: '' as TiposMaturidadeEnum,
      nome: '',
    },
    id: 0,
  };

  public empresas: Empresa[] = [];
  public maturidade: Maturidade[] = [];
  public tipo: TiposEnum[] = [];
  public tiposMaturidade: TiposMaturidadeEnum[] = [];
  public maturidadeByEsteiraId: MaturidadeByEsteiraId[] = [];
  public jornada: JornadaDeTransformacao[] = [];
  public jornadaByEsteiraId: JornadaDeTransformacaoByEsteiraId[] = [];
  public jornadaGoal: number = 95;
  public rateSaude: number = 30;
  public rate4key: number = 50;
  public rateCapacidadeDora: number = 80;
  public capacidade: CapacidadesRecomendadas[] = [];

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private esteiraService: EsteiraService,
    private maturidadeService: MaturidadeService,
    private jornadaService: JornadaService,
    private capacidadeService: CapacidadeService
  ) {}

  ngOnInit(): void {
    this.getJornadas();
    this.getCapacidades();
    this.getMaturidade();
    this.route.paramMap.subscribe((params) => {
      const id = params.get('esteiraId');
      if (id && !isNaN(Number(id))) {
        this.setCurrent(parseInt(id));
        this.getMaturidadeByEsteiraId(parseInt(id));
        this.getMaturidadeById(parseInt(id));
        this.getCapacidadesByEsteiraId(parseInt(id));
        this.getCapacidadeById(parseInt(id));
      }
    });
  }

  public async setCurrent(id: number) {
    const maturidade = this.maturidade.find(
      (maturidade) => maturidade.esteira.id === id
    );
    if (maturidade) {
      this.currentMaturidade = maturidade;
      this.currentEsteira = maturidade.esteira;
      this.getMaturidadeByEsteiraId(maturidade.esteira.id);
    }
    const jornada = this.jornada.find(
      (jornada) => jornada.maturidade.esteira.id === id
    );
    if (jornada && jornada.maturidade && jornada.maturidade.esteira) {
      this.currentJornada = jornada;
      this.currentJornada.maturidade.esteira = jornada.maturidade.esteira;
      this.getJornadaByEsteiraId(jornada.maturidade.esteira.id);
      console.log(this.currentJornada);
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
  getMaturidadeByEsteiraId(id: number): void {
    this.maturidadeService
      .getMaturidadeByEsteiraId(id)
      .subscribe((maturidade) => {
        this.maturidadeByEsteiraId = maturidade;
      });
  }

  getMaturidadeById(id: number): void {
    this.maturidadeService
      .getMaturidadeById(id)
      .subscribe((data: Maturidade) => {
        this.currentMaturidade = data;
      });
  }

  getJornadaByEsteiraId(id: number): void {
    this.jornadaService.getJornadasByEsteiraId(id).subscribe((jornada) => {
      this.jornadaByEsteiraId = jornada;
    });
  }

  public getJornadas(): void {
    this.jornadaService.getJornadas().subscribe((response) => {
      this.jornada = response;
    });
  }

  public getCapacidades(): void {
    this.capacidadeService.getCapacidades().subscribe((response) => {
      this.capacidade = response;
    });
  }

  getCapacidadesByEsteiraId(id: number): void {
    this.capacidadeService
      .getCapacidadesByEsteiraId(id)
      .subscribe((capacidade) => {
        this.currentCapacidade = capacidade;
      });
  }

  getCapacidadeById(id: number): void {
    this.capacidadeService
      .getCapacidadeById(id)
      .subscribe((data: CapacidadesRecomendadas) => {
        this.currentCapacidade = data;
      });
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

  getNivel(rate: number): string {
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
