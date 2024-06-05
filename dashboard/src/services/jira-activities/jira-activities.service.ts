import { HttpClient } from '@angular/common/http';
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
  getCountEpics(): Observable<any> {
    const url: string = `${environment.api}/jira/countepics`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>('getCountEpics')));
  }
 
  //Endpoint retorna a quantidade total de historias na  esteira  jira 
  getcountAllStories(): Observable<any> {
    const url: string = `${environment.api}/jira/countAllStories`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>('getcountAllStories')));
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
 
}
