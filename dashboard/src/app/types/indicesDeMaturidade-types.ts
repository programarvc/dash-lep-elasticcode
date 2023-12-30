export interface EsteiraDeDesenvolvimento {
  id: number;
  nome: string;
  tipo: TiposEnum;
  empresa: Empresa;
}
export interface ValorDosIndicesDeMaturidade {
      id: number;
      esteira: EsteiraDeDesenvolvimento;
      valorEsperado: number;
      valorAtingido: number;
      maturidade: number;
      itemDeMaturidade: number;
  }
  export interface Empresa {
    id: number;
    nome: string;
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
    esteira: EsteiraDeDesenvolvimento;
    data: string;
    numero: number;
    leadTime: number;
    frequencyDeployment: number;
    changeFailureRate: number;
    timeToRecovery: number;
  }
  export enum TiposEnum {
    PLANEJAMENTO = "PLANEJAMENTO",
    DESENVOVIMENTO = "DESENVOVIMENTO",
    INTEGRACAO = "INTEGRACAO",
    TESTE = "TESTE",
    IMPLANTAÇÃO = "IMPLANTAÇÃO",
    MONITORAMENTO = "MONITORAMENTO",
  }  
  