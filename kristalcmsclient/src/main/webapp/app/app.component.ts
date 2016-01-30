import { Component } from "angular2/core";

interface Navigationbar {
    title: string;
}

@Component({
    selector: 'my-app',
    template: `
    <nav class="navbar navbar-light bg-faded">
        <a class="navbar-brand" href="#">{{navigationbar.title}}</a>
  <ul class="nav navbar-nav">
    <li class="nav-item active">
      <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">Features</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">Pricing</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">About</a>
    </li>
  </ul>
</nav>    `
})
export class AppComponent {
    public navigationbar: Navigationbar = {
        title: 'prima'
    };
}