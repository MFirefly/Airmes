import { Component } from '@angular/core';

@Component({
    selector: 'indicators-section',
    template: `
    <div class="row">
        <div class="col-lg-2">
            <div class="card mb-3">
                <indicator-section [heading]="desc.temperatureIndicator.heading"
                                   [ts]="desc.temperatureIndicator.ts"
                                   [html]="desc.temperatureIndicator.html"
                                   [id]="desc.temperatureIndicator.id">
                    <temperature-indicator></temperature-indicator>
                </indicator-section>
            </div>
        </div>
        <div class="col-lg-4"></div>
        <div class="col-lg-2">
            <div class="card mb-3">
                <indicator-section [heading]="desc.humidityIndicator.heading"
                                   [ts]="desc.humidityIndicator.ts"
                                   [html]="desc.humidityIndicator.html"
                                   [id]="desc.humidityIndicator.id">
                    <humidity-indicator></humidity-indicator>
                </indicator-section>
            </div>
        </div>
    </div>
    `
})

export class IndicatorsSectionComponent {
    public desc: any = {
        temperatureIndicator: {
            heading: 'Current Temperature',
            id: 'currentTemperature',
            ts: require('!!raw-loader?lang=typescript!./temperature-indicator.ts'),
            html: require('!!raw-loader?lang=markup!./temperature-indicator.html')
        },
        humidityIndicator: {
            heading: 'Current Humidity',
            id: 'currentHumidity',
            ts: require('!!raw-loader?lang=typescript!./humidity-indicator.ts'),
            html: require('!!raw-loader?lang=markup!./humidity-indicator.html')
        }
    };
}
