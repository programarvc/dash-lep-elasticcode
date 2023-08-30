import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Colaborador } from 'src/app/types/colaborador-types';
import { ColaboradorService } from 'src/services/colaborador/colaborador.service';

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

  constructor(
    private route: ActivatedRoute,
    private colaboradorService: ColaboradorService
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

  public setCurrent(id: string) {
    const colaborador = this.colaboradores.find(
      (colaborador) => colaborador.id === parseInt(id)
    );
    if (colaborador) {
      this.currentColaborador = colaborador;
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
}
