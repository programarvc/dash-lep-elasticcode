import { Colaborador } from "./colaborador-types";

export interface ValorDosIndicesDeMaturidade {
    id: number;
    maturidade: Maturidade;
    itemDeMaturidade: ItemDeMaturidade;
    valorAtingido: number;
    valorEsperado: number;
}

export interface ValorDosIndicesDeMaturidadeByEsteiraIdAndTecnica {
    id: number;
    maturidade: Maturidade;
    itemDeMaturidade: ItemDeMaturidade;
    valorAtingido: number;
    valorEsperado: number;
}

export interface ValorDosIndicesDeMaturidadeByEsteiraIdAndCultura {
  id: number;
  maturidade: Maturidade;
  itemDeMaturidade: ItemDeMaturidade;
  valorAtingido: number;
  valorEsperado: number;
}

export interface ValorDosIndicesDeMaturidadeByEsteiraIdAndProcesso{
  id: number;
  maturidade: Maturidade;
  itemDeMaturidade: ItemDeMaturidade;
  valorAtingido: number;
  valorEsperado: number;
}

export interface ValorDosIndicesDeMaturidadeByEsteiraIdAndMetrica {
  id: number;
  maturidade: Maturidade;
  itemDeMaturidade: ItemDeMaturidade;
  valorAtingido: number;
  valorEsperado: number;
}

export interface ItemDeMaturidade {
    id: number;
    tipoMaturidade: TiposMaturidadeEnum;
    nome: string;
}

export enum TiposMaturidadeEnum {
    TECNICA = "TECNICA",
    PROCESSO = "PROCESSO",
    METRICA = "METRICA",
    CULTURA = "CULTURA",
    }

export interface Maturidade {
    id: number;
    esteira: EsteiraDeDesenvolvimento;
    dataHora: number[],
    numero: number;
    leadTime: number;
    frequencyDeployment: number;
    changeFailureRate: number;
    timeToRecovery: number;

    saude: number;
    metricas4: number;
    capacidadeDora: number;
    mediaDeJornada: number;
}

export interface EsteiraDeDesenvolvimento {
    id: number;
    nome: string;
    tipo: TiposEnum;
    empresa: Empresa;
}

export interface Empresa {
    id: number;
    nome: string;
}

export enum TiposEnum {
    PLANEJAMENTO = "PLANEJAMENTO",
    DESENVOVIMENTO = "DESENVOVIMENTO",
    INTEGRACAO = "INTEGRACAO",
    TESTE = "TESTE",
    IMPLANTACAO = "IMPLANTACAO",
    MONITORAMENTO = "MONITORAMENTO",
}

export interface ValorDosIndicesDeMaturidadeFilter {
  tipoMaturidade: string;
  nome: string;
  dataHora: number[];
  id: number;
  valorAtingido: number;
  valorEsperado: number;
  itemDeMaturidade: ItemDeMaturidade;
  maturidade: Maturidade;
}

export interface  VcsPullRequestTop5 {
  id: number;
  title: string;
  mergedAt: string;
  author: string;
  colaborador: Colaborador;
  countpr: number;
  repository: string;
  stateDetail: string;
 }
