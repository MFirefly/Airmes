import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './components/app.component';

// Indicators of current values
import { IndicatorsSectionComponent } from './components/indicators-section';
import { IndicatorSectionComponent } from './components/indicator-section.component';
import { TemperatureIndicatorComponent } from './components/temperature-indicator';
import { HumidityIndicatorComponent } from './components/humidity-indicator';

// Charts
import { ChartModule } from 'angular2-highcharts';
import { HighchartsStatic } from 'angular2-highcharts/dist/HighchartsService';
import * as highcharts from 'highcharts';
import { ChartsSectionComponent } from './components/charts-section';
import { ChartSectionComponent } from './components/chart-section.component';
import { TemperatureChartComponent } from './components/temperature-chart';
import { HumidityChartComponent } from './components/humidity-chart';

import { HttpModule } from '@angular/http';

// Services
import { HumidityService } from './services/humidity.service';
import { TemperatureService } from './services/temperature.service';


@NgModule({
  declarations: [
    AppComponent,
    IndicatorsSectionComponent,
    IndicatorSectionComponent,
    TemperatureIndicatorComponent,
    HumidityIndicatorComponent,
    ChartsSectionComponent,
    ChartSectionComponent,
    TemperatureChartComponent,
    HumidityChartComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    ChartModule
  ],
  providers: [HumidityService, 
    TemperatureService,
    {
    provide: HighchartsStatic,
    useValue: highcharts
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
