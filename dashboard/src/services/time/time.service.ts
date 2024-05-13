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
 /*
  //metodo antigo
  getPrCountByColaboradorId( colaboradorId: number): Observable<any> {
    const url: string = `${environment.api}/prcount/colaborador/${colaboradorId}`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>("getPrCountByColaboradorId")));
  } */

  //metodo novo busca quantidade de PRs por colaboradorId
  getPrCountForColaboradorId( colaboradorId: number): Observable<any> {
    const url: string = `${environment.api}/prcount/allprs/${colaboradorId}`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>("getPrCountForColaboradorId")));
  }

  //
  //metdo busca quantidade de PRs por colaboradorId no ano corrente
  getPrCountLast1YearForColaborador (colaboradorId: number): Observable<any> {
    const url: string = `${environment.api}/prcount/last1year/${colaboradorId}`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>("getPrCountLast90DaysForColaborador")));
  }


  //metodo busca quantidade de PRs por colaboradorId no ano anterior
  getPrCountLastYearForColaborador (colaboradorId: number): Observable<any> {
    const url: string = `${environment.api}/prcount/lastyear/${colaboradorId}`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>("getPrCountLast90DaysForColaborador")));
  }


  //metdo busca quantidade de PRs por colaboradorId no ano corrente
  getPrCountThisYearForColaborador (colaboradorId: number): Observable<any> {
    const url: string = `${environment.api}/prcount/thisyear/${colaboradorId}`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>("getPrCountLast90DaysForColaborador")));
  }

  //metodo busca quantidade de PRs por colaboradorId nos ultimos 90 dias
  getPrCountLast90DaysForColaborador (colaboradorId: number): Observable<any> {
    const url: string = `${environment.api}/prcount/last90days/${colaboradorId}`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>("getPrCountLast90DaysForColaborador")));
  }

  //metodo busca quantidade de PRs por colaboradorId nos ultimos 30 dias
  getPrCountLast30DaysForColaborador (colaboradorId: number): Observable<any> {
    const url: string = `${environment.api}/prcount/last30days/${colaboradorId}`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>("getPrCountLast30DaysForColaborador")));
  }

  //metodo busca quantidade de PRs por colaboradorId nos ultimos 7 dias
  getPrCountLast7DaysForColaborador (colaboradorId: number): Observable<any> {
    const url: string = `${environment.api}/prcount/last7days/${colaboradorId}`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>("getPrCountLast7DaysForColaborador")));
  }

  //metodo busca quantidade de PRs por colaboradorId dentro de uma data especifica
  getPrCountDateForColaborador (colaboradorId: number): Observable<any> {
    const url: string = `${environment.api}/dates/${colaboradorId}`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>("getPrCountDateForColaborador")));
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
