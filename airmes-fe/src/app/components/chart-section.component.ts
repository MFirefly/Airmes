import { Component, Input } from '@angular/core';

@Component({
  selector: 'chart-section',
  templateUrl: './chart-section.component.html'
})
export class ChartSectionComponent {
  @Input() public heading: string;
}