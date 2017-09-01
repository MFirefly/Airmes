import { Component } from '@angular/core';

// webpack html imports

@Component({
  selector: 'charts-section',
  template: `
    <section [attr.id]="name">
      <div class="row">
        <chart-section [ts]="desc.lineChart.ts"
                       [html]="desc.lineChart.html"
                      
                       [id]="desc.lineChart.id"
                       [heading]="desc.lineChart.heading">
          <line-chart></line-chart>
        </chart-section>
      </div>

      <br>

    </section>
  `
})

export class ChartsSectionComponent {
  public name: string = 'Charts';
  public desc: any = {
    lineChart: {
      heading: 'Line Chart',
      id: 'lineChart',
      ts: require('!!raw-loader?lang=typescript!./line-chart.ts'),
      html: require('!!raw-loader?lang=markup!./line-chart.html')
    }
  };
}
