import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {Contact} from "../model/contact";
import {ContactService} from "../service/contact-service";
import {GroupContactModule} from "../model/group-contact.module";

@Component({
  selector: 'app-contact-edit',
  templateUrl: './contact-edit.component.html',
  styleUrls: ['./contact-edit.component.css']
})
export class ContactEditComponent implements OnInit {

  contact: Contact;
  id: string;
  firstName: string;
  lastName: string;
  phoneNumber: string;
  email: string;
  birthday: Date;
  socialProfile: string;
  editFrom: FormGroup;
  group: string;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private contactService: ContactService) {

    this.editFrom = this.fb.group({
      firstName: new FormControl(''),
      lastName: new FormControl(''),
      phoneNumber: new FormControl(''),
      email: new FormControl(''),
      birthday: new FormControl(''),
      socialProfile: new FormControl(''),
      group: new FormControl('')
    });

  }

  ngOnInit(): void {
    this.contact = new Contact(this.firstName, this.lastName, this.phoneNumber, this.email, this.birthday, this.socialProfile, this.group);
    this.id = this.route.snapshot.params['id'];
    console.log(this.id);
    this.contactService.getById(this.id)
      .subscribe((res) => {
        this.editFrom.patchValue({
          firstName: res.firstName,
          lastName: res.lastName,
          phoneNumber: res.phoneNumber,
          email: res.email,
          birthday: res.birthday,
          socialProfile: res.socialProfile,
          group: res.group
        })
      });
  }

  onSubmit() {
    this.contactService.editContact(this.id, this.contact).subscribe(result => this.gotoContactList())
  }

  gotoContactList() {
    this.router.navigate(['/api/v1/contacts']).then(r => null);
  }

}
