import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable, of } from "rxjs";
import { catchError } from "rxjs/operators";
import { environment } from "src/environments/environment";

@Injectable({
  providedIn: "root",
})
export class EsteiraService {
  private esteiraSelecionadaSource = new BehaviorSubject<any>(null);
  esteiraSelecionada$ = this.esteiraSelecionadaSource.asObservable();

  constructor(private http: HttpClient) {}

  private handleError<T>(operation = "operation", result?: T) {
    return (error: any): Observable<T> => {
      console.error("Erro => " + JSON.stringify(error));
      console.error(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }

  getEsteiras(): Observable<any> {
    const url: string = `${environment.api}/esteira`;
    return this.http.get<any>(url).pipe(catchError(this.handleError<any>("getEsteiras")));
  }

  getEsteiraById(id: number): Observable<any> {
    const url: string = `${environment.api}/esteira/${id}`;
    return this.http.get<any>(url).pipe(catchError(this.handleError<any>('getEsteiraById')));
  }

  setEsteiraSelecionada(esteira: any): void {
    this.esteiraSelecionadaSource.next(esteira);
  }
}