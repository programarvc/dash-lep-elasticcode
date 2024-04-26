import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {
          Time,
          TimeColaborador,
          EsteiraDeDesenvolvimento,
          TiposEnum,
          Colaborador,
          MetasColaborador,
          MetasOneAOne,
          AllLatestMetaByColaboradorId,
          PrCount,
          IndiceDeSobrevivenciaDev,
          PrFromGithub
         } from 'src/app/types/time-types';
import { TimeService } from 'src/services/time/time.service';
import {  Habilidade,
          HabilidadeByColaborador,
          CompetenciaByColaborador,
          AcaoByColaborador,
          EmpresaByColaborador,
          Empresa,
          Competencia,
          Acao
          } from 'src/app/types/colaborador-types';
import { AcaoService } from 'src/services/acao/acao.service';
import { ColaboradorService } from 'src/services/colaborador/colaborador.service';
import { CompetenciaService } from 'src/services/competencia/competencia.service';
import { EsteiraService } from 'src/services/esteira/esteira.service';
import { HabilidadeService } from 'src/services/habilidade/habilidade.service';

@Component({
  selector: 'app-time',
  templateUrl: './time.component.html',
  styleUrls: ['./time.component.sass']
})
export class TimeComponent implements OnInit {
  public times: TimeColaborador[] = [];
  public timesColaborador: TimeColaborador[] = [];
  public currentTimeColaborador: TimeColaborador = {
    id: 0,
    time: {
      id: 0,
      nomeTime: '',
      esteira: {
        id: 0,
        nome: '',
        tipo: '' as TiposEnum,
        empresa: {
          id: 0,
          nome: '',
        },
      },
    },
    colaborador: {
      id: 0,
      nome: '',
      email: '',
      github: '',
      miniBio: '',
      habilidades: [],
    },
  };

  public timeC: Time[] = [];
  public time: Time[] = [];
  public currentTimes: Time = {
    id: 0,
    nomeTime: '',
    esteira: {
      id: 0,
      nome: '',
      tipo: '' as TiposEnum,
      empresa: {
        id: 0,
        nome: '',
      },
    },
  }
  public currentEmpresa: Empresa = {
    id: 0,
    nome: '',
  };
  public colaboradoresByEsteira: Colaborador[] = [];
  public colaboradores: Colaborador[] = [];
  public currentColaborador: Colaborador = {
    id: 0,
    nome: '',
    email: '',
    github: '',
    miniBio: '',
    habilidades: [],
  };

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
  public metasColaborador: MetasColaborador[] = [];
  public currentMetasColaborador: MetasColaborador = {
    id: 0,
    colaborador: {
      id: 0,
      nome: '',
      email: '',
      github: '',
      miniBio: '',
      habilidades: [],
    },
    meta: {
      id: 0,
      metas: '',
    },
    data: [],
  };

 /* metodo anterior
  public currentPrCount: PrCount = { 
    id: 0,
    colaborador: {
      id: 0,
      nome: '',
      email: '',
      github: '',
      miniBio: '',
      habilidades: [],
    },
    count: 0
  };*/

  currentPrFromGithub: PrFromGithub = {
    id: 0,
    prAuthor: '',
    createdAt: '',
    mergedAt: '',
    repoName: '', 
    colaborador: {
      id: 0,
      nome: '',
      email: '',
      github: '',
      miniBio: '',
      habilidades: [],
    },
    countPr: 0,
    nome: ''
  };

  public  currentValorIndiceDeSobrevivencia: IndiceDeSobrevivenciaDev = {
    id: 0,
    timeColaborador: {
      id: 0,
      time: {
        id: 0,
        nomeTime: '',
        esteira: {
          id: 0,
          nome: '',
          tipo: '' as TiposEnum,
          empresa: {
            id: 0,
            nome: '',
          },
        },
      },
      colaborador: {
        id: 0,
        nome: '',
        email: '',
        github: '',
        miniBio: '',
        habilidades: [],
      },
    },
    valorIndice: 0
  }

