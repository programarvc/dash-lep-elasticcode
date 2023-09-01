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
}