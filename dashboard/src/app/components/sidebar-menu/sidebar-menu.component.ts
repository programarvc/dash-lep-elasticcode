import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CognitoService } from 'src/app/cognito.service';


@Component({
  selector: 'app-sidebar-menu',
  templateUrl: './sidebar-menu.component.html',
  styleUrls: ['./sidebar-menu.component.sass']
})
export class SidebarMenuComponent {

  constructor(private cognitoService: CognitoService) { }

  public signOut(){
    this.cognitoService.signOut()
      .then(() => {
        window.location.reload();
      })
  }
}
