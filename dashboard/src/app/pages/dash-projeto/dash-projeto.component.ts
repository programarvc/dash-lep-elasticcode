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
  VcsPullRequest60Days
} from 'src/app/types/jornada-types';

import {
  ValorDosIndicesDeMaturidade,
  ValorDosIndicesDeMaturidadeByEsteiraIdAndTecnica,
  ValorDosIndicesDeMaturidadeFilter,
  VcsPullRequestTop5,
  PullRequest
} from 'src/app/types/valorMaturidade-types';

import { 
  JiraActivities,
  JiraEpics,
  AllActivities,
  AllActivitiesLast60Days,
  MediaStoriesPerEpic,
  StoriesAndEpicsData,
  TotalPoints,
  TotalPointsLast60Days
} from 'src/app/types/jiraActivities-types';

import { EsteiraService } from 'src/services/esteira/esteira.service';
import { EmpresaService } from 'src/services/empresa/empresa.service';
import { MaturidadeService } from 'src/services/maturidade/maturidade.service';
import { CapacidadeService } from 'src/services/capacidade/capacidade.service';
import { isInteger } from '@ng-bootstrap/ng-bootstrap/util/util';
import { valorMaturidadeService } from './../../../services/valor-maturidade/valor-maturidade.service';
import { jiraActivitieseService } from 'src/services/jira-activities/jira-activities.service';
import { NgbModal, NgbDateStruct, NgbDate } from '@ng-bootstrap/ng-bootstrap';

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
    dataHora: [],
    numero: 0,
    leadTime: 0,
    frequencyDeployment: 0,
    changeFailureRate: 0,
    timeToRecovery: 0,
    saude: 0,
    metricas4: 0,
    capacidadeDora: 0,
    mediaDeJornada: 0
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

      dataHora: [],
      numero: 0,
      leadTime: 0,
      frequencyDeployment: 0,
      changeFailureRate: 0,
      timeToRecovery: 0,

      saude: 0,
      metricas4: 0,
      capacidadeDora: 0,
      mediaDeJornada: 0
    },
    itemDeMaturidade: {
      id: 0,
      tipoMaturidade: '' as TiposMaturidadeEnum,
      nome: '',
    },
    valorAtingido: 0,
    valorEsperado: 0,
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
      hora: '',
    numero: 0,
    leadTime: 0,
    frequencyDeployment: 0,
    changeFailureRate: 0,
    timeToRecovery: 0,

    saude: 0,
    metricas4: 0,
    capacidadeDora: 0,
    mediaDeJornada: 0
    },

    itemDeMaturidade: {
      id: 0,
      tipoMaturidade: '' as TiposMaturidadeEnum,
      nome: '',
    },
    id: 0,
  };

  public currentFilterValorMaturidade: ValorDosIndicesDeMaturidadeFilter = {
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
      dataHora: [],
      numero: 0,
      leadTime: 0,
      frequencyDeployment: 0,
      changeFailureRate: 0,
      timeToRecovery: 0,
      saude: 0,
      metricas4: 0,
      capacidadeDora: 0,
      mediaDeJornada: 0
    },
    itemDeMaturidade: {
      id: 0,
      tipoMaturidade: '' as TiposMaturidadeEnum,
      nome: '',
    },
    valorAtingido: 0,
    valorEsperado: 0,
    tipoMaturidade: '',
    nome: '',
    dataHora: []
  };

  public currentVcsPullRequest60Days: VcsPullRequest60Days = {
    countpr60days: 0,
    countpr90days: 0,
    countpr30days: 0
  };

  public pullRequest: PullRequest = {
    totalprs: 0
  };

  public jiraActivities: JiraActivities = {
    id: 0,
    epic: '',
    parent: '',
    name: '',
    priority: '',
    sprint: '',
    typeDetail: '',
    statusDetail: '',
    story_count: 0
  };

  public jiraAllActivities: AllActivities = {
    epic: '',
    name: '',
    story_count: 0
  };

  public jiraMediaStories: MediaStoriesPerEpic = {
    media_stories: 0
  };

  public storiesAndEpicsData: StoriesAndEpicsData = {
    average_points: 0,
    total_points: 0,
    total_stories: 0
  };

  public TotalPoints: TotalPoints = {
    total_points: 0,
  };

  public TotalPointsLast60Days: TotalPointsLast60Days = {
    total_points_last60days: 0
  };

  public allActivities60days: AllActivitiesLast60Days = {
    story_count_last60days: 0
  };


  public jiraEpics: JiraEpics = {
    id: 0,
    epic: '',
    name: '',
    count_epics: 0
  };

  //variavel com dados para armazenar a quantidade total de prs por colaborador Hasura
  currentVcsPullRequestTop5: VcsPullRequestTop5[] = [];

  public empresas: Empresa[] = [];
  public maturidade: Maturidade[] = [];
  public capacidade: CapacidadesRecomendadas[] = [];
  public tipo: TiposEnum[] = [];
  public tiposMaturidade: TiposMaturidadeEnum[] = [];
  public maturidadeByEsteiraId: MaturidadeByEsteiraId[] = [];
  public itemDeMaturidade: ItemDeMaturidade[] = [];
  public valorMaturidadeTecnica: ValorDosIndicesDeMaturidadeByEsteiraIdAndTecnica[] = [];
  public valorMaturidadeCultura: ValorDosIndicesDeMaturidadeByEsteiraIdAndCultura[] = [];
  public valorMaturidadeC:  ValorDosIndicesDeMaturidade[] = [];
  public selectedOption: number;
  public filterValorMaturidade: ValorDosIndicesDeMaturidadeFilter[] = [];
  public valorMaturidadeDate: Date;
  public formattedDate: string | null = null;
 
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private esteiraService: EsteiraService,
    private maturidadeService: MaturidadeService,
    private capacidadeService: CapacidadeService,
    private valorMaturidadeService: valorMaturidadeService,
    private jiraActivitiesService: jiraActivitieseService,
    private modalService: NgbModal
  ) {
    this.selectedOption = 0;
    this.valorMaturidadeDate = new Date();
  }

  ngOnInit(): void {
    this.getCapacidades();
    this.getValorMaturidades();
    this.getMaturidade();
    this.route.paramMap.subscribe((params) => {
      const id = params.get('esteiraId');
      if (id && !isNaN(Number(id))) {
        this.setCurrent(parseInt(id));
        this.getLatestMaturidadeByEsteiraId(parseInt(id));
        this.getLatestCapacidadesByEsteiraId(parseInt(id));
        this.getValorMaturidadesByEsteiraIdAndTecnica(parseInt(id));
        this.getValorMaturidadesByEsteiraIdAndCultura(parseInt(id));
        this.getMaturidadeByEsteiraId(parseInt(id));
        
      }
      const maturidadeId = params.get('maturidadeId');
      if (maturidadeId ) {
        this.setCurrentMaturidade(parseInt(maturidadeId));

      }
    });
  }

