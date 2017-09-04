import { Component } from '@angular/core';

// webpack html imports

@Component({
  selector: 'charts-section',
  template: `
    <section style="padding-top: 0px;" [attr.id]="name">
      <div class="row">
        <chart-section [ts]="desc.temperatureChart.ts"
                       [html]="desc.temperatureChart.html"
                      
                       [id]="desc.temperatureChart.id"
                       [heading]="desc.temperatureChart.heading">
          <temperature-chart></temperature-chart>
        </chart-section>
      </div>
      <div class="row">
      <chart-section [ts]="desc.humidityChart.ts"
                     [html]="desc.humidityChart.html"
                    
                     [id]="desc.humidityChart.id"
                     [heading]="desc.humidityChart.heading">
        <humidity-chart></humidity-chart>
      </chart-section>
    </div>
    </section>
  `
})

export class ChartsSectionComponent {
  public name: string = 'Charts';
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
