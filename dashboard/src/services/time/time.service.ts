import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";

import { Observable, of } from "rxjs";
import { catchError } from "rxjs/operators";
import { environment } from "src/environments/environment";
import { CompetenciasPorData, DataArray, MetasColaborador } from "src/app/types/time-types";

@Injectable({
    providedIn: "root",
})

export class TimeService {

  public currentMetasColaborador: MetasColaborador = {
    id: 0,
    nota: [],
    colaborador: {
      id: 0,
      nome: '',
      email: '',
      github: '',
      miniBio: '',
      habilidades: [],
    },
    competencia: [],
    data: []
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
 /*
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
  }*/


getTop3CompetenciasByColaboradorAndData(colaboradorId: number, data: string){
    const formattedDate = new Date(data).toISOString().split('T')[0];
    const url: string = `${environment.api}/metas/competencias/${colaboradorId}?data=${formattedDate}`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>("getTop3CompetenciasByColaboradorAndData")));
}


getMetaWithAtLeast3metas(colaboradorId: number): Observable<CompetenciasPorData> {
  const url: string = `${environment.api}/metas/datameta/${colaboradorId}`;
  return this.http
    .get<CompetenciasPorData>(url)
    .pipe(catchError(this.handleError<CompetenciasPorData>("getMetaWithAtLeast3metas")));
}

getAllLatestMetaByColaboradorId(colaboradorId: number): Observable<DataArray[]> {
  const url: string = `${environment.api}/metas/datas/${colaboradorId}`;
  return this.http
    .get<DataArray[]>(url)
    .pipe(catchError(this.handleError<DataArray[]>("getAllLatestMetaByColaboradorId")));
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
getPrCountDateForColaborador (colaboradorId: number, startDate: string, endDate: string): Observable<any> {
  const url: string = `${environment.api}/prcount/dates/${colaboradorId}`;
  const params = new HttpParams().set('startDate', startDate).set('endDate', endDate);
  return this.http
    .get<any>(url, { params })
    .pipe(catchError(this.handleError<any>("getPrCountDateForColaborador")));
}

  //-------metodos para quantidade de tasks concluidas por colaboradorid-------

  //metodo busca quantidade de tasks concluidas por colaboradorId nos ultimos 90 dias
  getCountCompletedTasksLast90DaysByColaboradorId (colaboradorId: number): Observable<any> {
    const url: string = `${environment.api}/taskscount/last90days/${colaboradorId}`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>("getPrCountLast90DaysForColaborador")));
  }

  //metodo busca quantidade de tasks concluidas por colaboradorId nos ultimos 30 dias
  getCountCompletedTasksLast30DaysByColaboradorId (colaboradorId: number): Observable<any> {
    const url: string = `${environment.api}/taskscount/last30days/${colaboradorId}`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>("getPrCountLast30DaysForColaborador")));
  }

  //metodo busca quantidade de tasks concluidas por colaboradorId nos ultimos 7 dias
  getCountCompletedTasksLast7DaysByColaboradorId (colaboradorId: number): Observable<any> {
    const url: string = `${environment.api}/taskscount/last7days/${colaboradorId}`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>("getPrCountLast7DaysForColaborador")));
  }

   //metdo busca quantidade de PRs por colaboradorId no ano corrente
   getCountCompletedTasksThisYearByColaboradorId (colaboradorId: number): Observable<any> {
    const url: string = `${environment.api}/taskscount/thisyear/${colaboradorId}`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>("getCountCompletedTasksThisYearByColaboradorId")));
  }

  //metdo busca quantidade de PRs por colaboradorId no ano corrente
  getCountCompletedTasksLastYearByColaboradorId (colaboradorId: number): Observable<any> {
    const url: string = `${environment.api}/taskscount/lastyear/${colaboradorId}`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>("getCountCompletedTasksLastYearByColaboradorId")));
  }

  getTasksCountForColaboradorId (colaboradorId: number, startDate: string, endDate: string): Observable<any> {
      const url: string = `${environment.api}/taskscount/dates/${colaboradorId}`;
      const params = new HttpParams().set('startDate', startDate).set('endDate', endDate);
      return this.http
        .get<any>(url, { params })
        .pipe(catchError(this.handleError<any>("getTasksCountForColaboradorId")));
  }

  //metodo busca quantidade de tasks concluidas total por colaboradorId 
  getCountAllCompletedTasksByColaboradorId (colaboradorId: number): Observable<any> {
    const url: string = `${environment.api}/taskscount/all/${colaboradorId}`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>("getCountAllCompletedTasksByColaboradorId")));
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
