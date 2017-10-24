import { Component, OnInit } from '@angular/core';
import { HumidityService } from '../services/humidity.service'
import { Measurement } from '../interfaces/Measurement';

@Component({
    selector: 'humidity-chart',
    templateUrl: './humidity-chart.html'
})

export class HumidityChartComponent implements OnInit {

    humidityHistory: Measurement[];
    options: Object;

    constructor(private humidityService: HumidityService) { }

    ngOnInit(): void {
        this.humidityService.getHumidityHistory().then(result => {
            this.humidityHistory = result;
            this.showData();
        });
    }

    private showData() {
        var timestamps = this.getTimestamps();
        var humidities = this.getHumidities();
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
                text: 'Humidity - Living room',
                style: {
                    color: '#935347',
                    fontSize: '20px'
                }
            },
            yAxis: {
                title: {
                    text: '[%]',
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
                name: 'Humidity',
                data: humidities
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
        let tempTimestampData = new Array(this.humidityHistory.length);
        for (let i = 0; i < this.humidityHistory.length; i++) {
            tempTimestampData[i] = this.humidityHistory[i].time;
        }
        return tempTimestampData;
    }

    private getHumidities(): number[] {
        let tempHumidityData = new Array(this.humidityHistory.length);
        for (let i = 0; i < this.humidityHistory.length; i++) {
            tempHumidityData[i] = this.humidityHistory[i].humidity;
        }
        return tempHumidityData;
    }
}