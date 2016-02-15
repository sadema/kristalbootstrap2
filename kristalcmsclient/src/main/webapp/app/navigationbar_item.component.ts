import { Component } from 'angular2/core';
import { EventEmitter } from 'angular2/core';
import {NavigationItem} from './navigation_item';

@Component({
    selector: 'li',
    host: {'class': 'nav-item'},
    template: `
        <a class="nav-link"
            href="#"
            [class.disabled] = 'isDisabled(item)'
            (click) = 'setActiveItem(item)'
            >
            {{item.title}}
        </a>
    `,
    inputs: ['item'],
    outputs: ['selectItemEvent']
})
export class NavigationBarItem {
    item: NavigationItem;
    selectItemEvent: EventEmitter<NavigationItem>;

    constructor() {
        this.selectItemEvent = new EventEmitter();
    }

    isDisabled(item: NavigationItem): boolean {
        return item.disabled;
    }

    setActiveItem(item: NavigationItem): void {
        this.selectItemEvent.emit(item);
    }

}