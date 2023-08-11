import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


import { AllysonComponent } from './pages/allyson/allyson.component';
import { EmillyComponent } from './pages/emilly/emilly.component';
import { JakelineComponent } from './pages/jakeline/jakeline.component';
import { LeandroComponent } from './pages/leandro/leandro.component';
import { MavisComponent } from './pages/mavis/mavis.component';
import { MonalizaComponent } from './pages/monaliza/monaliza.component';
import { RobertoComponent } from './pages/roberto/roberto.component';
import { TiagorComponent } from './pages/tiagor/tiagor.component';
import { IgorComponent } from './pages/igor/igor.component';

const routes: Routes = [
  { path: "", component: AllysonComponent , pathMatch: 'full'},
  { path: "jakeline", component: JakelineComponent },
  { path: "leandro", component: LeandroComponent},
  { path: "emilly", component: EmillyComponent},
  { path: "roberto", component: RobertoComponent},
  { path: "tiagor", component: TiagorComponent},
  { path: "monaliza", component: MonalizaComponent},
  { path: "mavis", component: MavisComponent},
  { path: "igor", component: IgorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
