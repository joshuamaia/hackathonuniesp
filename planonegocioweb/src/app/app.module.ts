import { HomeModule } from './pages/home/home.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { TopoComponent } from './layout/topo/topo.component';
import { RodapeComponent } from './layout/rodape/rodape.component';
import { DashboardComponent } from './layout/dashboard/dashboard.component';
import { MenuComponent } from './layout/menu/menu.component';

import { LOCALE_ID } from '@angular/core';
import localePt from '@angular/common/locales/pt';
import { registerLocaleData } from '@angular/common';
import { SharedModule } from './shared/shared.module';
import { HttpClientModule } from '@angular/common/http';
import { NgHttpLoaderModule } from 'ng-http-loader';
registerLocaleData(localePt);

@NgModule({
  declarations: [
    AppComponent,
    TopoComponent,
    RodapeComponent,
    DashboardComponent,
    MenuComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    NgHttpLoaderModule.forRoot(),
    SharedModule,
    HomeModule,
  ],
  providers: [
    {
      provide: LOCALE_ID,
      useValue: 'pt-BR',
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
