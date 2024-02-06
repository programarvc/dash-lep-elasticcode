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
