import { Component, OnInit, ViewChild, AfterContentInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {
  AcaoByColaborador,
  Colaborador,
  CompetenciaByColaborador,
  Empresa,
  EmpresaByColaborador,
} from 'src/app/types/colaborador-types';
import { AcaoService } from 'src/services/acao/acao.service';
import { ColaboradorService } from 'src/services/colaborador/colaborador.service';
import { CompetenciaService } from 'src/services/competencia/competencia.service';
import { EmpresaService } from 'src/services/empresa/empresa.service';

@Component({
  selector: 'app-colaborador',
  templateUrl: './colaborador.component.html',
  styleUrls: ['./colaborador.component.sass'],

})
export class ColaboradorComponent implements OnInit {

  public colaboradores: Colaborador[] = [];
  public currentColaborador: Colaborador = {
    id: 0,
    nome: '',
    email: '',
    github: '',
    habilidades: [],
  };
  public currentEmpresa: Empresa = {
    id: 0,
    nome: '',
  };
  public competencias: CompetenciaByColaborador[] = [];
  public acoes: AcaoByColaborador[] = [];
  public empresas: Empresa[] = [];
  public empresasByColaborador: EmpresaByColaborador[] = [];
  public searchResultsAcoes: AcaoByColaborador[] = [];
  public searchResultsCompetencias:  CompetenciaByColaborador[] = [];

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private colaboradorService: ColaboradorService,
    private competenciaService: CompetenciaService,
    private acoesService: AcaoService,
    private empresaService: EmpresaService
  ) {}

  async ngOnInit(): Promise<void> {
    this.getEmpresas();
    this.getColaboradores();
    this.route.paramMap.subscribe((params) => {
      const id = params.get('colaboradorId');
      if (id) {
        this.setCurrent(parseInt(id));
        this.getEmpresasByColaborador(parseInt(id));
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
    }
  }

  public async getColaboradores() {
    this.colaboradorService.getAllColaboradores().subscribe((response) => {
      this.colaboradores = response;
      const id = this.route.snapshot.paramMap.get('colaboradorId');
      if (id) {
        this.setCurrent(parseInt(id));
      } else {
        if (this.colaboradores.length > 0) {
          this.setCurrent(this.colaboradores[0].id);
          this.router.navigate([`/${this.colaboradores[0].id}`]);
        }
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

  public getEmpresas(): void {
    this.empresaService.getEmpresas().subscribe((response) => {
      this.empresas = response;
    });
  }

  public selecionarEmpresa(id?: number) {
    if (id) {
      const empresa = this.empresas.find((empresa) => empresa.id === id);

      if (empresa) {
        this.currentEmpresa = empresa;
      }

      this.empresaService
        .getColaboradoresByEmpresa(id)
        .subscribe((response) => {
          const colaboradores: Colaborador[] = [];
          response.map((item: EmpresaByColaborador) => {
            colaboradores.push(item.colaborador);
          });
          this.colaboradores = colaboradores;
          if (this.colaboradores.length > 0)
            this.router.navigate([`/${this.colaboradores[0].id}`]);
        });
    } else {
      this.getColaboradores();
      this.currentEmpresa = {
        id: 0,
        nome: '',
      };
    }
  }

  public getEmpresasByColaborador(id: number) {
    this.empresaService.getEmpresasByColaborador(id).subscribe((response) => {
      this.empresasByColaborador = response;
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

    console.log(this.searchResultsAcoes);


  }


}
