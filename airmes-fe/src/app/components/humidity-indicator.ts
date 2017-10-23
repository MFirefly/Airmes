import { Component, OnInit, Input } from '@angular/core';
import { HumidityService } from '../services/humidity.service'
import { Measurement } from '../interfaces/Measurement';

@Component({
    selector: 'humidity-indicator',
    templateUrl: 'humidity-indicator.html'
})

export class HumidityIndicatorComponent implements OnInit {

    public humidityResult: Measurement;

    constructor(private humidityService: HumidityService) { }

    ngOnInit(): void {
        this.humidityService.getHumidity().then(result => this.humidityResult = result);
    }
}

