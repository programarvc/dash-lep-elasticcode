export interface IndicesDeMaturidade {
      id: number;
      nome: string;
      tipo: TiposEnum;
      valorAtingido: number;
      empresa: Empresa;
  }
  export interface Maturidade {
      esteira: IndicesDeMaturidade;
      valorEsperado: number;
      valorAtingido: number;
      maturidade_id: number;
      item_de_maturidade: number;
  }

  export interface Empresa {
    id: number;
    nome: string;
  }
  

  export enum TiposEnum {
    ARQUITETURA = "ARQUITETURA",
    GER_MUDANCA = "GER_MUDANCA",
    AUTO_IMPLANTACAO = "AUTO_IMPLANTACAO",
    GEREN_TESTE = "GEREN_TESTE",
    INTEGRACAO_CONTINUA = "INTEGRACAO_CONTINUA",
    FERRAMENTA_TIMES = "FERRAMENTA_TIMES",
    CONTROLE_VERSAO = "CONTROLE_VERSAO",
    MANUT_CODIGO = "MANUT_CODIGO",
    // Adicione outros tipos conforme necessário
  }
  
  
  export interface valorDosIndicesDeMaturidadeId{
    id: number;
    esteira: IndicesDeMaturidade;
    nome: string;
    valorEsperado: number;
    valorAtingido: number;
    maturidade_id: number;
    item_de_maturidade: number;

  }
  
  
  
  
  