public async setCurrent(id: number) {
  this.getLatestMaturidadeByEsteiraId(id);
  this.getLatestCapacidadesByEsteiraId(id);
  this.getValorMaturidadesByEsteiraIdAndTecnica(id);
  this.getValorMaturidadesByEsteiraIdAndCultura(id);
  this.getMaturidadeByEsteiraId(id);
  this.getPrCountLast30And60And90Days();
  this.getTop5ColaboradoresByPrs();
  this.getTotalPrs();
  this.getJiraStories();
  this.getcountAllStories();
  this.getAllActivitiesLast60Days();
  this.getTotalPointsForJiraStoriesLast60Days();
  this.getAveragePoints();
  this.getTotalPointsForJiraStories();
  this.getJiraEpicsLast60Days();
}

public async setCurrentMaturidade (id: number) {
  const maturidade = this.valorMaturidadeC.find(
    (maturidade) => maturidade.id === id
  );
  if (maturidade) {
    this.currentValorMaturidade = maturidade;
    this.getValorDoIndicesByMaturidadeId(maturidade.id);
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

  getLatestMaturidadeByEsteiraId(id: number): void {
    this.maturidadeService
      .getLatestMaturidadeByEsteiraId(id)
      .subscribe((maturidade) => {
        this.currentMaturidade = maturidade;
      });
  }


  getMaturidadeByEsteiraId(id: number): void {
    this.maturidadeService
      .getMaturidadeByEsteiraId(id)
      .subscribe((maturidadeArray: MaturidadeByEsteiraId[]) => {
        maturidadeArray.forEach((maturidadeData: MaturidadeByEsteiraId) => {
          let dateParts = maturidadeData.dataHora;
          let milliseconds = Number(dateParts[6]) / 1000000; // Convert nanoseconds to milliseconds
          let date = new Date(Number(dateParts[0]), Number(dateParts[1]) - 1, Number(dateParts[2]), Number(dateParts[3]), Number(dateParts[4]), Number(dateParts[5]), milliseconds);
          maturidadeData.dataHora = date.toISOString();
        });
        this.maturidadeByEsteiraId = maturidadeArray;
        if(this.maturidadeByEsteiraId.length > 0) {
          this.selecionarMaturidade(this.maturidadeByEsteiraId[0].id);
        }
        
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
    this.valorMaturidadeService.getValoresByEsteiraIdAndTipoMaturidadeTecnicaLatest(id).subscribe((response) =>{
      this.valorMaturidadeTecnica = response;
    });
  }

  public getValorMaturidadesByEsteiraIdAndCultura(id: number): void{
    this.valorMaturidadeService.getValorMaturidadesByEsteiraIdAndCulturaLatest(id).subscribe((response) =>{
      this.valorMaturidadeCultura = response;
    });
  }

  getValorDoIndicesByMaturidadeId(maturidadeId: number): void {
    this.valorMaturidadeService
      .getValorDoIndicesByMaturidadeId(maturidadeId)
      .subscribe((valorMaturidade) => {
        this.valorMaturidadeC = valorMaturidade;
        
      });
  }

  getCapacidadesByEsteiraId(id: number): void {
    this.capacidadeService
      .getCapacidadesByEsteiraId(id)
      .subscribe((capacidade) => {
        this.currentCapacidade = capacidade;
      });
  }

  getLatestCapacidadesByEsteiraId(id : number): void{
    this.capacidadeService
      .getLatestCapacidadesByEsteiraId(id)
      .subscribe((capacidade) =>{
      this.currentCapacidade = capacidade;
    });
  }

  getCorJornada(jornadaGoal: number | undefined, nivel: string): string | undefined {
    if (jornadaGoal === null || jornadaGoal === undefined) {
      return '#8892A7';
    } else if (jornadaGoal >= 0 && jornadaGoal <= 25 && nivel === 'Baixa') {
      return '#12C3FF';
    } else if (jornadaGoal >= 26 && jornadaGoal <= 50 && nivel === 'Média') {
      return '#12C3FF';
    } else if (jornadaGoal >= 51 && jornadaGoal <= 75 && nivel === 'Alta') {
      return '#12C3FF';
    } else if (jornadaGoal >= 76 && nivel === 'Elite') {
      return '#12C3FF';
    } else {
      return undefined;
    }
  }

  getNivel(rate: number | undefined): string | undefined {
    if (rate === null || rate === undefined){
      return 'sem dado';
    } else if (rate >= 0 && rate <= 25) {
      return 'Baixo';
    } else if (rate >= 26 && rate <= 75) {
      return 'Médio';
    } else if (rate >= 76 && rate <= 100) {
      return 'Alto';
    } else {
      return undefined;
    }
  }

  getFontSize (size: number | undefined): string | undefined {
    if (size === null || size === undefined){
      return '25px';
    } else {
      return '40px';
    }
  }

  getFontSizeFailureRate (size: number | undefined): string | undefined {
    if (size === null || size === undefined){
      return '25px';
    } else {
      return '39px';
    }
  }

  getFontSizeSaude (size: number | undefined): string | undefined {
    if (size === null || size === undefined){
      return '25px';
    } else {
      return '16px';
    }
  }

  getFontSizeMetricas (size: number | undefined): string | undefined {
    if (size === null || size === undefined){
      return '14px';
    } else {
      return '12px';
    }
  }

  public selecionarMaturidade (id?: number) {
   
    if (id) {
      const maturidade = this.maturidadeByEsteiraId.find((maturidade) => maturidade.id === id);
      if (maturidade) {
        let date = new Date(maturidade.dataHora);
        let timestamp = date.getTime();
        this.currentMaturidade = {
          ...maturidade,
          dataHora: [timestamp]
        };
        this.maturidadeService.setCurrentMaturidade(this.currentMaturidade);
        this.formattedDate = this.currentMaturidade.dataHora[0].toString();
        if(this.currentMaturidade) {
          this.getValorDoIndicesByMaturidadeId(this.currentMaturidade.id);
        }
      }
    } else {
      this.getMaturidade();
      this.currentMaturidade = {
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
        dataHora: [],
        numero: 0,
        leadTime: 0,
        frequencyDeployment: 0,
        changeFailureRate: 0,
        timeToRecovery: 0,
        saude: 0,
        metricas4: 0,
        capacidadeDora: 0,
        mediaDeJornada: 0
      };
      }
    }

  getPrCountLast30And60And90Days(): void {
    this.maturidadeService.getPrCountLast30And60And90Days().subscribe((response) => {
      this.currentVcsPullRequest60Days = response;
    });
  }

  getTop5ColaboradoresByPrs(): void {
    this.valorMaturidadeService.getTop5ColaboradoresByPrs().subscribe((response) => {
      this.currentVcsPullRequestTop5 = response;
    });
  }

  getTotalPrs(): void {
    this.valorMaturidadeService.getTotalPrs().subscribe((response) => {
      this.pullRequest = response[0];
    });
  }

  public getJiraStories(): void {
    this.jiraActivitiesService.getCountStories().subscribe((response) => {
      this.jiraActivities = response;
      console.log(this.jiraActivities);
    });
  }



  public getcountAllStories(): void {
    this.jiraActivitiesService.getcountAllStories().subscribe((response) => {
      this.jiraAllActivities = response;
    });
  }

  public getAllActivitiesLast60Days(): void {
    this.jiraActivitiesService.getAllActivitiesLast60Days().subscribe((response) => {
      this.allActivities60days = response;
    });
  }


  public getJiraEpicsLast60Days(): void {
    this.jiraActivitiesService.getEpicsLast60Days().subscribe((response) => {
      this.jiraEpics = response;
    });
  }

  public getAveragePoints(): void {
    this.jiraActivitiesService.getAveragePoints().subscribe((response) => {
      this.storiesAndEpicsData = response;
    });
  }

  public getTotalPointsForJiraStories(): void {
    this.jiraActivitiesService.getTotalPointsForJiraStories().subscribe((response) => {
      this.TotalPoints = response;
    });
  }


  public getTotalPointsForJiraStoriesLast60Days(): void {
    this.jiraActivitiesService.getTotalPointsForJiraStoriesLast60Days().subscribe((response) => {
      this.TotalPointsLast60Days = response;
    });
  }

  open(content: any) {
    this.modalService.open(content);
  }
  
}
