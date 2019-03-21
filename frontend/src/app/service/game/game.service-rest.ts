import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {GameDto} from './GameDto';
import {catchError, map} from 'rxjs/operators';
import {GameService} from './game.service';
import {Game} from '../../domain/Game';
import {GameDtoMapper} from './GameDtoMapper';

@Injectable({
  providedIn: 'root'
})
export class GameRestService implements GameService {

  private GAME_URL = '/api/game';
  private GAME_SIZE = 8;

  constructor(private http: HttpClient) {
  }

  public createNewGame(): Observable<Game> {
    const httpOptions = {
      params: new HttpParams().set('numberOfEmployees', `${this.GAME_SIZE}`)
    };

    return this.http
      .get<GameDto>(this.GAME_URL, httpOptions)
      .pipe(
        catchError(this.handleError('createNewGame', null)),
        map(gameDto => GameDtoMapper.mapFromDto(gameDto))
      );
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  private log(message: string): void {
    console.log(`${this}: ${message}`);
  }
}
