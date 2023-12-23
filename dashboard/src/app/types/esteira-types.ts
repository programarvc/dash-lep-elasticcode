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

export interface Maturidade {
  esteira: EsteiraDeDesenvolvimento;
  data: string;
  numero: number;
  leadTime: number;
  frequencyDeployment: number;
  changeFailureRate: number;
  timeToRecovery: number;
}

export interface MaturidadeByEsteiraId{
  id: number;
  esteira: EsteiraDeDesenvolvimento;
  data: string;
  numero: number;
  leadTime: number;
  frequencyDeployment: number;
  changeFailureRate: number;
  timeToRecovery: number;
}




