import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

import { Observable, of } from "rxjs";
import { catchError } from "rxjs/operators";
import { environment } from "src/environments/environment";

@Injectable({
    providedIn: "root",
})

export class CapacidadeService {
    getCapacidadesById() {
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

    getCapacidades(): Observable<any> {
        const url: string = `${environment.api}/capacidades`;
        return this.http
            .get<any>(url)
            .pipe(catchError(this.handleError<any>("getCapacidades")));
    }

    getCapacidadeById(id: number): Observable<any> {
        const url: string = `${environment.api}/capacidades/${id}`;
        return this.http
            .get<any>(url)
            .pipe(catchError(this.handleError<any>('getCapacidadeById')));
    }

    getCapacidadesByEsteiraId(id : number): Observable<any> {
        const url: string = `${environment.api}/capacidades/esteira/${id}`;
        return this.http
            .get<any>(url)
            .pipe(catchError(this.handleError<any>('getCapacidadesByEsteiraId')));
    }
}
