import { Component, OnInit} from '@angular/core';
import { ActivatedRoute, Router,  } from '@angular/router';

import {
  EsteiraDeDesenvolvimento,
  TiposEnum,
  Empresa,
  Maturidade,
  MaturidadeByEsteiraId
} from 'src/app/types/esteira-types';

import {
  JornadaDeTransformacao,
  JornadaDeTransformacaoByEsteiraId,
  CapacidadesRecomendadas,
  ItemDeMaturidade,
  TiposMaturidadeEnum
} from 'src/app/types/jornada-types';

import { EsteiraService } from 'src/services/esteira/esteira.service';
import { EmpresaService } from 'src/services/empresa/empresa.service';
import { MaturidadeService } from 'src/services/maturidade/maturidade.service';
import { JornadaService } from 'src/services/jornada/jornada.service';
import { CapacidadeService } from 'src/services/capacidade/capacidade.service';
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
    id: 0
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
  public capacidade: CapacidadesRecomendadas[] = []

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private esteiraService: EsteiraService,
    private maturidadeService: MaturidadeService,
    private jornadaService: JornadaService,
    private capacidadeService: CapacidadeService

  ) { }

  ngOnInit(): void {
    this.getMaturidade();
    this.getJornada();
    this.getCapacidade();
    this.route.paramMap.subscribe((params) => {
      const id = params.get('esteiraId');
      if (id) {
        this.setCurrent(parseInt(id));
        this.getMaturidadeByEsteiraId(parseInt(id));
        this.getMaturidadeById(parseInt(id));
        this.getJornadaByEsteiraId(parseInt(id));
        this.getCapacidadesByEsteiraId(parseInt(id));
        this.getCapacidadeById(parseInt(id));
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
      this.getMaturidadeByEsteiraId(maturidade.esteira.id);
    }

    const jornada = this.jornada.find(
      (jornada) => jornada.maturidade.esteira.id === id
    );
    if (jornada) {
      this.currentJornada = jornada;
      this.currentEsteira = jornada.maturidade.esteira;
      this.getJornadaByEsteiraId(jornada.maturidade.esteira.id);
      console.log(this.getNivel(jornada.saude));
    }

    const capacidade = this.capacidade.find(
      (capacidade) => capacidade.maturidade.esteira.id === id
    );
    if (capacidade) {
      this.currentCapacidade = capacidade;
      this.currentEsteira = capacidade.maturidade.esteira;
      this.getCapacidadesByEsteiraId(capacidade.maturidade.esteira.id);
      console.log(capacidade);
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

  public async getJornada() {
  this.jornadaService.getJornadas().subscribe((response) => {
    this.jornada = response;
    const id = this.route.snapshot.paramMap.get('esteiraId');
    if (id) {
      this.getJornadaByEsteiraId(parseInt(id));
    } else {
      if (this.jornada.length > 0) {
        this.getJornadaByEsteiraId(this.jornada[0].id);
        this.router.navigate([`dashboard/${this.esteiras[0].id}`]);
      }
    }
  });
}

public async getCapacidade() {
  this.capacidadeService.getCapacidades().subscribe((response) => {
    this.capacidade = response;
    const id = this.route.snapshot.paramMap.get('esteiraId');
    if (id) {
      this.getCapacidadesByEsteiraId(parseInt(id));
    } else {
      if (this.capacidade.length > 0) {
        this.getCapacidadesByEsteiraId(this.capacidade[0].id);
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
    this.jornadaService.getJornadasByEsteiraId(id).subscribe((jornada) => {
      this.currentJornada = jornada;
    });
  }

  getCapacidadesByEsteiraId(id: number) : void {
    this.capacidadeService.getCapacidadesByEsteiraId(id).subscribe((capacidade) => {
      this.currentCapacidade = capacidade;
    });
  }

  getCapacidadeById(id: number): void {
    this.capacidadeService.getCapacidadeById(id).subscribe((data: CapacidadesRecomendadas) => {
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
    if ( rate >= 0 && rate <= 25) {
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
