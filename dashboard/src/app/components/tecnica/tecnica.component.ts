import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {
  Empresa,
  TiposEnum,
  Maturidade,
  EsteiraDeDesenvolvimento,
  ItemDeMaturidade,
  TiposMaturidadeEnum,
  ValorDosIndicesDeMaturidadeByEsteiraIdAndTecnica,
  ValorDosIndicesDeMaturidade,
} from 'src/app/types/valorMaturidade-types';
import { EsteiraService } from 'src/services/esteira/esteira.service';
import { EmpresaService } from 'src/services/empresa/empresa.service';
import { MaturidadeService } from 'src/services/maturidade/maturidade.service';
import { valorMaturidadeService } from 'src/services/valor-maturidade/valor-maturidade.service';
@Component({
  selector: 'app-tecnica',
  templateUrl: './tecnica.component.html',
  styleUrls: ['./tecnica.component.sass']
})


export class TecnicaComponent implements OnInit {

  baseTabela:number[] = [0,10,20,30,40,50,60,70,80,90,100];

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
  public valorMaturidadeTecnica: ValorDosIndicesDeMaturidadeByEsteiraIdAndTecnica[] = [];
  public valorMaturidadeProcesso:  ValorDosIndicesDeMaturidadeByEsteiraIdAndTecnica[] =[];
  public valorMaturidadeMetrica:  ValorDosIndicesDeMaturidadeByEsteiraIdAndTecnica[] =[];

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private esteiraService: EsteiraService,
    private empresaService: EmpresaService,
    private valorMaturidadeService: valorMaturidadeService


  ) { }

  ngOnInit(): void {
    this.getValorMaturidades();
    this.route.paramMap.subscribe((params) => {
      const id = params.get('esteiraId');
      if (id) {
        this.setCurrent(parseInt(id));
        this.getValorMaturidadesByEsteiraIdAndTecnica(parseInt(id));
        this.getValorMaturidadesByEsteiraIdAndProcesso(parseInt(id));
        this.getValorMaturidadesByEsteiraIdAndMetrica(parseInt(id));

      }
    });
  }



  public async setCurrent(id: number) {
    this.getValorMaturidadesByEsteiraIdAndTecnica(id);
   /* const valorMaturidade = this.valorDosIndicesDeMaturidade.find(
      (valorMaturidade) => valorMaturidade.maturidade.esteira.id === id
    );
    if (valorMaturidade && valorMaturidade.maturidade && valorMaturidade.maturidade.esteira) {
      this.currentValorMaturidade = valorMaturidade;
      this.currentValorMaturidade.maturidade.esteira = valorMaturidade.maturidade.esteira;
      this.getValorMaturidadesByEsteiraIdAndTecnica(valorMaturidade.maturidade.esteira.id);
      this.getValorMaturidadesByEsteiraIdAndProcesso(valorMaturidade.maturidade.esteira.id);
      this.getValorMaturidadesByEsteiraIdAndMetrica(valorMaturidade.maturidade.esteira.id);
    }*/
  }


  public getValorMaturidades(): void {
    this.valorMaturidadeService.getValorMaturidades().subscribe((response) => {
      this.valorMaturidades = response;
    });
  }


  public getValorMaturidadesByEsteiraIdAndTecnica(id: number): void{
    this.valorMaturidadeService.getValorMaturidadesByEsteiraIdAndTecnica(id).subscribe((response) =>{
      this.valorMaturidadeTecnica = response;
      console.log(this.currentValorMaturidade)
    });
  }

  public getValorMaturidadesByEsteiraIdAndProcesso(id: number): void{
    this.valorMaturidadeService.getValorMaturidadesByEsteiraIdAndProcesso(id).subscribe((response) =>{
      this.valorMaturidadeProcesso = response;
    });
  }

  public getValorMaturidadesByEsteiraIdAndMetrica(id: number): void{
    this.valorMaturidadeService.getValorMaturidadesByEsteiraIdAndMetrica(id).subscribe((response) =>{
      this.valorMaturidadeMetrica = response;
    });
  }



}
