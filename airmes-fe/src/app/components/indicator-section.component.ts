import { Component, Input } from '@angular/core';

@Component({
    selector: 'indicator-section',
    templateUrl: './indicator-section.component.html'
})

export class IndicatorSectionComponent {

    @Input()
    public heading: string;
}