import { Component } from "angular2/core";
import {NavigationBar} from "./navigationbar.component";
import {NavigationItem} from "./navigation_item";

@Component({
    selector: 'kristalcms-app',
    template: `
        <navigation-bar
            [itemList] = "items">
        </navigation-bar>
        <h1>Hello World</h1>
    `,
    directives: [NavigationBar]
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