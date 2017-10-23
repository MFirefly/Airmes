import { Component, OnInit } from '@angular/core';
import { TemperatureService } from '../services/temperature.service'
import { Measurement } from '../interfaces/Measurement';

@Component({
    selector: 'temperature-indicator',
    templateUrl: 'temperature-indicator.html'
})

export class TemperatureIndicatorComponent implements OnInit {

    public temperatureResult: Measurement;
    
        constructor(private temperatureService: TemperatureService) { }
    
        ngOnInit(): void {
            this.temperatureService.getTemperature().then(result => this.temperatureResult = result);
        }

}