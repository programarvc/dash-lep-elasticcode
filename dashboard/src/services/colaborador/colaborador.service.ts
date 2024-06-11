import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ColaboradorService {
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error('Erro => ' + JSON.stringify(error));
      console.error(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }

  constructor(private http: HttpClient) {}

  getAllColaboradores(): Observable<any> {
    const url: string = `${environment.api}/colaborador`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>('getAllColaboradores')));
  }

  getColaboradoresByEsteiraId(id: number): Observable<any> {
    const url: string = `${environment.api}/colaborador/esteira/${id}`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>('getColaboradoresByEsteiraId')));
  }

  getColaboradorById(id: number): Observable<any> {
    const url: string = `${environment.api}/colaborador/${id}`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>('getColaboradorById')));
  }

  postNovoColaborador(novoColaborador: any): Observable<any>{
    const url: string = `${environment.api}/colaborador/novo-colaborador`;
    return this.http
      .post<any>(url, novoColaborador)
      .pipe(catchError(this.handleError<any>('postNovoColaborador')));
  }

}
