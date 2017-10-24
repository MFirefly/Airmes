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
            title: { text: '' },
            xAxis: {
                categories: timestamps,
                crosshair: false,
                type: 'datetime',
                labels: {
                    format: '{value:%H:%M}'
                }
            },
            series: [{
                data: temperatures
            }]
        };
    }

    private getTimestamps(): string[] {
        let tempTimestampData = new Array(this.temperatureHistory.length);
        for(let i = 0; i < this.temperatureHistory.length; i++) {
            tempTimestampData[i] = this.temperatureHistory[i].time;
        }
        return tempTimestampData;
    }

    private getTemperatures(): number[] {
        let tempTemperatureData = new Array(this.temperatureHistory.length);
        for(let i = 0; i < this.temperatureHistory.length; i++) {
            tempTemperatureData[i] = this.temperatureHistory[i].temperature;
        }
        return tempTemperatureData;
    }
}