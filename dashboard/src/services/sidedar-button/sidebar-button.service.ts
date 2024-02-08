import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})

export class SidebarButtonService {
    private selectedButton: string = '';

    setSelectedButton(button: string) {
        this.selectedButton = button;
    }

    getSelectedButton(): string {
        return this.selectedButton;
    }
}