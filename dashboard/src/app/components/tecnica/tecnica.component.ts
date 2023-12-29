import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {
  IndicesDeMaturidade,
  TiposEnum,
  Empresa,
  Maturidade,
  valorDosIndicesDeMaturidadeId,
} from 'src/app/types/tecnica-types';
import { EsteiraService } from 'src/services/esteira/esteira.service';
import { EmpresaService } from 'src/services/empresa/empresa.service';
import { MaturidadeService } from 'src/services/maturidade/maturidade.service';
import { TecnicaService } from 'src/services/tecnica/tecnica.service';
@Component({
  selector: 'app-tecnica',
  templateUrl: './tecnica.component.html',
  styleUrls: ['./tecnica.component.sass']
})
export class TecnicaComponent implements OnInit {

  public indices: IndicesDeMaturidade[] = [];
  public currentIndices: IndicesDeMaturidade = {
    id: 0,
    
    tipo: '' as TiposEnum,
    empresa: {
      id: 0,
      
    },
  };


  public currentMaturidade: Maturidade = {

    esteira: {
      id: 0,
      nome: '',
      tipo: '' as TiposEnum,
      empresa: {
        id: 0,
        nome: '',
      },
    },
    
    valor_atingido: 0,
    valor_esperado: 0,
    item_de_maturidade: 0,
    maturidade_id: 0,
  }


  public empresas: Empresa[] = [];
  public maturidade: Maturidade[] = [];
  public tipo: TiposEnum[] = [];
  public valorDosIndicesDeMaturidadeId: valorDosIndicesDeMaturidadeId[] = [];


  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private esteiraService: EsteiraService,
    private empresaService: EmpresaService,
    private maturidadeService: MaturidadeService,
    private TecnicaService: TecnicaService,
    
  ) { }

  ngOnInit(): void {
    this.getMaturidade();
    this.route.paramMap.subscribe((params) => {
      const id = params.get('maturidade_id');
      if (id) {
        this.setCurrent(parseInt(id));
        this.getTecnicaByEsteiraId(parseInt(id));
        this.getMaturidadeById(parseInt(id));
        /*this.getEsteiraById(parseInt(id));*/
      }
    });
  }


  public async setCurrent(id: number) {
    const maturidade= this.maturidade.find(
      (maturidade) => maturidade.esteira.id === id
    );
    if (maturidade) {
      this.currentMaturidade = maturidade;
      this.currentIndices = maturidade.esteira;

console.log(this.currentMaturidade);
console.log(this.currentIndices);
    }
  }



  public async getMaturidade() {
    this.esteiraService.getEsteiras().subscribe((response) => {
      this.indices = response;
      const id = this.route.snapshot.paramMap.get('esteiraId');
      if (id) {
        this.setCurrent(parseInt(id));
      } else {
        if (this.indices.length > 0) {
          this.setCurrent(this.indices[0].id);
          this.router.navigate([`dashboard/${this.indices[0].id}`]);
        }
      }
    });
  }

  /*getEsteiras(): void {
    this.esteiraService.getEsteiras().subscribe((esteiras) => {
      this.esteiras = esteiras;
    });
  }*/

  /*getEsteiraById(id: number): void {
    this.esteiraService.getEsteiraById(id).subscribe((esteira) => {
      this.esteiras = esteira;
    });
  }*/

  getTecnicaByEsteiraId(id: number): void {
    this.TecnicaService.getTecnicaByEsteiraId(id).subscribe((maturidade) => {
      this.valorDosIndicesDeMaturidadeId = maturidade;
    });
  }

  getMaturidadeById(id: number): void {
    this.maturidadeService.getMaturidadeById(id).subscribe((data: Maturidade) => {
      this.currentMaturidade = data;
    });
  }



}
