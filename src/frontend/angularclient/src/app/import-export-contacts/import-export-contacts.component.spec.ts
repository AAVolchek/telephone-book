import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ImportExportContactsComponent } from './import-export-contacts.component';

describe('ImportExportContactsComponent', () => {
  let component: ImportExportContactsComponent;
  let fixture: ComponentFixture<ImportExportContactsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ImportExportContactsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ImportExportContactsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
