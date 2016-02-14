import { Component } from 'angular2/core';
import { NavigationItem } from './navigation_item';
import { NavigationBarItem } from './navigationbar_item.component'

@Component({
    selector: 'nav',
    host: {'class': 'navbar navbar-fixed-top navbar-light bg-faded'},
    template: `
            <a class="navbar-brand" href="#">{{title}}</a>
            <ul class="nav navbar-nav">
                <navigationbar-item
                    *ngFor="#item of itemList">
                </navigationbar-item>
            </ul>
    `,
    inputs: ['itemList'],
    directives: [NavigationBarItem]
})
export class NavigationBar {
    title: string;
    activeItem: number;
    itemList: NavigationItem[];

    constructor() {
        this.title = 'prima';
        this.activeItem = 0;
    }
}