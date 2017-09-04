import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { ChartsSectionComponent } from './components/charts-section';
import { TemperatureChartComponent } from './components/temperature-chart';
import { HumidityChartComponent } from './components/humidity-chart';
import { ChartSectionComponent } from './components/chart-section.component';

import { TabsModule, CollapseModule, BsDropdownModule } from 'ngx-bootstrap';
import { ChartsModule } from 'ng2-charts';

import { AppComponent } from './app.component';

@NgModule({
  bootstrap: [AppComponent],
  declarations: [
    ChartSectionComponent,
    AppComponent,
    ChartsSectionComponent,
    TemperatureChartComponent,
    HumidityChartComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ChartsModule,
    TabsModule.forRoot(),
    CollapseModule.forRoot(),
    BsDropdownModule.forRoot()
  ]
})
export class AppModule {
}
