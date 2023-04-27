import {Component, OnInit} from '@angular/core';
import {Contact} from "../model/contact";
import {ContactService} from "../service/contact-service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-contact-list',
  templateUrl: './contact-list.component.html',
  styleUrls: ['./contact-list.component.css']
})
export class ContactListComponent implements OnInit {
  contacts: Contact [] = [];
  filterTerm: string;

  constructor(private contactService: ContactService,
              private router: Router,
  ) {}

  ngOnInit(): void {
    console.log('ngOnInit start contact list');

    this.contactService.findAll().subscribe( data => {
      this.contacts = data;
    });
  }

  deleteContact(id: string) {
     this.contactService.delete(id).subscribe(() => {
       this.ngOnInit();
     });
  }

  editContact(id: string) {
    this.router.navigate(['edit', id]);
  }
}
