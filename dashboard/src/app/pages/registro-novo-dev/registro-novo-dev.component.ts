import { Component, OnInit } from '@angular/core';

import {
  HabilidadeService
} from 'src/services/habilidade/habilidade.service'

@Component({
  selector: 'app-registro-novo-dev',
  templateUrl: './registro-novo-dev.component.html',
  styleUrls: ['./registro-novo-dev.component.sass']
})
export class RegistroNovoDevComponent implements OnInit {
  public nome: string ='';
  public email: string ='';
  public github: string ='';
  public jira: string ='';
  public bio: string ='';

  public stacksDev: any = [];
  public stackSelecionado: number = 0;

  public tecnologiasDev: any = [];
  public tecnologiaSelecionada: any = [];



  constructor(
    private habilidadeService: HabilidadeService
  ) { }

  ngOnInit(): void {
    //Armazena o retorno do méotdo de getStacks() em um stackDev
    this.habilidadeService.getStacks().subscribe((stacks : any) => {
      this.stacksDev = stacks;
    })

    //Armazena o retorno do méotdo de getTecnologias() em um tecnologiasDev
    this.habilidadeService.getTecnologias().subscribe((tecnologias : any) => {
      this.tecnologiasDev = tecnologias;
    })
  }

  public cadastrarDev(): void {
    this.tecnologiaSelecionada = this.tecnologiasDev
      .filter((tecnologia: any) => tecnologia.selecionada)
      .map((tecnologia: any) => tecnologia.id);
    console.log('Desenvolvedor cadastrado com sucesso!');
    const devData = {
      nome: this.nome,
      email: this.email,
      github: this.github,
      jira: this.jira,
      bio: this.bio,
      stack: this.stackSelecionado,
      tecnologias: this.tecnologiaSelecionada
    };
    console.log(devData);
  }

}
