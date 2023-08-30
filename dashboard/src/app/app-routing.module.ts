import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ColaboradorComponent } from './pages/colaborador/colaborador.component';

const routes: Routes = [
  { path: ':colaboradorId', component: ColaboradorComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
