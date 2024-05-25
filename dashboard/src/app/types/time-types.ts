import { Competencia } from "./colaborador-types";

  export interface Time {
    id: number;
    nomeTime: string;
    esteira: EsteiraDeDesenvolvimento;
  }

  export interface TimeColaborador {
    id: number;
    time: Time;
    colaborador: Colaborador;

  }

  export interface Colaborador {
    id: number;
    nome: string;
    email: string;
    github: string;
    miniBio: string;
    habilidades: Habilidade[];

  }

  export interface Habilidade {
    id: number;
    nome: string;
    backend: boolean;
  }

  export interface EsteiraDeDesenvolvimento {
    id: number;
    nome: string;
    tipo: TiposEnum;
    empresa: Empresa;
  }

  export enum TiposEnum {
    PLANEJAMENTO = "PLANEJAMENTO",
    DESENVOVIMENTO = "DESENVOVIMENTO",
    INTEGRACAO = "INTEGRACAO",
    TESTE = "TESTE",
    IMPLANTAÇÃO = "IMPLANTAÇÃO",
    MONITORAMENTO = "MONITORAMENTO",
  }

  export interface Empresa {
    id: number;
    nome: string;
  }
    


  export interface MetasColaborador {
    id: number;
    nota: number[];
    colaborador: Colaborador;
    competencia: CompetenciaNota[];
    data: string[];
  }

export interface MetasColaboradorPorData {
  data: string; // ou Date
  metasColaborador: MetasColaborador[];
}

export interface CompetenciaNota {
  id: number;
  competencia: string;
  nota: number;
}

export interface CompetenciasPorData {
  [data: string]: CompetenciaNota[];
}

export type DataArray = number[];

export interface AllLatestMetaByColaboradorId {
  id: number;
  data: string;
}


    /*
    export interface PrFromGithub {
      id: number;
      prAuthor: string;
      createdAt: string;
      mergedAt: string;
      repoName: string;
      colaborador: Colaborador;
      countPr: number;
      nome: string;

    }
    */

  export interface IndiceDeSobrevivenciaDev {
    id: number;
    timeColaborador: TimeColaborador;
    valorIndice: number;
  }

  export interface  VcsPullRequest {
    id: number;
    title: string;
    mergedAt: string;
    author: string;
    colaborador: Colaborador;
    countpr: number;
   }

  export interface TasksCountJira{
    id: number;
    taskName: string; 
    statusDetail: string;
    mergedAt: string;
    author: string;
    colaborador: Colaborador;
    counttasks: number;
   }