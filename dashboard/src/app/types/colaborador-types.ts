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
