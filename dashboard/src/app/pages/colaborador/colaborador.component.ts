import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Colaborador, CompetenciaByColaborador } from 'src/app/types/colaborador-types';
import { ColaboradorService } from 'src/services/colaborador/colaborador.service';
import { CompetenciaService } from 'src/services/competencia/competencia.service';

@Component({
  selector: 'app-colaborador',
  templateUrl: './colaborador.component.html',
  styleUrls: ['./colaborador.component.sass'],
})
export class ColaboradorComponent implements OnInit {
  public colaboradores: Colaborador[] = [];
  public currentColaborador: Colaborador = {
    id: 0,
    nome: '',
    email: '',
    github: '',
    habilidades: [],
  };
  public competencias: CompetenciaByColaborador[] = [];

  constructor(
    private route: ActivatedRoute,
    private colaboradorService: ColaboradorService,
    private competenciaService: CompetenciaService,
  ) {}

  async ngOnInit(): Promise<void> {
    await this.getColaboradores();
    this.route.paramMap.subscribe((params) => {
      const id = params.get('colaboradorId');
      if (id) {
        this.setCurrent(id);
      }
    });
  }

  public async setCurrent(id: string) {
    const colaborador = this.colaboradores.find(
      (colaborador) => colaborador.id === parseInt(id)
    );
    if (colaborador) {
      this.currentColaborador = colaborador;
      await this.getCompetencias(colaborador.id);
    }
  }

  public async getColaboradores() {
    this.colaboradorService.getAllColaboradores().subscribe((response) => {
      this.colaboradores = response;
      const id = this.route.snapshot.paramMap.get('colaboradorId');
      if (id) {
        this.setCurrent(id);
      }
    });
  }

  public async getCompetencias(id: number) {
    this.competenciaService.getCompetenciasByColaborador(id).subscribe((response) => {
      this.competencias = response;
    });
  }
}
