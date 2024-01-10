import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';


import { AmplifyAuthenticatorModule } from '@aws-amplify/ui-angular';

//Components
import { SearchBarComponent } from './components/search-bar/search-bar.component';

//Pages
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { HttpClientModule } from '@angular/common/http';
import { ColaboradorComponent } from './pages/colaborador/colaborador.component';
import { FormsModule } from '@angular/forms';
import { DashProjetoComponent } from './pages/dash-projeto/dash-projeto.component';
import { SidebarMenuComponent } from './components/sidebar-menu/sidebar-menu.component';
import { TecnicaComponent } from './components/tecnica/tecnica.component';
import { LoginComponent } from './pages/login/login.component';

@NgModule({
  declarations: [AppComponent, SearchBarComponent, ColaboradorComponent, DashProjetoComponent, SidebarMenuComponent, TecnicaComponent, LoginComponent],
  imports: [HttpClientModule, BrowserModule, AppRoutingModule,FormsModule, NgbModule, AmplifyAuthenticatorModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
