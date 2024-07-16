import { Component, OnInit, Renderer2, ElementRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {
  Time,
  TimeColaborador,
  EsteiraDeDesenvolvimento,
  TiposEnum,
  Colaborador,
  MetasColaborador,
  IndiceDeSobrevivenciaDev,
  VcsPullRequest,
  TasksCountJira,
  DataArray,
  CompetenciasPorData,
  AllLatestMetaByColaboradorId
} from 'src/app/types/time-types';
import { TimeService } from 'src/services/time/time.service';
import {
  Habilidade,
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
import { NgbModal, NgbDateStruct, NgbDate } from '@ng-bootstrap/ng-bootstrap';

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
    nota: [],
    colaborador: {
      id: 0,
      nome: '',
      email: '',
      github: '',
      miniBio: '',
      habilidades: [],
    },
    competencia: [],
    data: []
  };



  /*
  //variavel com dados para armazenar a quantidade total de prs por colaborador github API
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
  */

  //variavel com dados para armazenar a quantidade total de prs por colaborador Hasura
  currentVcsPullRequest: VcsPullRequest = {
    id: 0,
    title: '',
    mergedAt: '',
    author: '',
    colaborador: {
      id: 0,
      nome: '',
      email: '',
      github: '',
      miniBio: '',
      habilidades: [],
    },
    countpr: 0
  };

  public currentTasksCountJira: TasksCountJira = {
    id: 0,
    colaborador: {
      id: 0,
      nome: '',
      email: '',
      github: '',
      miniBio: '',
      habilidades: [],
    },
    taskName: '',
    statusDetail: '',
    mergedAt: '',
    author: '',
    counttasks: 0
  };

  public currentValorIndiceDeSobrevivencia: IndiceDeSobrevivenciaDev = {
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

  public PrCountLast7DaysForColaborador: VcsPullRequest[] = [];
  public PrCountLast30DaysForColaborador: VcsPullRequest[] = [];
  public PrCountLast90DaysForColaborador: VcsPullRequest[] = [];
  public allLatestMetaByColaboradorId: AllLatestMetaByColaboradorId[] = [];
  public competencias: CompetenciaByColaborador[] = [];
  public acoes: AcaoByColaborador[] = [];
  public empresas: Empresa[] = [];
  public empresasByColaborador: EmpresaByColaborador[] = [];
  public searchResultsAcoes: AcaoByColaborador[] = [];
  public searchResultsCompetencias: CompetenciaByColaborador[] = [];
  public habilidadesByColaborador: HabilidadeByColaborador[] = [];
  public duasHabilidadesByColaborador: HabilidadeByColaborador[] = [];
  public formattedDate: string | null = null;
  public selectedTimePr: string = 'Todos';
  public selectedActivities: string = 'Todos';
  public selectedDates = [];
  public dataInicio: string = '';
  public dataFim: string = '';
  public dataInicioActivity: string = '';
  public dataFimActivity: string = '';

  public esteiraSelecionada: any = [];
  public isEsteiraSelected: boolean = false;
  public esteiraSelecionadaId: number = 0;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private colaboradorService: ColaboradorService,
    private competenciaService: CompetenciaService,
    private acoesService: AcaoService,
    private esteiraService: EsteiraService,
    private habilidadeService: HabilidadeService,
    private timeService: TimeService,
    private modalService: NgbModal,
  ) {

    this.dataFim = new Date().toISOString().substring(0, 10);
    this.dataFimActivity = new Date().toISOString().substring(0, 10);

  }

  async ngOnInit(): Promise<void> {

    this.esteiraService.esteiraSelecionada$.subscribe((esteira) => {
      if (esteira) {
        this.esteiraSelecionada = esteira;
        this.isEsteiraSelected = true;
        this.esteiraSelecionadaId = this.esteiraSelecionada.id;
        this.atualizarDados();
      }
    });

    const savedEsteira = localStorage.getItem('selectedEsteira');
    if (savedEsteira) {
      this.esteiraSelecionada = JSON.parse(savedEsteira);
      this.isEsteiraSelected = true;
      this.esteiraSelecionadaId = this.esteiraSelecionada.id;
      this.currentEsteira = this.esteiraSelecionada;
      this.router.navigate([`time/${this.esteiraSelecionada.id}`]);
      this.atualizarDados();
    }

    this.route.paramMap.subscribe((params) => {
      const esteiraId = params.get('esteiraId');
      if (esteiraId && !isNaN(Number(esteiraId))) {
        console.log("esteiraId", esteiraId)
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

  getValorIndicePorIdColaborador(colaboradorId: number) {
    this.timeService.getValorIndicePorIdColaborador(colaboradorId).subscribe((response) => {
      this.currentValorIndiceDeSobrevivencia = response;
    });
  }

  getColaboradoresByTime(timeId: number) {
    this.timeService.getColaboradoresByTimeId(timeId).subscribe(colaboradores => {
      this.colaboradores = colaboradores;

    });
  }

  getTimesByColaboradorId(colaboradorId: number) {
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

  public getDuasHabilidades(id: number): void {
    this.habilidadeService.getPrimeirasHabilidadesByColaborador(id).subscribe((response) => {
      this.duasHabilidadesByColaborador = response;
    })
  }

  public getMetas() {
    this.timeService.getMetas().subscribe((response) => {
      this.metasColaborador = response;
    });
  }

  public async getTimesAcoesHabilidades(colaboradorId: number) {
    const colaborador = this.colaboradores.find(
      (colaborador) => colaborador.id === colaboradorId
    );
    if (colaborador) {
      this.currentColaborador = colaborador;
      this.currentMetasColaborador = {
        id: 0, // ou outro valor padrão para número
        nota: [], // array vazio para número
        colaborador: {} as Colaborador, // objeto vazio do tipo Colaborador
        competencia: [], // array vazio para CompetenciaNota
        data: [] // array vazio para string
      };
      this.getCompetencias(colaborador.id);
      this.getAcoes(colaborador.id);
      this.getHabilidades(colaborador.id);
      this.getTimesByColaboradorId(colaborador.id);
      this.getAllLatestMetaByColaboradorId(colaborador.id);
      this.getPrFromGithubByColaboradorId(colaborador.id);
      this.getTasksCountByColaboradorId(colaborador.id);
      this.getValorIndicePorIdColaborador(colaborador.id);
      this.getDuasHabilidades(colaborador.id);
    }
  }

  handleSearch(event: string) {
    if (event !== '') {
      this.searchResultsAcoes = this.acoes.filter((acao) =>
        acao.acao.nome.toLowerCase().includes(event.toLowerCase()));

      this.searchResultsCompetencias = this.competencias.filter((competencia) =>
        competencia.competencia.nome.toLowerCase().includes(event.toLowerCase()));
    }
    else {
      this.searchResultsAcoes = [];
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
      this.getTasksCountByColaboradorId(colaborador.id);
      this.getColaboradorEsteiraId(this.currentEsteira.id);
      this.getTimesByEsteira(this.currentEsteira.id);
      this.selectedTimePr = 'Todos';
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
      .subscribe((datas: DataArray[]) => {
        this.allLatestMetaByColaboradorId = datas.map(data => {
          return {
            id: id,
            data: new Date(data.join('-')).toISOString()
          };
        });
        if (this.allLatestMetaByColaboradorId.length > 0) {
          this.selecionarMetaColaborador(this.allLatestMetaByColaboradorId[0].id);
        }
      });
  }

  public selecionarMetaColaborador(id?: number, selectedDate?: string) {
    if (id) {
      const meta = this.allLatestMetaByColaboradorId.find((meta) => meta.id === id);
      if (meta) {
        let date = selectedDate ? new Date(selectedDate) : new Date(meta.data);
        let timestamp = date.getTime();
        this.currentMetasColaborador = {
          ...this.currentMetasColaborador,
          id: id,
          data: [timestamp.toString()]
        };
        this.timeService.setCurrentMetasColaborador(this.currentMetasColaborador);
        this.formattedDate = this.currentMetasColaborador.data[0].toString();
        this.timeService.getTop3CompetenciasByColaboradorAndData(id, date.toISOString())
          .subscribe((competenciasPorData: CompetenciasPorData) => {
            const dataKey = date.toISOString().split('T')[0];
            this.currentMetasColaborador.competencia = competenciasPorData[dataKey];
            this.currentMetasColaborador.nota = this.currentMetasColaborador.competencia.map(c => c.nota);
          });
      }
    } else {
      this.getMetas();
      this.currentMetasColaborador = {
        id: 0,
        nota: [],
        colaborador: {
          id: 0,
          nome: '',
          email: '',
          github: '',
          miniBio: '',
          habilidades: [],
        },
        data: [],
        competencia: []
      };
    }
  }

  //Metodos para retornar a quantidade de prs em tempo determinado Hasura API

  //metodo para retornar o total de prs por colaborador
  getPrFromGithubByColaboradorId(colaboradorId: number) {
    this.timeService.getPrCountForColaboradorId(colaboradorId).subscribe((response) => {
      this.currentVcsPullRequest.countpr = response.countpr || 0;
    });
  }

  //metodo para retornar a quantidade de pr nos ultimos 7 dias por colaborador
  getPrCountLast7DaysForColaborador(colaboradorId: number) {
    this.timeService.getPrCountLast7DaysForColaborador(colaboradorId).subscribe((response) => {
      this.currentVcsPullRequest.countpr = response.countpr || 0; // Atualiza diretamente a quantidade de PRs
    });
  }
  //metodo para retornar a quantidade de pr nos ultimos 30 dias por colaborador
  getPrCountLast30DaysForColaborador(colaboradorId: number) {
    this.timeService.getPrCountLast30DaysForColaborador(colaboradorId).subscribe((response) => {
      this.currentVcsPullRequest.countpr = response.countpr || 0; // Atualiza diretamente a quantidade de PRs
    });
  }

  //metodo para retornar a quantidade de pr nos ultimos 90 dias por colaborador
  getPrCountLast90DaysForColaborador(colaboradorId: number) {
    this.timeService.getPrCountLast90DaysForColaborador(colaboradorId).subscribe((response) => {
      this.currentVcsPullRequest.countpr = response.countpr || 0; // Atualiza diretamente a quantidade de PRs
    });
  }

  //metodo para retornar a quantidade de prs no ano corente por colaboradorId
  getPrCountThisYearForColaborador(colaboradorId: number) {
    this.timeService.getPrCountThisYearForColaborador(colaboradorId).subscribe((response) => {
      this.currentVcsPullRequest.countpr = response.countpr || 0; // Atualiza diretamente a quantidade de PRs
    });
  }

  getPrCountLastYearForColaborador(colaboradorId: number) {
    this.timeService.getPrCountLastYearForColaborador(colaboradorId).subscribe((response) => {
      this.currentVcsPullRequest.countpr = response.countpr || 0; // Atualiza diretamente a quantidade de PRs
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

  //garante que o select 90 dias retorne a quantidade de prs dos ultimos 90 dias
  updatePrCountToLast90Days() {
    this.selectedTimePr = '90 d';
    const colaboradorId = this.currentColaborador.id;
    this.getPrCountLast90DaysForColaborador(colaboradorId);
  }

  //garante que o select de Ano Anterior retorne a quantidade de prs do Dev referente ao Ano anterior 
  updatePrCountToLastYear() {
    this.selectedTimePr = 'Ano anterior';
    const colaboradorId = this.currentColaborador.id;
    this.getPrCountLastYearForColaborador(colaboradorId);
  }

  //garante que o select de Este Ano retorne a quantidade de prs do Dev referente ao Ano corrente 
  updatePrCountToThisYear() {
    this.selectedTimePr = 'Este ano';
    const colaboradorId = this.currentColaborador.id;
    this.getPrCountThisYearForColaborador(colaboradorId);
  }

  //Modal para Data Personalizada
  open(content: any) {
    this.modalService.open(content)
  }

  //Formata as datas
  formatDate(date: string): string {
    const [year, month, day] = date.split('-').map(Number);
    const d = new Date(year, month - 1, day);

    let formattedMonth = '' + (d.getMonth() + 1);
    let formattedDay = '' + d.getDate();
    const formattedYear = d.getFullYear();

    if (formattedMonth.length < 2)
      formattedMonth = '0' + formattedMonth;
    if (formattedDay.length < 2)
      formattedDay = '0' + formattedDay;

    return [formattedDay, formattedMonth, formattedYear].join('/');
  }

  //Atualização dos valores das variáveis dataInicio dataFim e Fechar Modal
  updateDates() {
    this.selectedTimePr = 'Data personalizada';
    this.dataInicio = new Date(this.dataInicio).toISOString().substring(0, 10);
    this.dataFim = new Date(this.dataFim).toISOString().substring(0, 10);
    this.modalService.dismissAll();

    const colaboradorId = this.currentColaborador.id;
    this.timeService.getPrCountDateForColaborador(colaboradorId, this.dataInicio, this.dataFim).subscribe(data => {
      this.currentVcsPullRequest.countpr = data.countpr || 0;
    });

    this.selectedTimePr = this.formatDate(this.dataInicio) + ' - ' + this.formatDate(this.dataFim);
  }

  //-------metodos para quantidade de tasks concluidas por colaboradorid-------

  //metodo busca quantidade total de tasks concluidas por colaboradorId
  getTasksCountByColaboradorId(colaboradorId: number) {
    this.timeService.getCountAllCompletedTasksByColaboradorId(colaboradorId).subscribe((response) => {
      this.currentTasksCountJira.counttasks = response.counttasks || 0;
    });
  }

  //metodo busca quantidade de tasks concluidas por colaboradorId nos ultimos 7 dias
  getTasksCountLast7DaysByColaboradorId(colaboradorId: number) {
    this.timeService.getCountCompletedTasksLast7DaysByColaboradorId(this.currentColaborador.id).subscribe((response) => {
      this.currentTasksCountJira.counttasks = response.counttasks || 0;
    });
  }

  //metodo busca quantidade de tasks concluidas por colaboradorId nos ultimos 30 dias
  getTasksCountLast30DaysByColaboradorId(colaboradorId: number) {
    this.timeService.getCountCompletedTasksLast30DaysByColaboradorId(this.currentColaborador.id).subscribe((response) => {
      this.currentTasksCountJira.counttasks = response.counttasks || 0;
    });
  }

  //metodo busca quantidade de tasks concluidas por colaboradorId nos ultimos 90 dias
  getCountCompletedTasksLast90DaysByColaboradorId(colaboradorId: number) {
    this.timeService.getCountCompletedTasksLast90DaysByColaboradorId(this.currentColaborador.id).subscribe((response) => {
      this.currentTasksCountJira.counttasks = response.counttasks || 0;
    });
  }

  //metdo busca quantidade de PRs por colaboradorId no ano corrente
  getTasksCountThisYearByColaboradorId(colaboradorId: number) {
    this.timeService.getCountCompletedTasksThisYearByColaboradorId(this.currentColaborador.id).subscribe((response) => {
      this.currentTasksCountJira.counttasks = response.counttasks || 0;
    });
  }
  //metdo busca quantidade de PRs por colaboradorId no ano anterior
  getTasksCountLastYearByColaboradorId(colaborador: number) {
    this.timeService.getCountCompletedTasksLastYearByColaboradorId(this.currentColaborador.id).subscribe((response) => {
      this.currentTasksCountJira.counttasks = response.counttasks || 0;
    });
  }

  updateTasksCount(timePeriod: string) {
    this.selectedActivities = timePeriod;
    const colaboradorId = this.currentColaborador.id;

    switch (timePeriod) {
      case 'Todos':
        this.getTasksCountByColaboradorId(colaboradorId);
        break;
      case '7 d':
        this.getTasksCountLast7DaysByColaboradorId(colaboradorId);
        break;
      case '30 d':
        this.getTasksCountLast30DaysByColaboradorId(colaboradorId);
        break;
      case '90 d':
        this.getCountCompletedTasksLast90DaysByColaboradorId(colaboradorId);
        break;
      case 'Ano anterior':
        this.getTasksCountLastYearByColaboradorId(colaboradorId);
        break;
      case 'Este ano':
        this.getTasksCountThisYearByColaboradorId(colaboradorId);
        break;
      default:
        break;
    }
  }

  //Modal para Data Personalizada
  openActivity(contentActivity: any) {
    this.modalService.open(contentActivity)
  }

  //Atualização dos valores das variáveis dataInicio dataFim e Fechar Modal
  updateDatesActivity() {
    this.selectedActivities = 'Data personalizada';
    this.dataInicioActivity = new Date(this.dataInicioActivity).toISOString().substring(0, 10);
    this.dataFimActivity = new Date(this.dataFimActivity).toISOString().substring(0, 10);
    this.modalService.dismissAll();

    const colaboradorId = this.currentColaborador.id;
    this.timeService.getTasksCountForColaboradorId(colaboradorId, this.dataInicioActivity, this.dataFimActivity).subscribe(data => {
      this.currentTasksCountJira.counttasks = data.counttasks || 0;
    });
    
    this.selectedActivities = this.formatDate(this.dataInicioActivity) + ' - ' + this.formatDate(this.dataFimActivity);
  }

  private atualizarDados() {
    // Recarregar os dados conforme a esteira selecionada
    this.getTimesByEsteira(this.esteiraSelecionadaId);
    this.getColaboradoresByEsteira(this.esteiraSelecionadaId);
    this.getTimeAndColaboradorByEsteiraId(this.esteiraSelecionadaId);
  }
}


