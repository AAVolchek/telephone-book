import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {AuthenticationResponse} from "../model/authentication-response";
import { tap } from 'rxjs/operators';
import {AuthenticationRequest} from "../model/authentication-request";
import {User} from "../model/user";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private authenticateUrl: string;
  private registerUrl: string;

  constructor(private http: HttpClient) {
    this.authenticateUrl = 'http://localhost:8080/api/v1/auth/authenticate';
    this.registerUrl = 'http://localhost:8080/api/v1/auth/register';
  }

  public authenticate(email: string, password: string): Observable<AuthenticationResponse> {
    const authRequest: AuthenticationRequest = { email, password };
    return this.http.post<AuthenticationResponse>(this.authenticateUrl, authRequest)
      .pipe(
        tap(data => console.log('Authentication response:', data)),
        catchError(error => {
          console.error(error);
          return throwError(error);
        })
      );
  }

  public register(user : User): Observable<User>{
    return this.http.post<User>(this.registerUrl,user)
      .pipe(
        tap(data => console.log('Registration response:', data)),
        catchError(error => {
          console.error(error);
          return throwError(error);
      })
    );
  }

  public logout() {
    localStorage.removeItem('access_token');
  }

  public isLoggedIn(): boolean {
    return localStorage.getItem('access_token') !== null;
  }

  public getAuthHeader(){

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.getItem('access_token')
      })
    };
    return httpOptions;
  }

  public showRegistrationForm() {
    localStorage.setItem("showLogin", "false");
    localStorage.setItem("showRegister", "true");
  }

  public showLoginForm() {
    localStorage.setItem("showLogin", "true");
    localStorage.setItem("showRegister", "false");
  }
}
