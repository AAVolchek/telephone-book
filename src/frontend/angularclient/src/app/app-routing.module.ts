import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ContactListComponent} from "./contact-list/contact-list.component";
import {ContactFormComponent} from "./contact-form/contact-form.component";
import {ContactEditComponent} from "./contact-edit/contact-edit.component";

const routes: Routes = [
  { path: 'api/v1/contacts', component: ContactListComponent},
  {
    path: 'addContact', component: ContactFormComponent
  },
  { path: 'edit/:id', component: ContactEditComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
