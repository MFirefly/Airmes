import { Component } from '@angular/core';

// webpack html imports

@Component({
  selector: 'charts-section',
  template: `
  <div class="row">
    <div class="col-lg-6">
      <div class="card mb-3">
        <chart-section [ts]="desc.temperatureChart.ts"
                       [html]="desc.temperatureChart.html"
                      
                       [id]="desc.temperatureChart.id"
                       [heading]="desc.temperatureChart.heading">
          <temperature-chart></temperature-chart>
        </chart-section>
      </div>
    </div>
    <div class="col-lg-6">
      <div class="card mb-3">
      <chart-section [ts]="desc.humidityChart.ts"
                     [html]="desc.humidityChart.html"
                    
                     [id]="desc.humidityChart.id"
                     [heading]="desc.humidityChart.heading">
        <humidity-chart></humidity-chart>
      </chart-section>
      </div>
    </div>
  </div>
  `
})

export class ChartsSectionComponent {
  public desc: any = {
    temperatureChart: {
      heading: 'Temperature Chart',
      id: 'temperatureChart',
      ts: require('!!raw-loader?lang=typescript!./temperature-chart.ts'),
      html: require('!!raw-loader?lang=markup!./temperature-chart.html')
    },
    humidityChart: {
      heading: 'Humidity Chart',
      id: 'humidityChart',
      ts: require('!!raw-loader?lang=typescript!./humidity-chart.ts'),
      html: require('!!raw-loader?lang=markup!./humidity-chart.html')
    }
  };
}
