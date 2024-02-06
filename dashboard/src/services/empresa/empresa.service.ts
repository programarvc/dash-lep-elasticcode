import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { numBytes } from 'aws-sdk/clients/finspace';

import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class EmpresaService {
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error('Erro => ' + JSON.stringify(error));
      console.error(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }

  constructor(private http: HttpClient) {}

  getEmpresas(): Observable<any> {
    const url: string = `${environment.api}/empresas`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>('getEmpresas')));
  }

  getEmpresasByColaborador(id: number): Observable<any> {
    const url: string = `${environment.api}/empresas/colaborador/${id}`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>('getEmpresasByColaborador')));
  }

  getColaboradoresByEmpresa(id: number): Observable<any> {
    const url: string = `${environment.api}/empresas/${id}/colaboradores`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>('getColaboradoresByEmpresa')));
  }
  
}
