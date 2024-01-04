import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {
  Empresa,
  TiposEnum,
  Maturidade,
  EsteiraDeDesenvolvimento,
  ItemDeMaturidade,
  TiposMaturidadeEnum,
  ValorDosIndicesDeMaturidade,
  ValorDosIndicesDeMaturidadeByEsteiraIdAndTipo
} from 'src/app/types/valorMaturidade-types';
import { EsteiraService } from 'src/services/esteira/esteira.service';
import { EmpresaService } from 'src/services/empresa/empresa.service';
import { MaturidadeService } from 'src/services/maturidade/maturidade.service';
import { valorMaturidadeService } from './../../../services/valor-maturidade/valor-maturidade.service';
@Component({
  selector: 'app-tecnica',
  templateUrl: './tecnica.component.html',
  styleUrls: ['./tecnica.component.sass']
})


export class TecnicaComponent implements OnInit {

  baseTabela:number[] = [0,10,20,30,40,50,60,70,80,90,100];

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
  public valorDosIndicesDeMaturidade: ValorDosIndicesDeMaturidade[] = [];
  public valorDosIndicesDeMaturidadeByEsteiraIdAndTipo: ValorDosIndicesDeMaturidadeByEsteiraIdAndTipo[] =[];

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private esteiraService: EsteiraService,
    private empresaService: EmpresaService,
    private maturidadeService: MaturidadeService,
    private valorMaturidadeService: valorMaturidadeService,
  ) { }

  ngOnInit(): void {
    this.getValorMaturidades();
    this.route.paramMap.subscribe((params) => {
      const id = params.get('esteiraId');
      if (id) {
        this.setCurrent(parseInt(id));
        this.getValorMaturidadesByEsteiraIdAndTipo(parseInt(id));

      }
    });
  }



  public async setCurrent(id: number) {
    const valorMaturidade = this.valorDosIndicesDeMaturidadeByEsteiraIdAndTipo.find(
      (valorMaturidade) => valorMaturidade.maturidade.esteira.id === id && valorMaturidade.itemDeMaturidade.tipoMaturidade === String(this.tiposMaturidade)
    );
    if (valorMaturidade && valorMaturidade.maturidade && valorMaturidade.maturidade.esteira && valorMaturidade.itemDeMaturidade && valorMaturidade.itemDeMaturidade.tipoMaturidade) {
      this.currentValorMaturidade = valorMaturidade;
      this.currentValorMaturidade.maturidade.esteira = valorMaturidade.maturidade.esteira;
      this.getValorMaturidadesByEsteiraIdAndTipo(valorMaturidade.maturidade.esteira.id);
      console.log(this.currentValorMaturidade);
    }
  }


  getValorMaturidades(): void {
    this.valorMaturidadeService
      .getValorMaturidades()
      .subscribe((response) => {
        this.valorDosIndicesDeMaturidade = response;
      });
  }

  getValorMaturidadesByEsteiraIdAndTipo(id: number): void {
    this.valorMaturidadeService
      .getValorMaturidadesByEsteiraIdAndTipo(id)
      .subscribe((data: ValorDosIndicesDeMaturidadeByEsteiraIdAndTipo) => {
        this.currentValorMaturidade = data;
      });
  }
}
