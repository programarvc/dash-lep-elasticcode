import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

//Components
import { SearchBarComponent } from './components/search-bar/search-bar.component';

//Pages
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { HttpClientModule } from '@angular/common/http';
import { ColaboradorComponent } from './pages/colaborador/colaborador.component';
import { FormsModule } from '@angular/forms';
@NgModule({
  declarations: [AppComponent, SearchBarComponent, ColaboradorComponent],
  imports: [HttpClientModule, BrowserModule, AppRoutingModule,FormsModule, NgbModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
