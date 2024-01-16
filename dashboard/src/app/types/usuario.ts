export interface UserEsteira {
  id: number;
  username: string;
  esteira: EsteiraDeDesenvolvimento;
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