  public PrCountLast7DaysForColaborador: PrFromGithub[] = [];
  public PrCountLast30DaysForColaborador: PrFromGithub[] = [];
  public PrCountLast90DaysForColaborador: PrFromGithub[] = [];
  public prCount: PrCount[] = [];
  public metasOneAOne: MetasOneAOne[] = [];
  public allLatestMetaByColaboradorId: AllLatestMetaByColaboradorId[] = []; 
  public competencias: CompetenciaByColaborador[] = [];
  public acoes: AcaoByColaborador[] = [];
  public empresas: Empresa[] = [];
  public empresasByColaborador: EmpresaByColaborador[] = [];
  public searchResultsAcoes: AcaoByColaborador[] = [];
  public searchResultsCompetencias:  CompetenciaByColaborador[] = [];
  public habilidadesByColaborador: HabilidadeByColaborador[] = [];
  public formattedDate: string | null = null;
  public selectedTimePr: string = 'Todos';

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private colaboradorService: ColaboradorService,
    private competenciaService: CompetenciaService,
    private acoesService: AcaoService,
    private esteiraService: EsteiraService,
    private habilidadeService: HabilidadeService,
    private timeService: TimeService,


  ) { }

  async ngOnInit(): Promise<void> {

    this.route.paramMap.subscribe((params) => {

      const esteiraId = params.get('esteiraId');
      if (esteiraId) {
        this.currentEsteira.id = parseInt(esteiraId);
        this.getTimesByEsteira(parseInt(esteiraId));
        this.getColaboradoresByEsteira(parseInt(esteiraId));
        this.getTimeAndColaboradorByEsteiraId(parseInt(esteiraId));

      }
      const colaboradorId = params.get('colaboradorId');
      if (colaboradorId) {
        this.getTimesAcoesHabilidades(parseInt(colaboradorId));
        
      }
    });
    if (this.searchResultsCompetencias.length === 0) {
      const cardElement = document.querySelector('.card-colaborador-content');
      if (cardElement) {
        cardElement.classList.add('card-colaborador-content-Scrrol');
      }
    }
  }



   getColaboradoresByEsteira(esteiraId: number) {
  this.timeService.getColaboradoresByEsteiraId(esteiraId).subscribe((response) => {
    // Aqui está o ajuste, usando [0] para pegar o primeiro colaborador da lista
    this.currentColaborador = response[0];
    this.colaboradores = response;
    this.getTimesAcoesHabilidades(this.currentColaborador.id);
  });
}

