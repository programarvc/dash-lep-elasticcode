import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";

import { Observable, of } from "rxjs";
import { catchError } from "rxjs/operators";
import { environment } from "src/environments/environment";

@Injectable({
    providedIn: "root",
})

export class EsteiraService {

  private esteiraSelecionadaSource = new BehaviorSubject<any>(null);
  esteiraSelecionada$ = this.esteiraSelecionadaSource.asObservable();

  getEsteirasById() {
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

  getEsteiras(): Observable<any> {
    const url: string = `${environment.api}/esteira`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>("getEsteiras")));
  }

  getEsteiraById(id: number): Observable<any> {
    const url: string = `${environment.api}/esteira/${id}`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>('getEsteiraById')));
  }

  //setter para a esteira selecionada
  setEsteiraSelecionada(esteira: any) {
    this.esteiraSelecionadaSource.next(esteira);
  }

}
