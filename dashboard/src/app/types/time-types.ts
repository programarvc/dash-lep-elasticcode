export interface TimeEsteira {
    id: number;
    time: Time;
    colaborador: Colaborador;
    esteira: EsteiraDeDesenvolvimento;
}

export interface Time {
    id: number;
    nome: string;
}

export interface Colaborador {
    id: number;
    nome: string;
    email: string;
    github: string;
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
    IMPLANTACAO = "IMPLANTACAO",
    MONITORAMENTO = "MONITORAMENTO",
  }

    export interface Empresa {
        id: number;
        nome: string;
    }