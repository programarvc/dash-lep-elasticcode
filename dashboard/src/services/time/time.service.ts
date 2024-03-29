import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

import { Observable, of } from "rxjs";
import { catchError } from "rxjs/operators";
import { environment } from "src/environments/environment";
import { MetasColaborador } from "src/app/types/time-types";

@Injectable({
    providedIn: "root",
})

export class TimeService {

  public currentMetasColaborador: MetasColaborador = {
    id: 0,
    colaborador: {
      id: 0,
      nome: '',
      email: '',
      github: '',
      miniBio: '',
      habilidades: [],
    },
    meta: {
      id: 0,
      metas: '',
    },
    data: [],
  };

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

  getLatestMetaByColaboradorId(colaboradorId: number){
    const url: string = `${environment.api}/metas/colaborador/${colaboradorId}`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>("getLatestMetaByColaboradorId")));
  }

  getAllLatestMetaByColaboradorId(colaboradorId: number){
    const url: string = `${environment.api}/metas/colaborador/${colaboradorId}/all`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>("getLatestMetaByColaboradorId")));
  }

  getMetas(): Observable <any> {
    const url: string = `${environment.api}/metas`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>("getMetas")));
  }

  getPrCountByColaboradorId( colaboradorId: number): Observable<any> {
    const url: string = `${environment.api}/prcount/colaborador/${colaboradorId}`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>("getPrCountByColaboradorId")));
  }

  getValorIndicePorIdColaborador(colaboradorId: number): Observable<any> {
    const url: string = `${environment.api}/indiceDeSobrevivencia/colaborador/${colaboradorId}`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>("getValorIndicePorIdColaborador")));

  }

  setCurrentMetasColaborador(metas: MetasColaborador) {
    this.currentMetasColaborador = metas;
  }

  getCurrentMetasColaborador(): MetasColaborador {
    return this.currentMetasColaborador;
  }


}
