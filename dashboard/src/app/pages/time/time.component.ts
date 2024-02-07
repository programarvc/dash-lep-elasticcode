import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {
          Time,
          TimeColaborador,
          EsteiraDeDesenvolvimento,
          TiposEnum,
          Colaborador,

         } from 'src/app/types/time-types';
import { TimeService } from 'src/services/time/time.service';
import {  Habilidade,
          HabilidadeByColaborador,
          CompetenciaByColaborador,
          AcaoByColaborador,
          EmpresaByColaborador,
          Empresa,
          Competencia,
          Acao,
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
  public competencias: CompetenciaByColaborador[] = [];
  public acoes: AcaoByColaborador[] = [];
  public empresas: Empresa[] = [];
  public empresasByColaborador: EmpresaByColaborador[] = [];
  public searchResultsAcoes: AcaoByColaborador[] = [];
  public searchResultsCompetencias:  CompetenciaByColaborador[] = [];
  public habilidadesByColaborador: HabilidadeByColaborador[] = [];

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
  }

   getColaboradoresByEsteira(esteiraId: number) {
  this.timeService.getColaboradoresByEsteiraId(esteiraId).subscribe((response) => {
    // Aqui está o ajuste, usando [0] para pegar o primeiro colaborador da lista
    this.currentColaborador = response[0];
    this.colaboradores = response;
    this.getTimesAcoesHabilidades(this.currentColaborador.id);
    console.log(this.currentColaborador);
  });
}

getColaboradorEsteiraId(esteiraId: number) {
  this.colaboradorService.getColaboradoresByEsteiraId(esteiraId).subscribe((response) => {
    this.colaboradoresByEsteira = response;
    console.log(this.colaboradoresByEsteira);
  });
}

  getTimesByEsteira(esteiraId: number) {
      this.timeService.getTimesByEsteiraId(esteiraId).subscribe((response) => {
        this.time = response;
        console.log(this.time);
      });
  }

  getTimeAndColaboradorByEsteiraId(esteiraId: number) {
    this.timeService.getTimeAndColaboradorByEsteiraId(esteiraId).subscribe((response) => {
      this.timesColaborador = response;
      console.log(this.timesColaborador);

    });
  }

  getColaboradoresByTime(timeId: number) {
    this.timeService.getColaboradoresByTimeId(timeId).subscribe(colaboradores => {
      this.colaboradores = colaboradores;
      console.log(this.colaboradores);

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
    }

  }


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

updateTimesByColaborador(colaboradorId: number) {

  this.getTimesByColaboradorId(colaboradorId);
  this.getTimesByEsteira(this.currentEsteira.id);
  this.getColaboradorEsteiraId(this.currentEsteira.id);
}

updateCurrentColaborador(colaborador: Colaborador) {
  this.currentColaborador = colaborador;
  this.getTimesAcoesHabilidades(colaborador.id);
  this.getTimesByColaboradorId(colaborador.id);

  // Recupera todos os times da esteira
  this.getColaboradorEsteiraId(this.currentEsteira.id);
  this.getTimesByEsteira(this.currentEsteira.id);
}

getAllTimesAndDevs() {
  this.getTimesByEsteira(this.currentEsteira.id);
  this.getColaboradoresByEsteira(this.currentEsteira.id);
  this.currentTimes.nomeTime = 'Todos';

  // Atualiza o colaborador atual para o primeiro da lista, se existir
  if (this.colaboradores.length > 0) {
    this.currentColaborador = this.colaboradores[0];
  }
}

selectTime(time: Time) {
  this.getTimesByEsteira(time.esteira.id);
  this.updateColaboradoresByTime(time.id);
  this.getColaboradorEsteiraId(this.currentEsteira.id);
  this.currentColaborador = this.colaboradores[0];
}

}

