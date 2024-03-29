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
  IMPLANTACAO = "IMPLANTACAO",
  MONITORAMENTO = "MONITORAMENTO",
}

export interface Empresa {
  id: number;
  nome: string;
}

export interface Maturidade {
  id: number,
  esteira: EsteiraDeDesenvolvimento;
  dataHora: number[];
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


export interface MaturidadeByEsteiraId{
  id: number;
  esteira: EsteiraDeDesenvolvimento;
  dataHora: string;
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




