import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

import { Observable, of } from "rxjs";
import { catchError } from "rxjs/operators";
import { environment } from "src/environments/environment";

import { Maturidade, TiposEnum } from "src/app/types/esteira-types";

@Injectable({
    providedIn: "root",
})

export class MaturidadeService {

  public currentMaturidade: Maturidade = {
    id: 0,
    esteira: {
      id: 0,
      nome: '',
      tipo: '' as TiposEnum,
      empresa: {
        id: 0,
        nome: '',
      },
    },
    dataHora: [],
    numero: 0,
    leadTime: 0,
    frequencyDeployment: 0,
    changeFailureRate: 0,
    timeToRecovery: 0,
    saude: 0,
    metricas4: 0,
    capacidadeDora: 0,
    mediaDeJornada: 0
  };

    setCurrentMaturidade(maturidade: Maturidade) {
      this.currentMaturidade = maturidade;
    }

    getCurrentMaturidade(): Maturidade {
      return this.currentMaturidade;
    }
    
    private handleError<T>(operation = "operation", result?: T) {
        return (error: any): Observable<T> => {
            console.error("Erro => " + JSON.stringify(error));
            console.error(`${operation} failed: ${error.message}`);
            return of(result as T);
        };
    }

  constructor(private http: HttpClient) {}

  getMaturidades(): Observable<any> {
    const url: string = `${environment.api}/maturidade`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>("getMaturidades")));
  }

  getMaturidadeById(id: number): Observable<any> {
    const url: string = `${environment.api}/maturidade/${id}`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>('getMaturidadeById')));
  }

  getMaturidadeByEsteiraId(id: number): Observable<any> {
    const url: string = `${environment.api}/maturidade/esteira/${id}`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>('getMaturidadeByEsteiraId')));
  }

  getLatestMaturidadeByEsteiraId(id: number): Observable<any> {
    const url: string = `${environment.api}/maturidade/latest/esteira/${id}`;
    return this.http
      .get<any>(url)
      .pipe(catchError(this.handleError<any>('getLatestMaturidadeByEsteiraId')));
  }

}
