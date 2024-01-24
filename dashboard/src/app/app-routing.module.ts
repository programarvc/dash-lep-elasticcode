import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth.guard';

import { ColaboradorComponent } from './pages/colaborador/colaborador.component';
import { DashProjetoComponent } from './pages/dash-projeto/dash-projeto.component';
import { AmplifyUiLoginComponent } from './pages/amplify-ui-login/amplify-ui-login.component';
import { TimeComponent } from './pages/time/time.component';



const routes: Routes = [
  { path: 'login', component: AmplifyUiLoginComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full'},
  { path: 'dashboard/:esteiraId', title: 'Dashboard Projeto', component: DashProjetoComponent, canActivate: [AuthGuard]},
  { path: 'time/:colaboradorId', title:'Time', component: TimeComponent, canActivate: [AuthGuard] },
  { path: ':colaboradorId', component: ColaboradorComponent, canActivate: [AuthGuard] },
  { path: '**', redirectTo: 'login'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
