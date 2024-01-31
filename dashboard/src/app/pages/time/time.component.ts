import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {  ColaboradorByTimeId,
          Time,
          TimeColaborador,
          EsteiraDeDesenvolvimento,
          TiposEnum,
          TimeByEsteiraId,
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
  public times: Time[] = [];
  public competencias: CompetenciaByColaborador[] = [];
  public acoes: AcaoByColaborador[] = [];
  public empresas: Empresa[] = [];
  public empresasByColaborador: EmpresaByColaborador[] = [];
  public searchResultsAcoes: AcaoByColaborador[] = [];
  public searchResultsCompetencias:  CompetenciaByColaborador[] = [];
  public habilidadesByColaborador: HabilidadeByColaborador[] = [];
  public timeByEsteiraId: TimeByEsteiraId[] = [];
  public colaboradorByTimeId: ColaboradorByTimeId[] = [];


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

    this.getColaboradores();
    this.route.paramMap.subscribe((params) => {
      const id = params.get('colaboradorId');
      if (id) {
        this.setCurrent(parseInt(id));
      }
      const esteiraId = params.get('esteiraId');
      if (esteiraId) {
        this.getTimes(parseInt(esteiraId));

      }
      const colaboradorId = params.get('colaboradorId');
      if (esteiraId && colaboradorId) {
        this.getTimesByEsteiraIdAndColaboradorId(parseInt(esteiraId), parseInt(colaboradorId));
        const timeColaboradors = this.timesColaborador.find((timeColaborador) =>
          timeColaborador.colaborador.id === parseInt(colaboradorId) && timeColaborador.time.esteira.id === parseInt(esteiraId)
        );
        if (timeColaboradors) {
          this.currentTimeColaborador = timeColaboradors;
        }
      }
    });
  }

  public async setCurrent(id: number) {

    const colaborador = this.colaboradores.find(
      (colaborador) => colaborador.id === id
    );
    if (colaborador) {
      this.currentColaborador = colaborador;
      this.getCompetencias(colaborador.id);
      this.getAcoes(colaborador.id);
      this.getHabilidades(colaborador.id);

    }

  }

  public async getColaboradores() {
    const esteiraId = this.route.snapshot.paramMap.get('esteiraId');
    if (esteiraId) {
      this.colaboradorService.getColaboradoresByEsteiraId(parseInt(esteiraId)).subscribe((response) => {
        this.colaboradores = response;
        const colaboradorId = this.route.snapshot.paramMap.get('colaboradorId');
        if (colaboradorId) {
          this.setCurrent(parseInt(colaboradorId));
        } else {
          if (this.colaboradores.length > 0 ) {
            this.setCurrent(this.colaboradores[0].id);
            this.router.navigate([`time/${esteiraId}`]);
          }
        }
      });
    }
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

  getTimesByEsteiraIdAndColaboradorId(esteiraId: number, colaboradorId: number): void {
    this.timeService.getTimesByEsteiraIdAndColaboradorId(esteiraId, colaboradorId).subscribe((response) => {
      this.timeByEsteiraId = response;
    });
  }

  getColaboradoresByTimeId(timeId: number): void {
    this.timeService.getColaboradoresByTimeId(timeId).subscribe((response) => {
      this.colaboradorByTimeId = response;
    });

  }

  getTimes(esteiraId: number) {
      this.timeService.getTimesByEsteiraId(esteiraId).subscribe((response) => {
        this.times = response;
        console.log(this.times);
      });
  }

  public selecionarTime(id?: number) {
    if (id) {
      const times = this.times.find((times) => times.id === id);
      if (times) {
        this.currentTimes = times;
      }
      this.timeService
        .getColaboradoresByTimeId(id)
        .subscribe((response) => {
          console.log(response);
          const colaboradoresTime: Colaborador[] = [];
          response.map((item: Colaborador) => {
            colaboradoresTime.push(item);
          });
          this.colaboradores = colaboradoresTime;
          if (this.colaboradores.length > 0) {
            // Navega para a rota do primeiro colaborador
            this.router.navigate([`/time/${this.currentEsteira.id}`]);
          }
        });
    } else {
      this.getTimes(this.currentEsteira.id);
      this.currentTimes = {
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
      };
    }
  }

  // No seu componente TypeScript

  onTimesButtonClick(): void {
    const esteiraId = this.currentEsteira.id;
    this.getTimes(esteiraId);
  }

  onDevButtonClick(): void {
    const timeId = this.currentTimes.id;
    this.getColaboradoresByTimeId(timeId);
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
