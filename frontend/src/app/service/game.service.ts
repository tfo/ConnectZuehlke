import {Observable} from 'rxjs';
import {Game} from '../domain/Game';

export interface GameService {
  createNewGame(): Observable<Game>
}

