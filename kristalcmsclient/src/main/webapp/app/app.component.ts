import { Component } from "angular2/core";

interface Navigationbar {
    title: string;
    activeItem: number;
}

interface NavigationbarItem {
    title: string;
    disabled: boolean;
}

@Component({
    selector: 'my-app',
    template: `
    <nav class="navbar navbar-fixed-top navbar-light bg-faded">
        <h1 class="navbar-brand" href="#">{{navigationbar.title}}</h1>
        <ul class="nav navbar-nav">
            <li *ngFor="#item of items" class="nav-item">
                <a class="nav-link" [ngClass]="{disabled: item.disabled}" href="#">{{item.title}}</a>
            </li>
        </ul>
    </nav>
        `
})
export class AppComponent {
    public navigationbar: Navigationbar = {
        title: 'prima',
        activeItem: 0
    };

    public items: NavigationbarItem[] = [{title: 'templates', disabled: false}, {title: 'pages', disabled: true}];

}