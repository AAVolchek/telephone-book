import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {catchError, map, Observable, throwError} from "rxjs";
import {Contact} from "../model/contact";
import {AuthenticationService} from "./authentication.service";

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  private contactUrl: string;

  constructor(private http: HttpClient, private authService: AuthenticationService) {
    this.contactUrl = 'http://localhost:8080/api/v1/contacts';
  }

  public findAll(): Observable<Contact[]> {

    return this.http.get<Contact[]>(this.contactUrl, this.authService.getAuthHeader())
      .pipe(
        catchError(error => {
          console.error(error);
          return throwError(error);
        }));
  }

  public save(contact: Contact): Observable<Contact> {

    return this.http.post<Contact>(this.contactUrl, contact, this.authService.getAuthHeader())
      .pipe(
        catchError(error => {
          console.error(error);
          return throwError(error);
        })
      );
  }

  public delete(id: string): Observable<Contact> {
    return this.http.delete<Contact>(`${this.contactUrl}/${id}`, this.authService.getAuthHeader())
      .pipe(
        catchError(error => {
          console.error(error);
          return throwError(error);
        })
      );
  }

  public editContact(id: string, value: any): Observable<Contact> {
    return this.http.put<Contact>(`${this.contactUrl}/${id}`, value, this.authService.getAuthHeader())
      .pipe(
        catchError(error => {
          console.error(error);
          return throwError(error);
        })
      );
  }

  public getById(id: string): Observable<Contact> {
    return this.http.get<Contact>(`${this.contactUrl}/${id}`, this.authService.getAuthHeader())
      .pipe(
        catchError(error => {
          console.error(error);
          return throwError(error);
        })
      );
  }

  public exportContacts(){
   return  this.http.get(`${this.contactUrl}/export`, {
      ...this.authService.getAuthHeader(),
      responseType: 'blob'
    }).pipe(
        map(response => new Blob([response], { type: 'text/csv' })),
        catchError(error => {
          console.error(error);
          return throwError(()  =>'Failed to export contacts. Please try again later.');
        })
      );
  }

  public importContacts(formData: FormData){

    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('access_token')
      })
    };

    return this.http.post(`${this.contactUrl}/import`, formData, httpOptions
    ).pipe(
      catchError((error: HttpErrorResponse) => {
        let errorMessage = 'Error importing contacts';
        if (error.error instanceof ErrorEvent) {
          // Client-side error
          errorMessage = `An error occurred: ${error.error.message}`;
        } else {
          // Server-side error
          errorMessage = `Server returned code ${error.status}: ${error.error.message}`;
        }
        return throwError(() => new Error(errorMessage));
      })
    );
  }
}
