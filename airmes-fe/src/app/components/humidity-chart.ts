import { Component } from '@angular/core';

@Component({
    selector: 'humidity-chart',
    templateUrl: './humidity-chart.html'
})

export class HumidityChartComponent {
    public humidChartData: any[] = [
        { data: [18, 48, 77, 9, 100, 27, 40], label: 'Humidity' }
    ];
    public humidChartLabels: any[] = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];
    public humidChartOptions: any = {
        responsive: true
    };
    public humidChartColors: any[] = [
        {
            backgroundColor: 'rgba(148,159,177,0.2)',
            borderColor: 'rgba(148,159,177,1)',
            pointBackgroundColor: 'rgba(148,159,177,1)',
            pointBorderColor: '#fff',
            pointHoverBackgroundColor: '#fff',
            pointHoverBorderColor: 'rgba(148,159,177,0.8)'
        }
    ];
    public humidChartLegend: boolean = true;
    public humidChartType: string = 'line';

    public randomize(): void {
        let _humidChartData: any[] = new Array(this.humidChartData.length);
        for (let i = 0; i < this.humidChartData.length; i++) {
            _humidChartData[i] = { data: new Array(this.humidChartData[i].data.length), label: this.humidChartData[i].label };
            for (let j = 0; j < this.humidChartData[i].data.length; j++) {
                _humidChartData[i].data[j] = Math.floor((Math.random() * 100) + 1);
            }
        }
        this.humidChartData = _humidChartData;
    }

    // Events
    public chartClicked(e: any): void {
        console.log(e);
    }

    public chartHovered(e: any): void {
        console.log(e);
    }
}