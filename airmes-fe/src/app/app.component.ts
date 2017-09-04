import { Component } from '@angular/core';

@Component({
  selector: 'app',
  template: `
  <br><br>
  <h1 class="text-center">Airmes</h1>
  <div class="container">
      <charts-section class="col-md-12"></charts-section>
  </div>

  <footer class="footer">
    <div class="container">
      <p class="text-muted text-center"><a href="https://github.com/MFirefly/Airmes">Airmes</a> is maintained by <a href="https://github.com/MFirefly">Maja Filakovic</a>.</p>
    </div>
  </footer>
  `
})
export class AppComponent {
  public ngAfterContentInit(): any {
    setTimeout(()=>{
      if (typeof PR !== 'undefined') {
        // google code-prettify
        PR.prettyPrint();
      }
    }, 150);
  }
}
