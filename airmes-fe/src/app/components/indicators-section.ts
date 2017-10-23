import { Component } from '@angular/core';

@Component({
    selector: 'indicators-section',
    templateUrl: './indicators-section.html'
})

export class IndicatorsSectionComponent {
    public desc = {
        temperatureIndicator: {
            heading: 'Current Temperature',
        },
        humidityIndicator: {
            heading: 'Current Humidity',
        }
    }
}