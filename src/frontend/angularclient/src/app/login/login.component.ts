import {Component} from '@angular/core';
import {AuthenticationService} from "../service/authentication.service";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthenticationResponse} from "../model/authentication-response";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  email: string;
  password: string;
  errorMessage: string;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private authService: AuthenticationService) {
  }

  login() {
    this.authService.authenticate(this.email, this.password)
      .subscribe({
        next: (data: AuthenticationResponse) => {
          console.log('Authentication successful');
          localStorage.setItem('access_token', data.access_token);
          this.gotoContactList();
        },
        error: (error: any) => {
          console.log('Authentication failed:', error);
          this.errorMessage = 'Invalid email or password';
        }
      });
  }

  gotoContactList() {
    this.router.navigate(['/api/v1/contacts']).then(_r => null);
  }

  register() {
    this.authService.showRegistrationForm();
  }
}
