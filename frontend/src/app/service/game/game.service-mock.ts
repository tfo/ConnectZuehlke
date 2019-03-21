import {Observable, of} from 'rxjs';
import {Injectable} from '@angular/core';

import {Answer} from '../../domain/Answer';
import {Game} from '../../domain/Game';
import {Question} from '../../domain/Question';
import {SECRET_EMPLOYEES} from '../employee/employee.service-mock';
import {GameService} from './game.service';


export const QUESTIONS: Question[] = [
  new Question('q1', 'What gender is the person?', [
      new Answer('q1-a1', 'Male', [1, 2], 'The person is male.'),
      new Answer('q1-a2', 'Female', [3, 4], 'The person is female.')
    ]
  ),
  new Question('q2', 'Where does the person work?', [
      new Answer('q2-a1', 'Switzerland', [1], 'The person works in Switzerland.'),
      new Answer('q2-a2', 'Everywhere else', [2, 3, 4], 'The person works somewhere else.')
    ]
  ),
  new Question('q3', 'What\'s the role of the person?', [
      new Answer('q3-a1', 'Engineer', [2, 3], 'The person is engineer.'),
      new Answer('q3-a1', 'Everything else', [1, 4], 'The person is something else.')
    ]
  )
];

export const GAME: Game = new Game('game-id', SECRET_EMPLOYEES, SECRET_EMPLOYEES[2], QUESTIONS);

@Injectable({providedIn: 'root'})
export class GameServiceMock implements GameService {
  createNewGame(): Observable<Game> {
    return of(GAME);
  }
}
