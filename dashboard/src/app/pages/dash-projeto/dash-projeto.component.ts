import { ValorDosIndicesDeMaturidadeByEsteiraIdAndCultura } from './../../types/valorMaturidade-types';
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

import {
  ValorDosIndicesDeMaturidade,
  ValorDosIndicesDeMaturidadeByEsteiraIdAndTecnica
} from 'src/app/types/valorMaturidade-types';


import { EsteiraService } from 'src/services/esteira/esteira.service';
import { EmpresaService } from 'src/services/empresa/empresa.service';
import { MaturidadeService } from 'src/services/maturidade/maturidade.service';
import { JornadaService } from 'src/services/jornada/jornada.service';
import { CapacidadeService } from 'src/services/capacidade/capacidade.service';
import { isInteger } from '@ng-bootstrap/ng-bootstrap/util/util';
import { valorMaturidadeService } from './../../../services/valor-maturidade/valor-maturidade.service';

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


  public valorMaturidades: ValorDosIndicesDeMaturidade[] = [];
  public currentValorMaturidade: ValorDosIndicesDeMaturidade = {
    id: 0,
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
    valorAtingido: 0,
    valorEsperado: 0,
  };

  public empresas: Empresa[] = [];
  public maturidade: Maturidade[] = [];
  public tipo: TiposEnum[] = [];
  public tiposMaturidade: TiposMaturidadeEnum[] = [];
  public maturidadeByEsteiraId: MaturidadeByEsteiraId[] = [];
  public jornadas: JornadaDeTransformacao[] = [];
  public jornadaByEsteiraId: JornadaDeTransformacaoByEsteiraId[] = [];
  public capacidade: CapacidadesRecomendadas[] = [];
  public itemDeMaturidade: ItemDeMaturidade[] = [];
  public valorMaturidadeTecnica: ValorDosIndicesDeMaturidadeByEsteiraIdAndTecnica[] = [];
  public valorMaturidadeCultura: ValorDosIndicesDeMaturidadeByEsteiraIdAndCultura[] = [];
  public valorMaturidadeC:  ValorDosIndicesDeMaturidade[] = [];

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private esteiraService: EsteiraService,
    private maturidadeService: MaturidadeService,
    private jornadaService: JornadaService,
    private capacidadeService: CapacidadeService,
    private valorMaturidadeService: valorMaturidadeService
  ) {}

  ngOnInit(): void {
    this.getJornadas();
    this.getCapacidades();
    this.getValorMaturidades();
    this.getMaturidade();
    this.route.paramMap.subscribe((params) => {
      const id = params.get('esteiraId');
      if (id && !isNaN(Number(id))) {
        this.setCurrent(parseInt(id));
        this.getMaturidadeByEsteiraId(parseInt(id));
        this.getMaturidadeById(parseInt(id));
        this.getCapacidadesByEsteiraId(parseInt(id));
        this.getJornadaByEsteiraId(parseInt(id));
        this.getValorMaturidadesByEsteiraIdAndTecnica(parseInt(id));
        this.getValorMaturidadesByEsteiraIdAndCultura(parseInt(id));


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

      this.getJornadaByEsteiraId(id);
      console.log(this.currentJornada);


    const valorMaturidadeTecnica = this.valorMaturidades.find(
      (valorMaturidade) => valorMaturidade.maturidade.esteira.id === id
    );
    if (valorMaturidadeTecnica && valorMaturidadeTecnica.maturidade && valorMaturidadeTecnica.maturidade.esteira){
      this.currentValorMaturidade = valorMaturidadeTecnica;
      this.currentValorMaturidade.maturidade.esteira = valorMaturidadeTecnica.maturidade.esteira;
      this.getValorMaturidadesByEsteiraIdAndTecnica(valorMaturidadeTecnica.maturidade.esteira.id);

    }
    const valorMaturidadeCultura = this.valorMaturidadeC.find(
      (valorMaturidadeCultura) => valorMaturidadeCultura.maturidade.esteira.id === id
    );
    if (valorMaturidadeCultura&& valorMaturidadeCultura.maturidade && valorMaturidadeCultura.maturidade.esteira){
      this.currentValorMaturidade = valorMaturidadeCultura;
      this.currentValorMaturidade.maturidade.esteira = valorMaturidadeCultura.maturidade.esteira;
      this.getValorMaturidadesByEsteiraIdAndCultura(valorMaturidadeCultura.maturidade.esteira.id);
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
      this.currentJornada = jornada;
      console.log(this.currentJornada);

    });
  }

  public getJornadas(): void {
    this.jornadaService.getJornadas().subscribe((response) => {
      this.jornadas = response;
    });
  }

  public getCapacidades(): void {
    this.capacidadeService.getCapacidades().subscribe((response) => {
      this.capacidade = response;
    });
  }

  public getValorMaturidades(): void {
    this.valorMaturidadeService.getValorMaturidades().subscribe((response) => {
      this.valorMaturidades = response;
    });
  }


  public getValorMaturidadesByEsteiraIdAndTecnica(id: number): void{
    this.valorMaturidadeService.getValorMaturidadesByEsteiraIdAndTecnica(id).subscribe((response) =>{
      this.valorMaturidadeTecnica = response;
    });
  }

  public getValorMaturidadesByEsteiraIdAndCultura(id: number): void{
    this.valorMaturidadeService.getValorMaturidadesByEsteiraIdAndCultura(id).subscribe((response) =>{
      this.valorMaturidadeCultura = response;
      console.log(this.valorMaturidadeCultura);
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
