import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './components/app.component';
import { IndicatorsSectionComponent } from './components/indicators-section';
import { IndicatorSectionComponent } from './components/indicator-section.component';
import { TemperatureIndicatorComponent } from './components/temperature-indicator';
import { HumidityIndicatorComponent } from './components/humidity-indicator';

import { HttpModule } from '@angular/http';

import { HumidityService } from './services/humidity.service';
import { TemperatureService } from './services/temperature.service';

@NgModule({
  declarations: [
    AppComponent,
    IndicatorsSectionComponent,
    IndicatorSectionComponent,
    TemperatureIndicatorComponent,
    HumidityIndicatorComponent
  ],
  imports: [
    BrowserModule,
    HttpModule
  ],
  providers: [HumidityService, TemperatureService],
  bootstrap: [AppComponent]
})
export class AppModule { }
