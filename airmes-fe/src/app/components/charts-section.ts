import { Component } from '@angular/core';

@Component({
    selector: 'charts-section',
    templateUrl: 'charts-section.html'
  })
  
  export class ChartsSectionComponent {
    public desc: any = {
      temperatureChart: {
        heading: 'Temperature Chart'
      },
      humidityChart: {
        heading: 'Humidity Chart'
      }
    };
  }