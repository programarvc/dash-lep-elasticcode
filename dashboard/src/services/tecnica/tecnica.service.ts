import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

import { Observable, of } from "rxjs";
import { catchError } from "rxjs/operators";
import { environment } from "src/environments/environment";

@Injectable({
    providedIn: "root",
})

export class TecnicaService {
    private handleError<T>(operation = "operation", result?: T) {
        return (error: any): Observable<T> => {
            console.error("Erro => " + JSON.stringify(error));
            console.error(`${operation} failed: ${error.message}`);
            return of(result as T);
        };
    }

  constructor(private http: HttpClient) {}

  getIndicesDeMaturidade(): Observable<any> {
    const url: string = `${environment.api}/indicesdematuridade`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>("getIndicesDeMaturidade")));
  }

  getIndicesDeMaturidadeByTipos(id: number): Observable<any> {
    const url: string = `${environment.api}/indicesdematuridade/tipo/TECNICA`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>('getMaturidadeById')));
  }
/* 
  getTecnicaByEsteiraId(id: number): Observable<any> {
    const url: string = `${environment.api}/tecnica/${id}`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>('getTecnicaByEsteiraId')));
  }
*/

}
