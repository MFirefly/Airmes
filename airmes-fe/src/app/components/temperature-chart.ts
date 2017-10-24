import { Component, OnInit } from '@angular/core';
import { TemperatureService } from '../services/temperature.service'
import { Measurement } from '../interfaces/Measurement';

@Component({
    selector: 'temperature-chart',
    templateUrl: './temperature-chart.html'
})
export class TemperatureChartComponent implements OnInit {

    temperatureHistory: Measurement[];
    options: Object;

    constructor(private temperatureService: TemperatureService) { }

    ngOnInit(): void {
        this.temperatureService.getTemperatureHistory().then(result => {
            this.temperatureHistory = result;
            this.showData();
        });
    }

    private showData() {
        var timestamps = this.getTimestamps();
        var temperatures = this.getTemperatures();
        this.options = {
            colors: ['#f45b5b'],
            chart: {
                backgroundColor: {
                    linearGradient: { x1: 0, y1: 0, x2: 1, y2: 1 },
                    stops: [
                        [0, '#c7ad88'],
                        [1, '#ede9ce']
                    ]
                },
                style: {
                    fontFamily: '\'Unica One\', sans-serif'
                },
                plotBorderColor: '#606063',
                zoomType: 'x'
            },
            title: {
                text: 'Temperature - Living room',
                style: {
                    color: '#935347',
                    fontSize: '20px'
                }
            },
            yAxis: {
                title: {
                    text: '[°C]',
                    style: {
                        color: '#935347'
                    }
                },
                gridLineColor: '#707073',
                labels: {
                    style: {
                        color: '#935347'
                    }
                },
                lineColor: '#707073',
                minorGridLineColor: '#505053',
                tickColor: '#707073',
                tickWidth: 1
            },
            xAxis: {
                categories: timestamps,
                crosshair: false,
                type: 'datetime',
                labels: {
                    format: '{value:%H:%M}',
                    style: {
                        color: '#935347'
                    }
                },
                gridLineColor: '#707073',
                lineColor: '#707073',
                minorGridLineColor: '#505053',
                tickColor: '#707073',
                title: {
                    text: 'Time',
                    style: {
                        color: '#935347'
                    }
                }
            },
            series: [{
                name: 'Temperature',
                data: temperatures
            }],
            plotOptions: {
                series: {
                    dataLabels: {
                        color: '#B0B0B3'
                    },
                    marker: {
                        lineColor: '#333'
                    }
                },
                boxplot: {
                    fillColor: '#505053'
                },
                candlestick: {
                    lineColor: 'white'
                },
                errorbar: {
                    color: 'white'
                }
            },
            credits: {
                enabled: false
            }
        };
    }

    private getTimestamps(): string[] {
        let tempTimestampData = new Array(this.temperatureHistory.length);
        for (let i = 0; i < this.temperatureHistory.length; i++) {
            tempTimestampData[i] = this.temperatureHistory[i].time;
        }
        return tempTimestampData;
    }

    private getTemperatures(): number[] {
        let tempTemperatureData = new Array(this.temperatureHistory.length);
        for (let i = 0; i < this.temperatureHistory.length; i++) {
            tempTemperatureData[i] = this.temperatureHistory[i].temperature;
        }
        return tempTemperatureData;
    }
}