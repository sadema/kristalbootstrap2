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
        <a class="navbar-brand" href="#">{{navigationbar.title}}</a>
        <ul class="nav navbar-nav">
            <li *ngFor="#item of items; #num = index" class="nav-item">
                <a class="nav-link"
                    [class.disabled] = "isDisabled(num)"
                    [class.active] = "isActive(num)"
                    data-index = num
                    (click) = "onClick(num)"
                    href="#">{{item.title}}</a>
            </li>
        </ul>
    </nav>
    <div>
        <h1>Active menu item: {{navigationbar.activeItem}}</h1>
    </div>
        `
})
export class AppComponent {
    public navigationbar: Navigationbar = {
        title: 'prima',
        activeItem: 0
    };

    public items: NavigationbarItem[] = [
        {title: 'templates', disabled: false},
        {title: 'pages', disabled: true},
        {title: 'content', disabled: false}
    ];

    public isActive(num: number): boolean {
        return num === this.navigationbar.activeItem;
    }

    public isDisabled(num: number): boolean {
        return this.items[num].disabled;
    }

    public onClick(num: number): void {
        if (!this.isDisabled(num)) {
            this.navigationbar.activeItem = num;
        }
    }
}