import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import { AmplifyAuthenticatorModule } from '@aws-amplify/ui-angular';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { Amplify } from 'aws-amplify';
import awsconfig from 'aws-exports';

//Components
import { SearchBarComponent } from './components/search-bar/search-bar.component';
import { SidebarMenuComponent } from './components/sidebar-menu/sidebar-menu.component';
import { TecnicaComponent } from './components/tecnica/tecnica.component';

//Pages
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';
import { ColaboradorComponent } from './pages/colaborador/colaborador.component';
import { DashProjetoComponent } from './pages/dash-projeto/dash-projeto.component';
import { LoginComponent } from './pages/login/login.component';
import { SignInComponent } from './pages/sign-in/sign-in.component';
import { AmplifyUiLoginComponent } from './pages/amplify-ui-login/amplify-ui-login.component';

Amplify.configure(awsconfig);

@NgModule({
  declarations: [AppComponent, SearchBarComponent, ColaboradorComponent, DashProjetoComponent, SidebarMenuComponent, TecnicaComponent, LoginComponent, SignInComponent, AmplifyUiLoginComponent],
  imports: [HttpClientModule, BrowserModule, AmplifyAuthenticatorModule, AppRoutingModule,FormsModule, NgbModule, FormsModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
