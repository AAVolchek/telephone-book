import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../service/authentication.service";
import {User} from "../model/user";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  name: string;
  email: string;
  password: string;
  errorMessage: string;

  constructor(private authService: AuthenticationService) {
  }

  ngOnInit(): void {
    console.log(" register form open ");
  }

  login() {
    this.showLoginForm();
  }

  register() {
    const user = new User(null, this.name, this.email, this.password);
    this.authService.register(user)
      .subscribe({
        next: (_data: User) => {
          console.log('Registration successful');
          this.authService.showLoginForm();
        },
        error: (error: any) => {
          console.log('Registration failed:', error);
          this.errorMessage = 'Invalid email or password';
        }
      });
  }

  cancel() {
    this.showLoginForm();
  }

  private showLoginForm() {
    this.authService.showLoginForm();
  }
}
