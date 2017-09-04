import { Component } from '@angular/core';

@Component({
  selector: 'temperature-chart',
  templateUrl: './temperature-chart.html'
})
export class TemperatureChartComponent {
  public tempChartData: any[] = [
    { data: [65, 59, 80, 81, 56, 55, 40], label: 'Temperature' }
  ];
  public tempChartLabels: any[] = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];
  public tempChartOptions: any = {
    responsive: true
  };
  public tempChartColors: any[] = [
    {
      backgroundColor: 'rgba(77,83,96,0.2)',
      borderColor: 'rgba(77,83,96,1)',
      pointBackgroundColor: 'rgba(77,83,96,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(77,83,96,1)'
    }
  ];
  public tempChartLegend: boolean = true;
  public tempChartType: string = 'line';

  public randomize(): void {
    let _tempChartData: any[] = new Array(this.tempChartData.length);
    for (let i = 0; i < this.tempChartData.length; i++) {
      _tempChartData[i] = { data: new Array(this.tempChartData[i].data.length), label: this.tempChartData[i].label };
      for (let j = 0; j < this.tempChartData[i].data.length; j++) {
        _tempChartData[i].data[j] = Math.floor((Math.random() * 100) + 1);
      }
    }
    this.tempChartData = _tempChartData;
  }

  // events
  public chartClicked(e: any): void {
    console.log(e);
  }

  public chartHovered(e: any): void {
    console.log(e);
  }
}
