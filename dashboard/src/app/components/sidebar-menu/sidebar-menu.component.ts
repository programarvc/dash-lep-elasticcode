import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sidebar-menu',
  templateUrl: './sidebar-menu.component.html',
  styleUrls: ['./sidebar-menu.component.sass']
})
export class SidebarMenuComponent {

  constructor(private router: Router) { }

  abrirComponente(componente: string) {
    switch (componente) {
      case 'app-dash-projeto':
        this.router.navigate(['/app-dash-projeto']);
        break;
      case 'app-colaborador':
        this.router.navigate(['/app-colaborador']);
        break;
      default:
        break;
    }
  }
}