getColaboradorEsteiraId(esteiraId: number) {
  this.colaboradorService.getColaboradoresByEsteiraId(esteiraId).subscribe((response) => {
    this.colaboradoresByEsteira = response;
  });
}

  getTimesByEsteira(esteiraId: number) {
      this.timeService.getTimesByEsteiraId(esteiraId).subscribe((response) => {
        this.time = response;
      });
  }

  getTimeAndColaboradorByEsteiraId(esteiraId: number) {
    this.timeService.getTimeAndColaboradorByEsteiraId(esteiraId).subscribe((response) => {
      this.timesColaborador = response;

    });
  }
  
  getValorIndicePorIdColaborador(colaboradorId: number){
    this.timeService.getValorIndicePorIdColaborador(colaboradorId).subscribe((response) => {
      this.currentValorIndiceDeSobrevivencia = response;
    });
  }

  getColaboradoresByTime(timeId: number) {
    this.timeService.getColaboradoresByTimeId(timeId).subscribe(colaboradores => {
      this.colaboradores = colaboradores;

    });
  }

  getTimesByColaboradorId(colaboradorId: number){
    this.timeService.getTimesAndEsteiraByColaboradorId(colaboradorId).subscribe(response => {
      this.time = response.filter((time: { esteira: { id: any; }; }) => time.esteira.id === this.currentEsteira.id);

    });
  }

  public getCompetencias(id: number) {
    this.competenciaService
      .getCompetenciasByColaborador(id)
      .subscribe((response) => {
        this.competencias = response;
      });
  }

  public getAcoes(id: number): void {
    this.acoesService.getAcoesByColaborador(id).subscribe((response) => {
      this.acoes = response;
    });
  }

  public getHabilidades(id: number): void {
    this.habilidadeService.getHabilidadesByColaborador(id).subscribe((response) => {
      this.habilidadesByColaborador = response;
    });
  }

  public getMetas () {
    this.timeService.getMetas().subscribe((response) => {
      this.metasOneAOne = response;
    });
  }

  

  public async getTimesAcoesHabilidades(colaboradorId: number) {
    const colaborador = this.colaboradores.find(
      (colaborador) => colaborador.id === colaboradorId
    );
    if (colaborador) {
      this.currentColaborador = colaborador;
      this.getCompetencias(colaborador.id);
      this.getAcoes(colaborador.id);
      this.getHabilidades(colaborador.id);
      this.getTimesByColaboradorId(colaborador.id);
      this.getLatestMetaByColaboradorId(colaborador.id);
      this.getAllLatestMetaByColaboradorId(colaborador.id);
      this.getPrFromGithubByColaboradorId(colaborador.id);
      this.getPrCountLast7DaysForColaborador(colaborador.id);
      this.getPrCountLast30DaysForColaborador(colaborador.id);
      this.getValorIndicePorIdColaborador(colaborador.id);
    }
  }

  /*public async setCurrentMetasColaborador (id: number) {
    const metas = this.metasColaborador.find(
      (metasColaborador) => metasColaborador.id === id
    );
    if (metas) {
      this.currentMetasColaborador = metas;
      this.getAllLatestMetaByColaboradorId(metas.id);
    }
  }*/

  handleSearch(event: string) {
    if(event !== ''){
      this.searchResultsAcoes =  this.acoes.filter((acao)=>
      acao.acao.nome.toLowerCase().includes(event.toLowerCase()));

      this.searchResultsCompetencias =  this.competencias.filter((competencia)=>
      competencia.competencia.nome.toLowerCase().includes(event.toLowerCase()));
    }
    else{
      this.searchResultsAcoes= [];
      this.searchResultsCompetencias = [];
    }
}

updateColaboradoresByTime(timeId: number) {
  this.timeService.getTimeById(timeId).subscribe((time) => {
    this.currentTimes = time; // atualiza o time atual
    this.getColaboradoresByTime(timeId);
  });
}

public selecionarTime(timeId: number) {
  this.timeService.getTimeById(timeId).subscribe((time) => {
    this.currentTimes = time; // atualiza o time atual
    this.getColaboradoresByTime(timeId);
    this.getTimesByEsteira(time.esteira.id);
    this.getColaboradorEsteiraId(this.currentEsteira.id);
  });
}

public selecionarColaborador(colaboradorId: number) {
  this.colaboradorService.getColaboradorById(colaboradorId).subscribe((colaborador) => {
    this.currentColaborador = colaborador; // atualiza o colaborador atual
    this.getTimesAcoesHabilidades(colaborador.id);
    this.getTimesByColaboradorId(colaborador.id);
    this.getPrFromGithubByColaboradorId(colaborador.id);
    this.getColaboradorEsteiraId(this.currentEsteira.id);
    this.getTimesByEsteira(this.currentEsteira.id);
  });
}

getLatestMetaByColaboradorId(colaboradorId: number){
  this.timeService.getLatestMetaByColaboradorId(colaboradorId).subscribe((response) => {
    this.currentMetasColaborador = response;
  });
}

getAllTimesAndDevs() {
  this.getTimesByEsteira(this.currentEsteira.id);
  this.getColaboradoresByEsteira(this.currentEsteira.id);
  this.currentTimes.nomeTime = 'Todos';
}

getAllLatestMetaByColaboradorId(id: number): void {
  this.timeService
    .getAllLatestMetaByColaboradorId(id)
    .subscribe((metasColaboradorArray: AllLatestMetaByColaboradorId[]) => {
      metasColaboradorArray.forEach((metasColaboradorData: AllLatestMetaByColaboradorId) => {
        let dateParts = metasColaboradorData.data;
        let milliseconds = Number(dateParts[6]) / 1000000; // Convert nanoseconds to milliseconds
        let date = new Date(Number(dateParts[0]), Number(dateParts[1]) - 1, Number(dateParts[2]), Number(dateParts[3]), Number(dateParts[4]), Number(dateParts[5]), milliseconds);
        metasColaboradorData.data = date.toISOString();
      });
      this.allLatestMetaByColaboradorId = metasColaboradorArray;
      if(this.allLatestMetaByColaboradorId.length > 0) {
        this.selecionarMetaColaborador(this.allLatestMetaByColaboradorId[0].id);
      }
    });
}


