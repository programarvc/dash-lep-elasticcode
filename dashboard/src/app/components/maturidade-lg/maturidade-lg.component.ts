import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-maturidade-lg',
  templateUrl: './maturidade-lg.component.html',
  styleUrls: ['./maturidade-lg.component.sass']
})
export class MaturidadeLgComponent implements OnInit {

  baseTabela:number[] = [0,10,20,30,40,50,60,70,80,90,100];

  maturidadeItem: string[] = [
    'Arquitetura',
    'Ger. de Mudança',
    'Auto. Implementação',
    'Geren. de Teste',
    'Integração Contínua',
    'Ferramenta de Times',
    'Controle de Versão',
    'Manu. de Código'
  ]

  constructor() { }

  ngOnInit(): void {
  }

}
