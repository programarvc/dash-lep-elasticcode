import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth.guard';

import { ColaboradorComponent } from './pages/colaborador/colaborador.component';
import { DashProjetoComponent } from './pages/dash-projeto/dash-projeto.component';
import { AmplifyUiLoginComponent } from './pages/amplify-ui-login/amplify-ui-login.component';
import { TimeComponent } from './pages/time/time.component';
import { GenAiForDevsComponent } from './pages/gen-ai-for-devs/gen-ai-for-devs.component';



const routes: Routes = [
  { path: 'login', component: AmplifyUiLoginComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full'},
  { path: 'dashboard/:esteiraId', title: 'Dashboard Projeto', component: DashProjetoComponent, canActivate: [AuthGuard]},
  { path: 'time', title:'Dashboard Time', pathMatch:'full', component: TimeComponent, canActivate: [AuthGuard] },
  { path: 'time/:esteiraId', title:'Dashboard Time', pathMatch:'full', component: TimeComponent, canActivate: [AuthGuard] },
  { path: 'genAi-for-devs', title: 'GEN.AI for Devs', component: GenAiForDevsComponent, canActivate: [AuthGuard]},
  { path: ':colaboradorId', pathMatch: 'full',component: ColaboradorComponent, canActivate: [AuthGuard] },
  { path: '**', redirectTo: 'login'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
