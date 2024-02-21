import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class valorMaturidadeService {
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error('Erro => ' + JSON.stringify(error));
      console.error(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }

  constructor(private http: HttpClient) {}

  getValorMaturidades(): Observable<any> {
    const url: string = `${environment.api}/indicesdematuridade`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>('getvalorMaturidades')));
  }


  getValorMaturidadeByItemId(id: number): Observable<any> {
    const url: string = `${environment.api}/indicesdematuridade/item/${id}`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>('getValorMaturidadeByItemId')));
  }

  getValorMaturidadesById(id: number): Observable<any> {
    const url: string = `${environment.api}/indicesdematuridade/${id}`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>('getValorMaturidadesById')));
  }

  getValorMaturidadesByTipo(tipo: string): Observable<any> {
    const url: string = `${environment.api}/indicesdematuridade/tipo/${tipo}`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>('getValorMaturidadesByType')));
  }

  getValoresByEsteiraIdAndTipoMaturidadeTecnicaLatest(id: number): Observable<any> {
    const url: string = `${environment.api}/indicesdematuridade/esteira/${id}/tipo/TECNICA/latest`;
    return this.http
      .get<any>(url)
      .pipe(
        catchError(
          this.handleError<any>(
            'getValoresByEsteiraIdAndTipoMaturidadeLatest'
          )
        )
      );
  }

  getValorMaturidadesByEsteiraIdAndCulturaLatest(id: number): Observable<any> {
    const url: string = `${environment.api}/indicesdematuridade/esteira/${id}/tipo/CULTURA/latest`;
    return this.http
      .get<any>(url)
      .pipe(
        catchError(
          this.handleError<any>('getValorMaturidadesByEsteiraIdAndCulturaLatest')
        )
      );
  }

  getValorMaturidadesByEsteiraIdAndProcessoLatest(id: number): Observable<any> {
    const url: string = `${environment.api}/indicesdematuridade/esteira/${id}/tipo/PROCESSO/latest`;
    return this.http
      .get<any>(url)
      .pipe(
        catchError(
          this.handleError<any>('getValorMaturidadesByEsteiraIdAndProcessoLatest')
        )
      );
  }

  getValorMaturidadesByEsteiraIdAndMetricaLatest(id: number): Observable<any> {
    const url: string = `${environment.api}/indicesdematuridade/esteira/${id}/tipo/METRICA/latest`;
    return this.http
      .get<any>(url)
      .pipe(
        catchError(
          this.handleError<any>('getValorMaturidadesByEsteiraIdAndMetricaLatest')
        )
      );
  }




  getMaturidadeFilterById(maturidadeId: number): Observable<any>{
    const url: string = `${environment.api}/indicesdematuridade/maturidade/${maturidadeId}`;
    return this.http
      .get<any>(url)
      .pipe(
        catchError(
          this.handleError<any>('getMaturidadeById')
        )
      );
  }

  getValorDoIndicesByMaturidadeId(maturidadeId: number): Observable<any>{
    const url: string = `${environment.api}/indicesdematuridade/allmaturidade/${maturidadeId}`;
    return this.http
      .get<any>(url)
      .pipe(
        catchError(
          this.handleError<any>('getValorDoIndicesByMaturidadeId')
        )
      );
  }
}
