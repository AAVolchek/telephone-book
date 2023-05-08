import { Component, OnInit, ElementRef, ViewChild  } from '@angular/core';
import {ContactService} from "../service/contact-service";
import {ActivatedRoute, Router} from "@angular/router";
import {FileSaverService} from "../service/file-saver.service";


@Component({
  selector: 'app-import-export-contacts',
  templateUrl: './import-export-contacts.component.html',
  styleUrls: ['./import-export-contacts.component.css']
})
export class ImportExportContactsComponent implements OnInit {
  @ViewChild('fileInput') fileInput!: ElementRef;
  errorMessage: string;

  constructor(private contactService: ContactService,
              private route: ActivatedRoute,
              private router: Router,
              private fileSaverService: FileSaverService) { }

  ngOnInit(): void {
  }
  onImport(event) {
    event.preventDefault();
    const file = (this.fileInput.nativeElement as HTMLInputElement).files?.[0];
    if (file) {
      const formData = new FormData();
      formData.append('file', file);
      this.contactService.importContacts(formData).subscribe({
        next: () => {
          this.gotoContactList();
        },
        error: (errorMessage) => {
          this.errorMessage = errorMessage;
        }
      });
    }
  }

  onExport() {
    this.contactService.exportContacts().subscribe({
      next: (blob: Blob) => {
        this.fileSaverService.saveAs(blob, 'contacts.csv');
      },
      error: (errorMessage) => {
        this.errorMessage = errorMessage;
      }
    });
  }

  gotoContactList() {
    this.router.navigate(['/api/v1/contacts']).then(r => null);
  }
}
