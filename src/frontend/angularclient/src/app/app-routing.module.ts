import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ContactListComponent} from "./contact-list/contact-list.component";
import {ContactFormComponent} from "./contact-form/contact-form.component";
import {ContactEditComponent} from "./contact-edit/contact-edit.component";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {UserFormComponent} from "./user-form/user-form.component";

const routes: Routes = [
  {path: 'api/v1/contacts', component: ContactListComponent},
  {
    path: 'addContact', component: ContactFormComponent
  },
  {path: 'edit/:id', component: ContactEditComponent},
  {
    path: 'api/v1/login', component: LoginComponent
  },
  {
    path: 'api/v1/register', component: RegisterComponent
  },
  {
    path: 'api/v1/user', component: UserFormComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
