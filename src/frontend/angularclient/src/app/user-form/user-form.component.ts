import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../service/user.service";
import {User} from "../model/user";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit {

  user: User;
  id: string;
  userForm: FormGroup;
  errorMessage: string;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService
  ) {
    this.userForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]]
    });
  }

  ngOnInit(): void {
    this.userService.getAuthUser().subscribe(res => {
      this.id = res.id;
      this.user = res;
      this.userForm.patchValue({
        name: res.name,
        email: res.email,
        password: res.password
      });
    });
  }

  onSubmit(): void {
    if (this.userForm.valid) {
      this.user = this.userForm.value;
      this.userService.editUser(this.id, this.user)
        .subscribe({
        next: (_data : User) => {
          console.log('Update successful');
          this.gotoContactList();
        },
        error: (error : any) => {
          console.log('Update failed:', error);
          this.errorMessage = 'Invalid email or password';
        }
      });
    }
  }

  gotoContactList(): void {
    this.router.navigate(['/api/v1/contacts']).then(_r => null);
  }

  cancel() {
    this.gotoContactList();
  }
}
