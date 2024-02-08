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


    // Adicione esta propriedade ao seu componente
    public uniqueTiposMaturidade: Set<string> = new Set();

    // ...



    private updateUniqueTiposMaturidade(): void {
        // Limpe o conjunto antes de atualizar
        this.uniqueTiposMaturidade.clear();

        // Preencha o conjunto com valores únicos
        this.valorMaturidades.forEach((valor) => {
            if (valor.itemDeMaturidade.tipoMaturidade) {
                this.uniqueTiposMaturidade.add(valor.itemDeMaturidade.tipoMaturidade);

            }
        });
    }

    // ...


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

  public empresas: Empresa[] = [];
  public maturidade: Maturidade[] = [];
  public tipo: TiposEnum[] = [];
  public tiposMaturidade: TiposMaturidadeEnum[] = [];
  public maturidadeByEsteiraId: Maturidade[] = [];
  public valorMaturidadeTecnica: ValorDosIndicesDeMaturidadeByEsteiraIdAndTecnica[] = [];
  public valorMaturidadeProcesso:  ValorDosIndicesDeMaturidadeByEsteiraIdAndTecnica[] =[];
  public valorMaturidadeMetrica:  ValorDosIndicesDeMaturidadeByEsteiraIdAndTecnica[] =[];
  public valorMaturidadeC:  ValorDosIndicesDeMaturidade[] = [];
  public selectedOption: number;
  public valorMaturidadeDate: Date;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private esteiraService: EsteiraService,
    private empresaService: EmpresaService,
    private valorMaturidadeService: valorMaturidadeService


  ) {
    this.selectedOption = 0;
    this.valorMaturidadeDate = new Date();

   }

  ngOnInit(): void {
    this.getValorMaturidades();
    this.route.paramMap.subscribe((params) => {
      const id = params.get('esteiraId');
      if (id) {
        this.setCurrent(parseInt(id));
        this.getValoresByEsteiraIdAndTipoMaturidadeTecnica(parseInt(id));
        this.getValorMaturidadesByEsteiraIdAndProcesso(parseInt(id));
        this.getValorMaturidadesByEsteiraIdAndMetrica(parseInt(id));

      }
      const maturidadeId = params.get('maturidadeId');
      if (maturidadeId ) {
        this.getValorDoIndicesByMaturidadeId
      }
    });
  }

  public async setCurrent(id: number) {
     this.uniqueTiposMaturidade = new Set(this.valorMaturidades.map(valor => valor.itemDeMaturidade.tipoMaturidade));
     this.getValoresByEsteiraIdAndTipoMaturidadeTecnica(id);
     this.getValorMaturidadesByEsteiraIdAndProcesso(id);
     this.getValorMaturidadesByEsteiraIdAndMetrica(id);
  }

  


  public getValorMaturidades(): void {
    this.valorMaturidadeService.getValorMaturidades().subscribe((response) => {
      this.valorMaturidades = response;
        // Atualize o conjunto de tiposMaturidade
        this.updateUniqueTiposMaturidade();

    });
  }



  public getValorMaturidadesByEsteiraIdAndProcesso(id: number): void{
    this.valorMaturidadeService.getValorMaturidadesByEsteiraIdAndProcessoLatest(id).subscribe((response) =>{
      this.valorMaturidadeProcesso = response;
    });
  }

  public getValorMaturidadesByEsteiraIdAndMetrica(id: number): void{
    this.valorMaturidadeService.getValorMaturidadesByEsteiraIdAndMetricaLatest(id).subscribe((response) =>{
      this.valorMaturidadeMetrica = response;
    });
  }

  getValorDoIndicesByMaturidadeId(maturidadeId: number): void {
    this.valorMaturidadeService
      .getValorDoIndicesByMaturidadeId(maturidadeId)
      .subscribe((valorMaturidade) => {
        this.valorMaturidadeC = valorMaturidade;
        console.log(this.valorMaturidadeC);
      });
  }

  calculateWidth(): number {
    // Substitua isso pela lógica real de cálculo da largura
    // Exemplo: largura base + (número de barras * largura de cada barra)
    return 200 + (this.valorMaturidadeTecnica.length * 30);
  }


  getValoresByEsteiraIdAndTipoMaturidadeTecnica(id: number): void{
    this.valorMaturidadeService.getValoresByEsteiraIdAndTipoMaturidadeTecnicaLatest(id).subscribe((response) =>{
      this.valorMaturidadeTecnica = response;
    });

  }

  onOptionChange(event: Event) {
    let selectElement = event.target as HTMLSelectElement;
    this.selectedOption = Number(selectElement.value);

    let selectedOptionElement = selectElement.options[selectElement.selectedIndex];
    let maturidadeId = Number(selectedOptionElement.getAttribute('data-id'));

    this.getValorDoIndicesByMaturidadeId(maturidadeId);
  }

}
