export interface JornadaDeTransformacao {
  id: number;
  capacidadeDora: number;
  metricas4: number;
  saude: number;
  maturidade: Maturidade;
  mediaDeJornada: number;
}

export interface JornadaDeTransformacaoByEsteiraId {
  id: number;
  capacidadeDora: number;
  metricas4: number;
  saude: number;
  maturidade: Maturidade;
  mediaDeJornada: number;
}

export interface Maturidade {
  id: number;
  esteira: EsteiraDeDesenvolvimento;
  data: string;
  numero: number;
  leadTime: number;
  frequencyDeployment: number;
  changeFailureRate: number;
  timeToRecovery: number;
}

export interface EsteiraDeDesenvolvimento {
  id: number;
  nome: string;
  tipo: TiposEnum;
  empresa: Empresa;
}

export enum TiposEnum {
  PLANEJAMENTO = 'PLANEJAMENTO',
  DESENVOVIMENTO = 'DESENVOVIMENTO',
  INTEGRACAO = 'INTEGRACAO',
  TESTE = 'TESTE',
  IMPLANTAÇÃO = 'IMPLANTAÇÃO',
  MONITORAMENTO = 'MONITORAMENTO',
}

export interface Empresa {
  id: number;
  nome: string;
}

export interface CapacidadesRecomendadas {
  id: number;
  maturidade: Maturidade;
  itemDeMaturidade: ItemDeMaturidade;
}

export interface ItemDeMaturidade {
  id: number;
  tipoMaturidade: TiposMaturidadeEnum;
  nome: string;
}

export enum TiposMaturidadeEnum {
  TECNICA = 'TECNICA',
  PROCESSO = 'PROCESSO',
  METRICA = 'METRICA',
  CULTURA = 'CULTURA',
}

