import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {Contact} from "../model/contact";

@Injectable()
export class ContactService {

  private contactUrl: string;

  constructor(private http: HttpClient) {
    this.contactUrl = 'http://localhost:8080/api/v1/contacts';
  }

  public findAll(): Observable<Contact[]> {
    return this.http.get<Contact[]>(this.contactUrl).pipe(
      catchError(error => {
        console.error(error);
        return throwError(error);
      })
    );
  }

  public save(contact : Contact): Observable<Contact>  {
    return this.http.post<Contact>(this.contactUrl, contact).pipe(
      catchError(error => {
        console.error(error);
        return throwError(error);
      })
    );
  }

  public delete (id : string): Observable<Contact> {
    return this.http.delete<Contact>(`${this.contactUrl}/${id}`).pipe(
    catchError(error => {
      console.error(error);
      return throwError(error);
    })
    );
  }

  public editContact(id: string, value: any): Observable<Contact>{
    return this.http.put<Contact>(`${this.contactUrl}/${id}`, value).pipe(
      catchError(error => {
        console.error(error);
        return throwError(error);
      })
    );
  }

  getById(id: string): Observable<Contact> {
    return this.http.get<Contact>(`${this.contactUrl}/${id}`).pipe(
      catchError(error => {
        console.error(error);
        return throwError(error);
      })
    );
  }
}
