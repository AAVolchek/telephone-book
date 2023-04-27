import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {User} from "../model/user";
import {AuthenticationService} from "./authentication.service";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userUrl: string;

  constructor(private http: HttpClient, private authService: AuthenticationService) {
    this.userUrl = 'http://localhost:8080/api/v1/users';
  }

  public editUser(id: string, value: any): Observable<User> {
    return this.http.put<User>(`${this.userUrl}/${id}`, value, this.authService.getAuthHeader())
      .pipe(catchError(error => {
        console.error(error);
        return throwError(error);
      }));
  }

  public getAuthUser(): Observable<User> {
    return this.http.get<User>(`${this.userUrl}/authUser`, this.authService.getAuthHeader()).pipe(
      catchError((error) => {
        console.error(error);
        return throwError(error);
      })
    );
  }
}
