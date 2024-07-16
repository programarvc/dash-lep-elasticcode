import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class jiraActivitieseService {
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error('Erro => ' + JSON.stringify(error));
      console.error(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }

  constructor(private http: HttpClient) {}

  //retorna a quantidade de historias no jira para um epico especificado
  getCountStories(): Observable<any> {
    const url: string = `${environment.api}/jira/countstory`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>('getCountStory')));
  }

  //Endpoint retorna a quantidade de epicos no jira para um epico especificado
  getEpicList(): Observable<any> {
    const url: string = `${environment.api}/jira/countepics`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>('getCountEpics')));
  }
 
  //Endpoint retorna a quantidade total de atividades na  esteira  jira 
  getcountAllStories(): Observable<any> {
    const url: string = `${environment.api}/jira/countAllActivities`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>('getcountAllStories')));
  }

  getAllActivitiesLast60Days(): Observable<any> {
    const url: string = `${environment.api}/jira/countAllActivities/last60days`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>('getAllActivitiesLast60Days')));
  }

  //Endpoint retorna a media de hitorias por epico jira
  getAverageStoriesPerEpic(): Observable<any> {
    const url: string = `${environment.api}/jira/mediastoryperepic`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>('getAverageStoriesPerEpic')));
  }

  getEpicsLast60Days(): Observable<any> {
    const url: string = `${environment.api}/jira/countepicslast60days`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>('getEpicsLast60Days')));
  }

  getAveragePoints(): Observable<any> {
    const url: string = `${environment.api}/jira/averagepoints`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>('getAveragePoints')));
  }

  //Endpoint retorna a quantidade total de story points na  esteira  jira
  getTotalPointsForJiraStories(): Observable<any> {
    const url: string = `${environment.api}/jira/allStoryPoints`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>('getTotalPointsForJiraStories')));
  }

  // Endpoint retorna a quantidade total de story points na  esteira  jira nos ultimos 60 dias
  getTotalPointsForJiraStoriesLast60Days(): Observable<any> {
    const url: string = `${environment.api}/jira/allStoryPointsLast60days`;
    return this.http
      .get<any>(url)
      .pipe(
        catchError(this.handleError<any>('getTotalPointsForJiraStoriesLast60Days'))
      );
  }

  //Endpoint retorna as tarefas pendentes no Jira
  getPendingTasks(): Observable<any> {
    const url: string = `${environment.api}/jira/atividades-disponiveis`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>('getPendingTasks')));
  }
 
  getActivitiesPerEpic(epic: string): Observable<any> {
      const params = new HttpParams().set('epic', epic);
      const url: string = `${environment.api}/jira/epicslistacttivities`;
      return this.http
        .get<any>(url, { params })
        .pipe(catchError(this.handleError<any>('getActivitiesPerEpic')));
  }

  getNameAndPoints(): Observable<any> {
    const url: string = `${environment.api}/jira/namepoints`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>('getNameAndPoints')));
  }

  getFinishedActivities(): Observable<any> {
    const url: string = `${environment.api}/jira/completedActivities`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>('getFinishedAcitivities')));
  }

}
