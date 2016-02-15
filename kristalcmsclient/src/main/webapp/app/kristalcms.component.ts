import { Component } from "angular2/core";
import {NavigationBar} from "./navigationbar.component";
import {NavigationItem} from "./navigation_item";
import { RouteConfig, ROUTER_DIRECTIVES, ROUTER_PROVIDERS } from 'angular2/router';
import { TemplatesComponent } from './templates.component';

@RouteConfig([
    {
        path: 'kristalcmsclient/templates',
        name: 'Templates',
        component: TemplatesComponent
    }
])
@Component({
    selector: 'kristalcms-app',
    template: `
        <nav
            [itemList] = "items">
        </nav>
        <a [routerLink] = "['Templates']">Templates</a>
        <router-outlet></router-outlet>
    `,
    directives: [NavigationBar, ROUTER_DIRECTIVES],
    providers: [ROUTER_PROVIDERS]
})
export class KristalcmsComponent {
    items: NavigationItem[];

    constructor() {
        this.items = [
            new NavigationItem('templates', false),
            new NavigationItem('pages', true),
            new NavigationItem('content', false)
        ]
    }
}