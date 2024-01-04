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

export interface Empresa {
    id: number;
    nome: string;
}

export enum TiposEnum {
    PLANEJAMENTO = "PLANEJAMENTO",
    DESENVOVIMENTO = "DESENVOVIMENTO",
    INTEGRACAO = "INTEGRACAO",
    TESTE = "TESTE",
    IMPLANTAÇÃO = "IMPLANTAÇÃO",
    MONITORAMENTO = "MONITORAMENTO",
}  