import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ColaboradorComponent } from './pages/colaborador/colaborador.component';
import { DashProjetoComponent } from './pages/dash-projeto/dash-projeto.component';
import { LoginComponent } from './pages/login/login.component';
import { AuthGuard } from './auth.guard';


const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'dash-projeto', title: 'Dashboard Projeto', component: DashProjetoComponent},
  { path: 'dashboard/:esteiraId', title: 'Dashboard Projeto', component: DashProjetoComponent},
  { path: ':colaboradorId', component: ColaboradorComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
