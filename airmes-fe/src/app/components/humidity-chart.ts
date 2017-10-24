import { Component, OnInit } from '@angular/core';
import { HumidityService } from '../services/humidity.service'
import { Measurement } from '../interfaces/Measurement';

@Component({
    selector: 'humidity-chart',
    templateUrl: './humidity-chart.html'
})

export class HumidityChartComponent implements OnInit{

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
            title: { text: '' },
            yAxis: {
                title: {
                    text: '[%]'
                }
            },
            xAxis: {
                categories: timestamps,
                crosshair: false,
                type: 'datetime',
                labels: {
                    format: '{value:%H:%M}'
                }
            },
            series: [{
                name: 'Humidity',
                data: humidities
            }]
        };
    }

    private getTimestamps(): string[] {
        let tempTimestampData = new Array(this.humidityHistory.length);
        for(let i = 0; i < this.humidityHistory.length; i++) {
            tempTimestampData[i] = this.humidityHistory[i].time;
        }
        return tempTimestampData;
    }

    private getHumidities(): number[] {
        let tempHumidityData = new Array(this.humidityHistory.length);
        for(let i = 0; i < this.humidityHistory.length; i++) {
            tempHumidityData[i] = this.humidityHistory[i].humidity;
        }
        return tempHumidityData;
    }
}