public selecionarMetaColaborador (id?: number) {
  if (id) {
    const metas = this.allLatestMetaByColaboradorId.find((metas) => metas.id === id);
    if (metas) {
      let date = new Date(metas.data);
      let timestamp = date.getTime();
      this.currentMetasColaborador = {
        ...metas,
        data: [timestamp]
      };
      this.timeService.setCurrentMetasColaborador(this.currentMetasColaborador);
      this.formattedDate = this.currentMetasColaborador.data[0].toString();                                             
    }
  } else {
      this.getMetas();
      this.currentMetasColaborador = {
        id: 0,
        colaborador: {
          id: 0,
          nome: '',
          email: '',
          github: '',
          miniBio: '',
          habilidades: [],
        },
        meta: {
          id: 0,
          metas: '',
        },
        data: [],
      };
    }
  }

  /* metodo anterior x
  getPrCountByColaboradorId( colaboradorId: number) {
    this.timeService.getPrCountByColaboradorId(colaboradorId).subscribe((response) => {
      this.currentPrCount = response;
    });
  }*/

  //metodo para retornar o total de prs por colaborador
  getPrFromGithubByColaboradorId(colaboradorId: number) {
    this.timeService.getPrCountForColaboradorId(colaboradorId).subscribe((response) => {
      this.currentPrFromGithub = response;
    });
  }

  //metodo para retornar a quantidade de pr nos ultimos 7 dias por colaborador
  getPrCountLast7DaysForColaborador(colaboradorId: number) {
    this.timeService.getPrCountLast7DaysForColaborador(colaboradorId).subscribe((response) => {
       this.currentPrFromGithub.countPr = response.countpr || 0; // Atualiza diretamente a quantidade de PRs
    });
  }
   //metodo para retornar a quantidade de pr nos ultimos 30 dias por colaborador
  getPrCountLast30DaysForColaborador(colaboradorId: number) {
    this.timeService.getPrCountLast30DaysForColaborador(colaboradorId).subscribe((response) => {
      this.currentPrFromGithub.countPr = response.countpr || 0; // Atualiza diretamente a quantidade de PRs
   });
  }

    //metodo para retornar a quantidade de pr nos ultimos 90 dias por colaborador
  getPrCountLast90DaysForColaborador(colaboradorId: number) {
      this.timeService.getPrCountLast90DaysForColaborador(colaboradorId).subscribe((response) => {
        this.currentPrFromGithub.countPr = response.countpr || 0; // Atualiza diretamente a quantidade de PRs
     });
  }

   //garante que o select Todos retorne o total de prs
  updatePrCountToTotal() {
    this.selectedTimePr = 'Todos';
    const colaboradorId = this.currentColaborador.id;
    this.getPrFromGithubByColaboradorId(colaboradorId);
  }

   //garante que o select 1 sem retorne a quantidade de prs dos ultimos 7 dias
  updatePrCountToLast7Days() {
    this.selectedTimePr = '7 d';
    const colaboradorId = this.currentColaborador.id;
    this.getPrCountLast7DaysForColaborador(colaboradorId);
  }

   //garante que o select 30 dias retorne a quantidade de prs dos ultimos 30 dias
  updatePrCountToLast30Days() {
    this.selectedTimePr = '30 d';
    const colaboradorId = this.currentColaborador.id;
    this.getPrCountLast30DaysForColaborador(colaboradorId);
  }

  //garante que o select 30 dias retorne a quantidade de prs dos ultimos 30 dias
  updatePrCountToLast90Days() {
    this.selectedTimePr = '90 d';
    const colaboradorId = this.currentColaborador.id;
    this.getPrCountLast90DaysForColaborador(colaboradorId);
  }


}


