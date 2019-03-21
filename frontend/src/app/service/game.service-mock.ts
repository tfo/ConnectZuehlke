import {Game} from '../domain/Game';
import {EMPLOYEES} from '../employee.service-mock';
import {GameService} from './game.service';
import {Observable, of} from 'rxjs';

export const GAME: Game = {
  id: 'game-id',
  employees: EMPLOYEES,
  selectedEmployee: EMPLOYEES[2]
};

export class GameServiceMock implements GameService {
  public createNewGame(): Observable<Game> {
    return of(GAME);
  }
}
