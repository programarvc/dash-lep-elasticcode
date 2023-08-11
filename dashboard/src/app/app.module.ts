import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

//Components
import { FilterComponent } from './components/filter/filter.component';
import { SearchBarComponent } from './components/search-bar/search-bar.component';

//Pages
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AllysonComponent } from './pages/allyson/allyson.component';
import { EmillyComponent } from './pages/emilly/emilly.component';
import { JakelineComponent } from './pages/jakeline/jakeline.component';
import { LeandroComponent } from './pages/leandro/leandro.component';
import { MavisComponent } from './pages/mavis/mavis.component';
import { MonalizaComponent } from './pages/monaliza/monaliza.component';
import { RobertoComponent } from './pages/roberto/roberto.component';
import { TiagorComponent } from './pages/tiagor/tiagor.component';
import { IgorComponent } from './pages/igor/igor.component';

@NgModule({
  declarations: [
    AppComponent,
    FilterComponent,
    SearchBarComponent,
    LeandroComponent,
    JakelineComponent,
    AllysonComponent,
    EmillyComponent,
    MavisComponent,
    MonalizaComponent,
    RobertoComponent,
    TiagorComponent,
    IgorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
