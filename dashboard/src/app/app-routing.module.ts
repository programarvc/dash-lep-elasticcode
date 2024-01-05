import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ColaboradorComponent } from './pages/colaborador/colaborador.component';
import { DashProjetoComponent } from './pages/dash-projeto/dash-projeto.component';
import { TecnicaComponent } from './components/tecnica/tecnica.component';

const routes: Routes = [
  { path: 'dash-projeto', title: 'Dashboard Projeto', component: DashProjetoComponent},
  { path: 'tecnica', title: 'Tecnica', component: TecnicaComponent},
  { path: 'dashboard/:esteiraId', title: 'Dashboard Projeto', component: DashProjetoComponent},
  { path: ':colaboradorId', component: ColaboradorComponent },
  { path: '', component: ColaboradorComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
