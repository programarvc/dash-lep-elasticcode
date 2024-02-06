import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

import { Observable, of } from "rxjs";
import { catchError } from "rxjs/operators";
import { environment } from "src/environments/environment";

@Injectable({
    providedIn: "root",
})

export class TimeService {
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

  getTimes(): Observable<any> {
    const url: string = `${environment.api}/time`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>("getTimes")));
  }

  getTimeById(timeId: number): Observable<any> {
    const url: string = `${environment.api}/time/${timeId}`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>("getTimeById")));
  }

  getColaboradoresByEsteiraId(esteiraId: number): Observable<any> {
    const url: string = `${environment.api}/time/esteira/${esteiraId}`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>("getColaboradoresByTimeId")));
  }

  getTimesByEsteiraId(esteiraId: number): Observable<any> {
    const url: string = `${environment.api}/time/esteira/${esteiraId}/times`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>("getTimesByEsteiraId")));

  }

  getTimeAndColaboradorByEsteiraId(esteiraId: number): Observable<any> {
    const url: string = `${environment.api}/time/colaborador/esteira/${esteiraId}`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>("getTimeAndColaboradorByEsteiraId")));
  }

  getColaboradoresByTimeId(timeId: number){
    const url: string = `${environment.api}/time/${timeId}/colaboradores`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>("getColaboradoresByTimeId")));
  }

  getTimesAndEsteiraByColaboradorId(colaboradorId: number){
    const url: string = `${environment.api}/time/colaboradorId/${colaboradorId}`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>("getTimesAndEsteiraByColaboradorId")));
  }


}
