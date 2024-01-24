import { TimeByEsteiraId } from './../../types/time-types';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Time,
          TimeEsteira,
          EsteiraDeDesenvolvimento,
          TiposEnum,
         } from 'src/app/types/time-types';
import { TimeService } from 'src/services/time/time.service';
import { Colaborador,
          Habilidade,
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
import { EmpresaService } from 'src/services/empresa/empresa.service';
import { HabilidadeService } from 'src/services/habilidade/habilidade.service';

@Component({
  selector: 'app-time',
  templateUrl: './time.component.html',
  styleUrls: ['./time.component.sass']
})
export class TimeComponent implements OnInit {

  public times: TimeEsteira[] = [];
  public currentTime: TimeEsteira = {
    id: 0,
    time: {
      id: 0,
      nome: '',
    },
    colaborador: {
      id: 0,
      nome: '',
      email: '',
      github: '',
      habilidades: [],
    },
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
  public competencias: CompetenciaByColaborador[] = [];
  public acoes: AcaoByColaborador[] = [];
  public empresas: Empresa[] = [];
  public empresasByColaborador: EmpresaByColaborador[] = [];
  public searchResultsAcoes: AcaoByColaborador[] = [];
  public searchResultsCompetencias:  CompetenciaByColaborador[] = [];
  public habilidadesByColaborador: HabilidadeByColaborador[] = [];
  public timeByEsteiraId: TimeByEsteiraId[] = [];



  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private colaboradorService: ColaboradorService,
    private competenciaService: CompetenciaService,
    private acoesService: AcaoService,
    private empresaService: EmpresaService,
    private habilidadeService: HabilidadeService,
    private timeService: TimeService

  ) { }

  async ngOnInit(): Promise<void> {
    this.getTimes();
    const id = this.route.snapshot.paramMap.get('colaboradorId');
    if (id) {
      this.setCurrent(parseInt(id));
    }
    this.route.paramMap.subscribe((params) => {
      const id = params.get('colaboradorId');
      if (id) {
        this.setCurrent(parseInt(id));
        this.getTimeEsteiraById(parseInt(id));
      }
    });
  }

  public async setCurrent(id: number) {
    this.getTimeEsteiraById(id);
    const colaboradortime = this.colaboradores.find(
      (colaboradortime) => colaboradortime.id === id
    );
    if (colaboradortime) {
      this.currentColaborador = colaboradortime;
      this.getCompetencias(colaboradortime.id);
      this.getAcoes(colaboradortime.id);
      this.getHabilidades(colaboradortime.id);
    }
    const colaborador = this.colaboradores.find(
      (colaborador) => colaborador.id === id
    );
    if (colaborador){
      this.currentColaborador = colaborador;
      this.getAcoes(colaborador.id);
      this.getCompetencias(colaborador.id);
      this.getHabilidades(colaborador.id);
    }

  }

  public async getTimes() {
    this.timeService.getTimes().subscribe((response) => {
      this.times = response;
      if (this.times.length > 0) {
        this.setCurrent(this.times[0].colaborador.id);
        this.router.navigate([`time/${this.times[0].colaborador.id}`]);
      }
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

  public getEmpresas(): void {
    this.empresaService.getEmpresas().subscribe((response) => {
      this.empresas = response;
    });
  }

  public getEmpresasByColaborador(id: number) {
    this.empresaService.getEmpresasByColaborador(id).subscribe((response) => {
      this.empresasByColaborador = response;
    });
  }

  getTimeEsteiraById(id: number) {
    this.timeService.getTimeEsteiraById(id).subscribe((response) => {
      this.timeByEsteiraId = response;
      console.log(this.timeByEsteiraId);
    });
  }

  getColaboradores(){
    this.colaboradorService.getAllColaboradores().subscribe((response) => {
      this.colaboradores = response;
    });
  }

  public selecionarTime(id?: number) {
    if (id) {
      const time = this.times.find((time) => time.time.id === id);

      if (time) {
        this.currentTime = time;
      }

      this.timeService
        .getTimeEsteiraById(id)
        .subscribe((response) => {
          const colaboradoresTime: TimeByEsteiraId[] = [];
          response.map((item: TimeEsteira) => {
            colaboradoresTime.push(item.colaborador.);
          });
          this.currentColaborador = colaboradoresTime;
          if (this.colaboradores.length > 0)
            this.router.navigate([`time/${this.colaboradores[0].id}`]);
        });
    } else {
      this.getTimes();
      this.currentTime = {
        id: 0,
        time: {
          id: 0,
          nome: '',
        },
        colaborador: {
          id: 0,
          nome: '',
          email: '',
          github: '',
          habilidades: [],
        },
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
