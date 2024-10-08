import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import { AmplifyAuthenticatorModule } from '@aws-amplify/ui-angular';
import { ReactiveFormsModule } from '@angular/forms'; 


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgChartsModule } from 'ng2-charts';
import { Amplify } from 'aws-amplify';
import awsconfig from 'aws-exports';


//Components
import { SearchBarComponent } from './components/search-bar/search-bar.component';
import { SidebarMenuComponent } from './components/sidebar-menu/sidebar-menu.component';
import { TecnicaComponent } from './components/tecnica/tecnica.component';
import { MaturidadeLgComponent } from './components/maturidade-lg/maturidade-lg.component';
import { MaturidadeSmComponent } from './components/maturidade-sm/maturidade-sm.component';

//Pages
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';
import { ColaboradorComponent } from './pages/colaborador/colaborador.component';
import { DashProjetoComponent } from './pages/dash-projeto/dash-projeto.component';
import { AmplifyUiLoginComponent } from './pages/amplify-ui-login/amplify-ui-login.component';
import { TimeComponent } from './pages/time/time.component';
import { GenAiForDevsComponent } from './pages/gen-ai-for-devs/gen-ai-for-devs.component';
import { RegistroNovoDevComponent } from './pages/registro-novo-dev/registro-novo-dev.component';
import { PromptHistoryModalComponent } from './components/prompt-history-modal/prompt-history-modal.component';

Amplify.configure(awsconfig);

@NgModule({
  
  declarations: [
    AppComponent,
    SearchBarComponent, 
    ColaboradorComponent, 
    DashProjetoComponent, 
    SidebarMenuComponent, 
    TecnicaComponent, 
    MaturidadeLgComponent, 
    MaturidadeSmComponent, 
    AmplifyUiLoginComponent, 
    TimeComponent,
    GenAiForDevsComponent,
    RegistroNovoDevComponent,
    PromptHistoryModalComponent,
  ],
  imports: [
    HttpClientModule, 
    BrowserModule, 
    AppRoutingModule, 
    FormsModule, 
    NgbModule, 
    AmplifyAuthenticatorModule,
    ReactiveFormsModule,
    NgChartsModule
  ],
  providers: [],
  bootstrap: [AppComponent],

})
export class AppModule {}