import {Component} from '@angular/core';
import {AuthenticationService} from "../service/authentication.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  title: string;

  constructor(private auth: AuthenticationService) {
    this.title = 'Contacts';
    this.auth = auth;
  }

  logout() {
    this.auth.logout();
  }
}
