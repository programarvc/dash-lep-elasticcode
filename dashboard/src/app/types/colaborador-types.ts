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

export interface Competencia {
  id: number;
  nome: string;
}

export interface CompetenciaByColaborador {
  id: number;
  competencia: Competencia;
  progresso: number;
  meta: number;
}

export interface Acao {
  id: number;
  nome: string;

}

export interface AcaoByColaborador {
  id: number;
  acao: Acao;
  progresso: number;
}

export interface Empresa {
  id: number;
  nome: string;
}

export interface EmpresaByColaborador {
  id: number;
  empresa: Empresa;
  colaborador: Colaborador;
}
