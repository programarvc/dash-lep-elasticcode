import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

import { Observable, of } from "rxjs";
import { catchError } from "rxjs/operators";
import { environment } from "src/environments/environment";

@Injectable({
    providedIn: "root",
})

export class UserService {
  getUserById() {
    throw new Error('Method not implemented.');
  }
    private handleError<T>(operation = "operation", result?: T) {
        return (error: any): Observable<T> => {
            console.error("Erro => " + JSON.stringify(error));
            console.error(`${operation} failed: ${error.message}`);
            return of(result as T);
        };
    }

  constructor(private http: HttpClient) {}

  getUsuarios(): Observable<any> {
    const url: string = `${environment.api}/user`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>("getUsuarios")));
  }

  getUserEsteiraById(id: number): Observable<any> {
    const url: string = `${environment.api}/user/esteira/${id}`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>('getUserEsteiraById')));
  }

  getEsteiraIdAndUsername(): Observable<any> {
    const url: string = `${environment.api}/user/useresteira`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>('getEsteiraIdAndUsername')));
  }

  getEsteirasPorUsuarioId(id: number): Observable<any> {
    const url: string = `${environment.api}/user/${id}/esteiras`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>('getEsteirasPorUsuarioId')));
  }

  getUsuarioIdPorUsername(username: string): Observable<any> {
    const url: string = `${environment.api}/user/${username}/id`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>('getUsuarioIdPorUsername')));
  }

}
