import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

import { Observable, of } from "rxjs";
import { catchError } from "rxjs/operators";
import { environment } from "src/environments/environment";

@Injectable ({
    providedIn: 'root',
})

export class PromptService {
    private handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {
            console.error('Erro =>' + JSON.stringify(error));
            console.error(`${operation} failed: ${error.message}`);
            return of(result as T);
        }
    }

    constructor(private http: HttpClient) {}

    //Obter todos os prompts
    postPromptHistory(promptHistory: any): Observable<any> {
        const url: string = `${environment.api}/prompts-history`;
        return this.http
            .post<any>(url, promptHistory)
            .pipe(catchError(this.handleError<any>('postPromptHistory')));
    }

    //Obter prompt por id
    getPromptHistoryById(id: any): Observable<any> {
        const url = `${environment.api}/prompts-history/${id}`;
        return this.http
            .get<any>(url)
            .pipe(catchError(this.handleError<any>('getPromptHistoryById')));
    }

    //Obter prompts por userEsteiraId
    getPromptsHistoryByUserEsteiraId(userEsteiraId: any): Observable<any> {
        const url = `${environment.api}/prompts-history/user-esteira/${userEsteiraId}`;
        return this.http
            .get<any>(url)
            .pipe(catchError(this.handleError<any>('getPromptsHistoryByUserEsteiraId')));
    }

    //Obter quantidade de prompts por userEsteiraId
    getContPromptsByUserEsteiraId(userEsteiraId: any): Observable<any> {
        const url =`${environment.api}/prompts-history/count/user-esteira/${userEsteiraId}`;
        return this.http
            .get<any>(url)
            .pipe(catchError(this.handleError<any>('getContPromptsByUserEsteiraId')));
    }

    //Obter prompts por EsteiraId
    getPromptsHistoryByEsteiraId(esteiraId: any): Observable<any> {
        const url =`${environment.api}/prompts-history/esteira/${esteiraId}`;
        return this.http
            .get<any>(url)
            .pipe(catchError(this.handleError<any>('getPromptsHistoryByEsteiraId')));
    }

    //Obter quantidade de prompts por EsteiraId
    getContPromptsByEsteiraId(esteiraId: any): Observable<any> {
        const url =`${environment.api}/prompts-history/count/esteira/${esteiraId}`;
        return this.http
            .get<any>(url)
            .pipe(catchError(this.handleError<any>('getContPromptsByEsteiraId')));
    }
}