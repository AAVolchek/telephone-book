import {Component} from '@angular/core';
import {Contact} from "../model/contact";
import {ContactService} from "../service/contact-service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-contact-form',
  templateUrl: './contact-form.component.html',
  styleUrls: ['./contact-form.component.css']
})
export class ContactFormComponent {

  contact: Contact;
  firstName: string;
  lastName: string;
  phoneNumber: string;
  email: string;
  birthday: Date;
  socialProfile: string;
  group: string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private contactService: ContactService
  ) {
  }

  onSubmit(firstName: string, lastName: string, phoneNumber: string, email: string, birthday: Date, socialProfile: string, group: string) {

    this.contact = new Contact(firstName, lastName, phoneNumber, email, birthday, socialProfile, group);
    this.contactService.save(this.contact).subscribe(result => this.gotoContactList());
  }

  gotoContactList() {
    this.router.navigate(['/api/v1/contacts']).then(r => null);
  }
}
