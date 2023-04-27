import { Component } from '@angular/core';
import {AuthenticationService} from "./service/authentication.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(private authService: AuthenticationService
  ) { }

  isLoggedIn() {
    return this.authService.isLoggedIn();
  }

  isShowLoginForm(){
    if(localStorage.getItem("showLogin") == "false"){
      return false;
    }
    return true;
  }

  isShowRegisterForm(){
    if(localStorage.getItem("showRegister")  == "true"){
      return true;
    }
    return false;
  }
}
