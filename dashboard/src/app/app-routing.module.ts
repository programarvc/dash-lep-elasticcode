import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth.guard';

import { ColaboradorComponent } from './pages/colaborador/colaborador.component';
import { DashProjetoComponent } from './pages/dash-projeto/dash-projeto.component';
import { SignInComponent } from './pages/sign-in/sign-in.component';



const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full'},
  { path: 'login', component: SignInComponent },
  { path: 'dashboard/:esteiraId', title: 'Dashboard Projeto', component: DashProjetoComponent, canActivate: [AuthGuard]},
  { path: ':colaboradorId', component: ColaboradorComponent, canActivate: [AuthGuard] },
  { path: '**', redirectTo: 'login'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
