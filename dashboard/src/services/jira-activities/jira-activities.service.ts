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

  getCountStories(): Observable<any> {
    const url: string = `${environment.api}/jira/countstory`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>('getCountStory')));
  }

  getCountEpics(): Observable<any> {
    const url: string = `${environment.api}/jira/countepics`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>('getCountEpics')));
  }
 
}
