import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {  ColaboradorByTimeId,
          Time,
          TimeColaborador,
          EsteiraDeDesenvolvimento,
          TiposEnum,
          TimeByEsteiraId,
          Colaborador,
          ColaboradorByEsteiraId,
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
    private timeService: TimeService

  ) { }

  async ngOnInit(): Promise<void> {
    this.route.paramMap.subscribe((params) => {
      const esteiraId = params.get('esteiraId');
      if (esteiraId) {
        this.getTimesByEsteira(parseInt(esteiraId));
        this.getColaboradoresByEsteira(parseInt(esteiraId));
      }
    });
  }

  getColaboradoresByEsteira(esteiraId: number) {
    this.colaboradorService.getColaboradoresByEsteiraId(esteiraId).subscribe((response) => {
      this.colaboradores = response;
      console.log(this.colaboradores);
    });
  }

  getTimesByEsteira(esteiraId: number) {
      this.timeService.getTimesByEsteiraId(esteiraId).subscribe((response) => {
        this.times = response;
        console.log(this.times);
      });
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
}
