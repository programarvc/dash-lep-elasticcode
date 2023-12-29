import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

import { Observable, of } from "rxjs";
import { catchError } from "rxjs/operators";
import { environment } from "src/environments/environment";

@Injectable({
    providedIn: "root",
})

export class JornadaService {
    getJornadasById() {
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

    getJornadas(): Observable<any> {
        const url: string = `${environment.api}/jornada`;
        return this.http
            .get<any>(url)
            .pipe(catchError(this.handleError<any>("getJornadas")));
    }

    getJornadaById(id: number): Observable<any> {
        const url: string = `${environment.api}/jornada/${id}`;
        return this.http
            .get<any>(url)
            .pipe(catchError(this.handleError<any>('getJornadaById')));
    }
}