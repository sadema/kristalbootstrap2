import { Component } from 'angular2/core';
import { NavigationItem } from './navigation_item';
import { NavigationBarItem } from './navigationbar_item.component'

@Component({
    selector: 'nav',
    host: {'class': 'navbar navbar-fixed-top navbar-light bg-faded'},
    template: `
            <a class="navbar-brand" href="#">{{title}}</a>
            <ul class="nav navbar-nav">
                <li
                    *ngFor = '#item of itemList'
                    [item] = 'item'
                    [class.active] = 'isActiveItem(item)'
                    (selectItemEvent) = 'onItemSelected($event)'
                    >
                </li>
            </ul>
    `,
    inputs: ['itemList'],
    directives: [NavigationBarItem]
})
export class NavigationBar {
    title: string;
    activeItem: NavigationItem;
    itemList: NavigationItem[];

    constructor() {
        this.title = 'prima';
        this.activeItem = null;
    }

    isActiveItem(item: NavigationItem): boolean {
        return item === this.activeItem;
    }

    onItemSelected(item: NavigationItem): void {
        console.log("method onItemSelected");
        if (!item.disabled) {
            this.activeItem = item;
        }
    }

}